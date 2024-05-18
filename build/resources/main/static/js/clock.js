setInterval( () => {
    const date = new Date();

    const h = date.getHours();
    const m = date.getMinutes();
    const s = date.getSeconds();

    const degHour = h * (360/12) + m *(360/12/60);
    const degMinute = m *(360/60);
    const degSecond = s * (360/60);

    const hour = document.querySelector('.hour');
    const minute = document.querySelector('.minute');
    const second = document.querySelector('.second');

    hour.style.transform = `rotate(${degHour}deg)`;
    minute.style.transform = `rotate(${degMinute}deg)`;
    second.style.transform = `rotate(${degSecond}deg)`;
},50);

