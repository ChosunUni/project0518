let totalTime = { hours: 0, minutes: 0, seconds: 0 }; // 총 누적 시간을 저장하기 위한 객체

// 총 누적 시간을 계산하고 화면에 표시하는 함수
function calculateTotalTime() {
    // 타이머가 실행 중인 상태라면 정지합니다.
    if (running) {
        stopTimer();
    }

    // 총 누적 시간 초기화
    totalTime = { hours: 0, minutes: 0, seconds: 0 };

    // 각 과목별로 누적된 시간을 총 누적 시간에 더합니다.
    for (let i = 0; i < timeDataList.length; i++) {
        totalTime.hours += timeDataList[i].hours;
        totalTime.minutes += timeDataList[i].minutes;
        totalTime.seconds += timeDataList[i].seconds;
    }

    // 초단위를 분과 시간으로 변환
    totalTime.minutes += Math.floor(totalTime.seconds / 60);
    totalTime.seconds %= 60;

    totalTime.hours += Math.floor(totalTime.minutes / 60);
    totalTime.minutes %= 60;

    // 화면에 총 누적 시간을 표시합니다.
    updateTimerDisplay(totalTime);
}
