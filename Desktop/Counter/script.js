let count = 0;

const valueDisplay = document.getElementById('value');
const btnIncrement = document.getElementById('increment');
const btnDecrement = document.getElementById('decrement');
const btnReset = document.getElementById('reset');

function updateDisplay() {
    valueDisplay.textContent = count;

    if (count > 0) {
        valueDisplay.style.color = "#4ade80";
    } else if (count < 0) {
        valueDisplay.style.color = "#f87171";
    } else {
        valueDisplay.style.color = "#f8fafc";
    }

    
}

function increment() {
    count++;
    updateDisplay();
}

function decrement() {
    count--;
    updateDisplay();
}

function reset() {
    count = 0;
    updateDisplay();
}

btnIncrement.addEventListener('click', increment);
btnDecrement.addEventListener('click', decrement);
btnReset.addEventListener('click', reset);


updateDisplay();