let timerInterval; // setInterval을 저장하기 위한 변수
let startTime; // 시작 시간을 저장하기 위한 변수
let running = false; // 타이머가 실행 중인지 여부를 저장하는 변수
let selectedSubjectId; // 선택된 과목의 ID를 저장하기 위한 변수
let timeData; // 서버에서 가져온 시간 데이터를 저장하기 위한 변수

function callTimerFromServer() {
    var selectedId = document.querySelector('#subjectselect').value;
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/get-time/" + selectedId, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // 성공적으로 요청이 완료되면 서버에서 받은 데이터를 처리합니다.
            timeData = JSON.parse(xhr.responseText);
            // 시간 데이터를 받아와서 타이머에 표시합니다.
            updateTimerDisplay(timeData);
        }
    };
    xhr.send();
}

// 시간 데이터를 서버에서 가져와서 타이머에 표시하고 타이머를 시작하는 함수
function startTimerFromServer() {
    var selectedId = document.querySelector('#subjectselect').value;
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/get-time/" + selectedId, true);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // 성공적으로 요청이 완료되면 서버에서 받은 데이터를 처리합니다.
            timeData = JSON.parse(xhr.responseText);
            // 시간 데이터를 받아와서 타이머에 표시합니다.
            updateTimerDisplay(timeData);
            // 시작 시간을 설정합니다.
            startTime = new Date();
            // 타이머를 시작합니다.
            startTimer();
        }
    };

    xhr.send();
}

// 타이머 시작 함수
function startTimer() {
    timerInterval = setInterval(updateTimer, 1000);
    running = true;
}

// 타이머 업데이트 함수
function updateTimer() {
    let currentTime = new Date();
    let elapsedTime = currentTime - startTime;

    // 불러온 시간에서 측정된 시간을 더한 값 계산
    let totalElapsedTime = elapsedTime + (timeData.hours * 3600000) + (timeData.minutes * 60000) + (timeData.seconds * 1000);

    let seconds = Math.floor((totalElapsedTime / 1000) % 60);
    let minutes = Math.floor((totalElapsedTime / 1000 / 60) % 60);
    let hours = Math.floor((totalElapsedTime / 1000 / 60 / 60) % 24);

    // 10보다 작은 경우 0을 추가하여 두 자리로 표시
    seconds = seconds < 10 ? "0" + seconds : seconds;
    minutes = minutes < 10 ? "0" + minutes : minutes;
    hours = hours < 10 ? "0" + hours : hours;

    // 타이머 업데이트
    document.getElementById("timer").textContent = `${hours}:${minutes}:${seconds}`;
}

// 타이머 정지 함수
function stopTimer() {
    clearInterval(timerInterval);
    running = false;

    // 시간 데이터를 서버로 보내기
    saveTimeToDatabase();
}

// 시간 데이터를 서버로 보내는 함수
function saveTimeToDatabase() {
    // 시간 데이터를 가져오기
    let currentTime = document.getElementById("timer").textContent.split(":");
    let hours = parseInt(currentTime[0]);
    let minutes = parseInt(currentTime[1]);
    let seconds = parseInt(currentTime[2]);

    // 시간 데이터를 서버로 보내기
    let timeDataToSend = {// 선택된 과목의 ID
        hours: hours,
        minutes: minutes,
        seconds: seconds
    };

    // AJAX 요청을 보냅니다.
    var selectedId = document.querySelector('#subjectselect').value;
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/save-time/" + selectedId, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    // 전송할 데이터를 준비합니다.
    var data = JSON.stringify(timeDataToSend);

    // 요청이 완료되었을 때의 콜백 함수를 정의합니다.
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // 성공적으로 요청이 완료되면 여기에 처리 로직을 추가합니다.
            console.log("데이터 전송이 성공했습니다.");
        }
    };

    // 요청을 보냅니다.
    xhr.send(data);
}

// 시작 버튼 클릭 시 이벤트 핸들러
document.getElementById("startButton").addEventListener("click", function() {
    if (!running) {
        startTimerFromServer(); // 서버에서 시간 데이터를 가져와서 타이머를 시작합니다.
    }
});

// 종료 버튼 클릭 시 이벤트 핸들러
document.getElementById("stopButton").addEventListener("click", function() {
    if (running) {
        stopTimer();
    }
});

function deleteSubject() {
    var selectedId = document.querySelector('#subjectselect').value;
    if(selectedId !== "") {
        var form = document.getElementById('deleteForm');
        form.action = '/timer/del/' + selectedId;
        form.method = 'POST';
        form.submit();
    } else {
        alert('과목을 선택해주세요.');
    }
}

// 타이머 화면에 표시 함수
function updateTimerDisplay(timeData) {
    // 시간 데이터를 받아와서 타이머에 표시합니다.
    let hours = timeData.hours < 10 ? "0" + timeData.hours : timeData.hours;
    let minutes = timeData.minutes < 10 ? "0" + timeData.minutes : timeData.minutes;
    let seconds = timeData.seconds < 10 ? "0" + timeData.seconds : timeData.seconds;
    document.getElementById("timer").textContent = hours + ":" + minutes + ":" + seconds;
}