function updateClock() {
    const now = new Date();
    
    let h = now.getHours();
    let m = now.getMinutes();
    let s = now.getSeconds();
    let ampm = h >= 12 ? 'PM' : 'AM';

    const options = { weekday: 'long', month: 'short', day: '2-digit', year: 'numeric' };
    document.getElementById('date-display').innerText = now.toLocaleDateString('en-US', options);

    let hDigital = h % 12;
    hDigital = hDigital ? hDigital : 12;
    
    document.getElementById('hour').innerText = String(hDigital).padStart(2, '0');
    document.getElementById('min').innerText = String(m).padStart(2, '0');
    document.getElementById('sec').innerText = String(s).padStart(2, '0');
    document.getElementById('ampm').innerText = ampm;

    const hDeg = (h % 12) * 30 + (m / 60) * 30; 
    const mDeg = m * 6;
    const sDeg = s * 6;

    document.getElementById('hour-hand').style.transform = `translateX(-50%) rotate(${hDeg}deg)`;
    document.getElementById('min-hand').style.transform = `translateX(-50%) rotate(${mDeg}deg)`;
    document.getElementById('sec-hand').style.transform = `translateX(-50%) rotate(${sDeg}deg)`;

   
}

function switchClock(design) {
    document.querySelectorAll('.clock-view').forEach(view => {
        view.classList.remove('active');
    });
    
    document.getElementById(design).classList.add('active');

    document.querySelectorAll('.btn').forEach(btn => {
        btn.classList.remove('active');
        if(btn.innerText.toLowerCase() === design) btn.classList.add('active');
    });
}

setInterval(updateClock, 1000);
updateClock();