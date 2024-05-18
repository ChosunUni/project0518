let todoId; // 수정할 ToDo의 ID를 저장할 변수

// ToDo 삭제 요청 보내기
function deleteItem(id) {
    const action = '/todo/delete/' + id;
    const method = 'POST';
    $.ajax({
        type: method,
        url: action,
        success: function() {
            alert('삭제되었습니다.');
            window.location.reload(); // 페이지 새로고침
        },
        error: function(xhr, status, error) {
            alert('삭제에 실패하였습니다. 다시 시도해주세요.');
            console.error(xhr.responseText);
        }
    });
}

// 모달 열기
function openModal(idx){
    todoId = idx;
    $(".modal").fadeIn();
}


function reviseItem() {
    var contentWritten = $("#revise").val(); // 모달에서 입력한 내용 가져오기
    const url = "/todo/update/" + todoId;
    $.ajax({
        type: 'POST',
        url: url,
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8', // content-type 변경
        // contentType:'application/json; charset=UTF-8',

        data: { content: contentWritten }, // 데이터를 객체 형태로 변경
        // data: JSON.stringify({ content: contentWritten }),

        success: function() {
            alert('할 일이 수정되었습니다.');
            window.location.reload(); // 페이지 새로고침
        },
        error: function(xhr, status, error) {
            alert('수정에 실패하였습니다. 다시 시도해주세요.');
            console.error(xhr.responseText);
        }
    });

    $(".modal").fadeOut(); // 모달 닫기
}


function toggleTodoStatus(idx) {
    const action = '/todo/change/' + idx;
    const method = 'POST';

    $.ajax({
        type: method,
        url: action,
        success: function() {
            // 상태 변경 성공 시
            alert('상태를 변경하였습니다.');
            // 페이지 새로고침
            window.location.reload();
        },
        error: function(xhr, status, error) {
            alert('상태 변경에 실패하였습니다. 다시 시도해주세요.');
            console.error(xhr.responseText);
        }
    });
}
document.addEventListener('DOMContentLoaded', function () {
    const addTaskIcon = document.querySelector('.add-task i');
    const taskForm = document.querySelector('.add-task-form'); // 수정된 부분

    addTaskIcon.addEventListener('click', function () {
        taskForm.classList.toggle('active'); // 수정된 부분
    });
});

