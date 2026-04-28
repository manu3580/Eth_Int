const storageKey = 'flux-transactions';
const legacyStorageKey = 'transactions';
const budgetKey = 'flux-monthly-budget';

let transactions = JSON.parse(localStorage.getItem(storageKey) || localStorage.getItem(legacyStorageKey) || '[]');
let expenseChart = null;

const categories = ['Food', 'Travel', 'Bills', 'Shopping', 'Health', 'Study'];
const colors = ['#d92d20', '#2457d6', '#f79009', '#7a5af8', '#079455', '#0e9384'];
const currency = new Intl.NumberFormat('en-IN', {
    style: 'currency',
    currency: 'INR'
});

const form = document.getElementById('transaction-form');
const list = document.getElementById('transaction-list');
const emptyState = document.getElementById('empty-state');
const balanceEl = document.getElementById('total-balance');
const incomeEl = document.getElementById('total-income');
const expenseEl = document.getElementById('total-expense');
const savingsRateEl = document.getElementById('savings-rate');
const monthlyExpenseEl = document.getElementById('hero-monthly-expense');
const budgetStatusEl = document.getElementById('hero-budget-status');
const progressEl = document.getElementById('budget-progress');
const budgetInput = document.getElementById('budget-input');
const searchInput = document.getElementById('search');
const categoryFilter = document.getElementById('filter-category');
const topCategoryEl = document.getElementById('top-category');
const themeToggle = document.getElementById('theme-toggle');

function init() {
    document.getElementById('date').valueAsDate = new Date();
    budgetInput.value = localStorage.getItem(budgetKey) || '';

    if (localStorage.getItem('flux-theme') === 'dark') {
        document.body.classList.add('dark');
        themeToggle.textContent = 'Light';
    }

    initChart();
    bindEvents();
    render();
}

function bindEvents() {
    form.addEventListener('submit', addTransaction);
    searchInput.addEventListener('input', render);
    categoryFilter.addEventListener('change', render);
    document.getElementById('save-budget').addEventListener('click', saveBudget);
    document.getElementById('export-csv').addEventListener('click', exportCSV);
    themeToggle.addEventListener('click', toggleTheme);
}

function addTransaction(event) {
    event.preventDefault();

    const amount = Number(document.getElementById('amount').value);
    const title = document.getElementById('title').value.trim();

    if (!title || amount <= 0) {
        return;
    }

    transactions.unshift({
        id: crypto.randomUUID ? crypto.randomUUID() : String(Date.now()),
        title,
        amount,
        category: document.getElementById('category').value,
        date: document.getElementById('date').value
    });

    updateStorage();
    form.reset();
    document.getElementById('date').valueAsDate = new Date();
    render();
}

function deleteTransaction(id) {
    transactions = transactions.filter((transaction) => transaction.id !== id);
    updateStorage();
    render();
}

function getFilteredTransactions() {
    const searchTerm = searchInput.value.trim().toLowerCase();
    const selectedCategory = categoryFilter.value;

    return transactions.filter((transaction) => {
        const matchesSearch = transaction.title.toLowerCase().includes(searchTerm);
        const matchesCategory = selectedCategory === 'all' || transaction.category === selectedCategory;
        return matchesSearch && matchesCategory;
    });
}

function render() {
    const filteredTransactions = getFilteredTransactions();
    renderSummary();
    renderList(filteredTransactions);
    updateChart();
}

function renderSummary() {
    const totals = transactions.reduce((summary, transaction) => {
        if (transaction.category === 'Income') {
            summary.income += Number(transaction.amount);
        } else {
            summary.expense += Number(transaction.amount);
        }
        return summary;
    }, { income: 0, expense: 0 });

    const balance = totals.income - totals.expense;
    const savingsRate = totals.income ? Math.max(0, Math.round((balance / totals.income) * 100)) : 0;
    const monthlyExpense = getCurrentMonthExpense();

    balanceEl.textContent = currency.format(balance);
    incomeEl.textContent = `+${currency.format(totals.income)}`;
    expenseEl.textContent = `-${currency.format(totals.expense)}`;
    savingsRateEl.textContent = `${savingsRate}%`;
    monthlyExpenseEl.textContent = currency.format(monthlyExpense);
    updateBudget(monthlyExpense);
}

