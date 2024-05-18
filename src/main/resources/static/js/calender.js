let date = new Date();
let currYear = date.getFullYear(),
    currMonth = date.getMonth();

console.log(date);
console.log(currYear);
console.log(currMonth+1);

const months = [
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
    'July',
    'August',
    'September',
    'October',
    'November',
    'December',
];

console.log(months[currMonth]);

const currentDate = document.querySelector('.current-date');
currentDate.innerHTML = `${currYear} | ${months[currMonth]}`;
let lastDateofMonth = new Date(currYear, currMonth + 1, 0).getDate();
console.log(lastDateofMonth);

const renderCalendar = () => {
    let liTag = '';
    let firstDayofMonth = new Date(currYear, currMonth, 1).getDay();
    let lastDayofMonth = new Date(currYear, currMonth, lastDateofMonth).getDay();
    let lastDateofLastMonth = new Date(currYear, currMonth, 0).getDate();

    // 이전 달의 날짜를 출력
    for (let i = firstDayofMonth; i > 0; i--) {
        liTag += `<li class="inactive">${lastDateofLastMonth - i + 1}</li>`;
    }

    // 현재 달의 날짜를 출력
    for (let i = 1; i <= lastDateofMonth; i++) {
        let isToday = i === date.getDate() && currMonth === new Date().getMonth() && currYear === new Date().getFullYear() ? 'active' : '';
        liTag += `<li class="${isToday}">${i}</li>`;
    }

    const daysTag = document.querySelector('.days');
    daysTag.innerHTML = liTag;
}
const prevNextIcon = document.querySelectorAll('.material-icons');

prevNextIcon.forEach((icon) => {
    icon.addEventListener('click', () => {
        if (icon.id === 'prev')  {
            currMonth -= 1;
            if (currMonth < 0) {
                currMonth = 11;
                currYear -= 1;
            }
        }
        else {
            currMonth += 1;
            if (currMonth > 11) {
                currMonth = 0;
                currYear += 1;
            }
        }
        renderCalendar();

        currentDate.innerHTML = `${currYear} | ${months[currMonth]}`;
    });
});

renderCalendar();