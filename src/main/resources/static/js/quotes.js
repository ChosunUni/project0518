const studyQuotes = [
    "노력은 배신하지 않는다. - 나폴레옹",
    "꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다. - 탈무드",
    "실패는 재도전의 기회일 뿐이다. - 톰 홉스",
    "1일 1실천. 작은 습관이 큰 성과를 만든다. - 제임스 클리어",
    "마음만을 가지고 있어서는 안된다. 반드시 실천하여야 한다. - 이소룡",
    "더 이상 상황을 바꿀 수 없을 때 우리는 스스로를 변화시켜야 합니다. - 빅터 플랭클",
    "탁월함은 기술이 아니다. 태도입니다. - 랄프 마스턴"
];

const randomIndex = Math.floor(Math.random() * studyQuotes.length);
const selectedQuote = studyQuotes[randomIndex];

document.getElementById('quote').textContent = selectedQuote;