function renderList(items) {
    list.innerHTML = '';
    emptyState.style.display = items.length ? 'none' : 'block';

    items.forEach((transaction) => {
        const type = transaction.category === 'Income' ? 'income' : 'expense';
        const item = document.createElement('li');
        item.className = 'transaction-item';

        item.innerHTML = `
            <div class="item-main">
                <h4>${escapeHTML(transaction.title)}</h4>
                <p>${formatDate(transaction.date)} &middot; ${transaction.category}</p>
            </div>
            <div class="item-actions">
                <span class="amount ${type}">${type === 'income' ? '+' : '-'}${currency.format(transaction.amount)}</span>
                <button class="delete-btn" type="button" aria-label="Delete ${escapeHTML(transaction.title)}">&times;</button>
            </div>
        `;

        item.querySelector('.delete-btn').addEventListener('click', () => deleteTransaction(transaction.id));
        list.appendChild(item);
    });
}

function initChart() {
    const context = document.getElementById('expenseChart').getContext('2d');

    expenseChart = new Chart(context, {
        type: 'doughnut',
        data: {
            labels: categories,
            datasets: [{
                data: categories.map(() => 0),
                backgroundColor: colors,
                borderColor: 'transparent',
                hoverOffset: 8
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            cutout: '68%',
            plugins: {
                legend: {
                    position: 'bottom',
                    labels: {
                        usePointStyle: true,
                        boxWidth: 8,
                        color: getComputedStyle(document.body).getPropertyValue('--muted').trim()
                    }
                }
            }
        }
    });
}

function updateChart() {
    const chartData = categories.map((category) => {
        return transactions
            .filter((transaction) => transaction.category === category)
            .reduce((sum, transaction) => sum + Number(transaction.amount), 0);
    });

    const highestValue = Math.max(...chartData);
    const highestIndex = chartData.indexOf(highestValue);
    topCategoryEl.textContent = highestValue > 0 ? `${categories[highestIndex]} leads` : 'No expenses yet';

    expenseChart.data.datasets[0].data = chartData;
    expenseChart.options.plugins.legend.labels.color = getComputedStyle(document.body).getPropertyValue('--muted').trim();
    expenseChart.update();
}

function updateBudget(monthlyExpense) {
    const budget = Number(localStorage.getItem(budgetKey) || 0);

    if (!budget) {
        budgetStatusEl.textContent = 'Set a budget to start tracking progress.';
        progressEl.style.width = '0%';
        progressEl.style.background = '#39d98a';
        return;
    }

    const percentage = Math.min(100, Math.round((monthlyExpense / budget) * 100));
    const remaining = budget - monthlyExpense;

    progressEl.style.width = `${percentage}%`;
    progressEl.style.background = percentage >= 90 ? '#f04438' : percentage >= 70 ? '#f79009' : '#39d98a';
    budgetStatusEl.textContent = remaining >= 0
        ? `${currency.format(remaining)} left from ${currency.format(budget)}.`
        : `${currency.format(Math.abs(remaining))} over budget.`;
}

function saveBudget() {
    const budget = Number(budgetInput.value);

    if (budget > 0) {
        localStorage.setItem(budgetKey, String(budget));
    } else {
        localStorage.removeItem(budgetKey);
        budgetInput.value = '';
    }

    renderSummary();
}

function getCurrentMonthExpense() {
    const today = new Date();

    return transactions
        .filter((transaction) => {
            const date = new Date(transaction.date);
            return transaction.category !== 'Income'
                && date.getMonth() === today.getMonth()
                && date.getFullYear() === today.getFullYear();
        })
        .reduce((sum, transaction) => sum + Number(transaction.amount), 0);
}

function exportCSV() {
    const rows = [
        ['Title', 'Amount', 'Category', 'Date'],
        ...transactions.map((transaction) => [
            transaction.title,
            transaction.amount,
            transaction.category,
            transaction.date
        ])
    ];

    const csv = rows
        .map((row) => row.map((value) => `"${String(value).replaceAll('"', '""')}"`).join(','))
        .join('\n');

    const blob = new Blob([csv], { type: 'text/csv;charset=utf-8' });
    const url = URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = 'flux-expenses.csv';
    link.click();
    URL.revokeObjectURL(url);
}

function toggleTheme() {
    document.body.classList.toggle('dark');
    const isDark = document.body.classList.contains('dark');
    localStorage.setItem('flux-theme', isDark ? 'dark' : 'light');
    themeToggle.textContent = isDark ? 'Light' : 'Dark';
    updateChart();
}

function updateStorage() {
    localStorage.setItem(storageKey, JSON.stringify(transactions));
}

function formatDate(value) {
    if (!value) {
        return 'No date';
    }

    return new Intl.DateTimeFormat('en-US', {
        month: 'short',
        day: 'numeric',
        year: 'numeric'
    }).format(new Date(`${value}T00:00:00`));
}

function escapeHTML(value) {
    return value.replace(/[&<>"']/g, (character) => {
        return {
            '&': '&amp;',
            '<': '&lt;',
            '>': '&gt;',
            '"': '&quot;',
            "'": '&#039;'
        }[character];
    });
}

init();
