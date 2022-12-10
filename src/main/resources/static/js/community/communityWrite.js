/* CommunityWrite.html */

let $title = $("#post-title-input");
let $content = $("textarea[name='boardContent']");
let $category = $("select[name='boardCategory']");
let $submitBtn = $("button.write-post-submit");
let $updateBtn = $("button.write-post-update");
let $xBtn = $("button.close");
let $okBtn = $("button.btn-block");
let $selectbox = $("select.post-subject-select-box");

const $writeFile = $("input#__BVID__4799");
let $writeDeleteFileBtn = $("img.delete-badge");
let fileIndex = 0;

let url = decodeURI(window.location.href).split("/");
let boardId = new URLSearchParams(location.search).get('id');
globalThis.board = null;

$title.on('keyup', function () {
    submitBtn();
});

$content.on('keyup', function () {
    submitBtn();
});

$xBtn.on('click', function () {
    closeModal();
});

$okBtn.on('click', function () {
    closeModal();
});

$selectbox.on('click', function () {
    selectSubject();
});

/* x버튼 혹은 확인 버튼 누르면 모달 닫기 */
function closeModal() {
    $("#__BVID__540___BV_modal_outer_").css('display', 'none');
}

/* 글 주제 selectbox 클릭 시 주제선택은 다시 선택 못하도록 */
function selectSubject() {
    $selectbox.children().eq(0).attr("disabled", true);
}

/* 유효성 검사 & 버튼 활성화 */
function submitBtn() {
    if (!$title.val()) {
        $submitBtn.removeClass("active");
        $updateBtn.removeClass("active")
        $submitBtn.attr("disabled", true);
        $updateBtn.attr("disabled", true);
        return;
    }

    if (!$content.val()) {
        $submitBtn.removeClass("active");
        $updateBtn.removeClass("active");
        $submitBtn.attr("disabled", true);
        $updateBtn.attr("disabled", true);
        return;
    }

    $submitBtn.addClass("active");
    $updateBtn.addClass("active");
    $submitBtn.removeAttr("disabled");
    $updateBtn.removeAttr("disabled");
}


/* ================================== Board ==================================*/

writeOrupdate();

function writeOrupdate(){
    if(!boardId){
        return;
    }
    $submitBtn.css('display', 'none');
    $updateBtn.css('display', 'block');
    $updateBtn.removeAttr('disabled');
    $updateBtn.removeClass('disabled');
    $updateBtn.addClass('active');
    $("#__BVID__540___BV_modal_outer_").css('display', 'none');

    updateBoardDetail();
}

/* 게시글 등록 버튼 클릭 시 */
$(document).ready(function(){
    let arrayFile = [];
    $submitBtn.on('click', function(){
        if(!$category.val()){
            alert("카테고리를 선택하세요.");
            return;
        }
        if(!$title.val()){
            alert("제목을 입력하세요.");
            return;
        }
        if(!$content.val()){
            alert("내용을 입력하세요.");
            return;
        }

        let formData = new FormData();

        formData.append('boardCategory', $category.val());
        formData.append('boardTitle', $title.val());
        formData.append('boardContent', $content.val());
        formData.append('memberId', 1);

        if(fileIndex != 0){
            for(var i = 0; i < fileIndex; i++){
                let selector = "input[name='files[" + i + "].fileBoardName']";
                formData.append('files[' + i + '].fileBoardName', $(selector).val());
            }
        }

        communityService.saveBoard(
            formData, saveBoard);
    });
});

function saveBoard(){
    location.href="/community";
}


/* 게시글 수정, 제목과 내용 미리 넣어주기 */
function updateBoardDetail(){
    communityService.getBoardDetail(
        boardId, updateBoard
    );
}

function updateBoard(board){
    globalThis.board = board;

    $category.val(board.boardCategory);
    $title.val(board.boardTitle);
    $content.val(board.boardContent);
}

/* 수정완료 버튼 클릭 시 */
$updateBtn.on('click', function(){
    let formData = new FormData();

    formData.append('boardId', boardId);
    formData.append('boardCategory', $category.val());
    formData.append('boardTitle', $title.val());
    formData.append('boardContent', $content.val());
    formData.append('boardView', board.boardView);
    formData.append('memberId', 1);

    if(fileIndex != 0){
        for(var i = 0; i < fileIndex; i++){
            let selector = "input[name='files[" + i + "].fileBoardName']";
            formData.append('files[' + i + '].fileBoardName', $(selector).val());
        }
    }

    communityService.updateBoard(
        formData, updateAfterBoard
    );
});

function updateAfterBoard(){
    location.href="/community/" + boardId;
}


/* 파일 업로드 시 upload 폴더에 이미지 저장 */
$writeFile.on('change', function(){
    let file = $writeFile[0].files[0];

    communityService.uploadBoardFile(
        file, afterUploadFile
    );
});

/* 파일 업로드 후 미리보기 사진 div 생성 */
function afterUploadFile(file){
    let text = "";
    text += `<div data-v-7d0a628e="" class="image-preview ` + fileIndex + `">`;
    text += `<img data-v-7d0a628e="" src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTYiIGhlaWdodD0iMTYiIHZpZXdCb3g9IjAgMCAxNiAxNiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Ik01LjM2NCA0LjIzNGEuNzk4Ljc5OCAwIDEgMC0xLjEzIDEuMTNMNi44NyA4LjAwMWwtMi42MzcgMi42MzZhLjguOCAwIDAgMCAxLjEzIDEuMTI5TDggOS4xM2wyLjYzNiAyLjYzNWEuNzk4Ljc5OCAwIDEgMCAxLjEzLTEuMTNMOS4xMyA4LjAwMmwyLjYzNy0yLjYzN2EuOC44IDAgMCAwLTEuMTMtMS4xMjlMOCA2Ljg3IDUuMzY0IDQuMjM0eiIgZmlsbD0iI0ZGRiIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=" class="delete-badge ` + fileIndex +`">`;
    text += `<img data-v-7d0a628e="" src="` + file + `" alt="badge.png" class="view">`;
    text += `<input type="hidden" name="files[` + fileIndex + `].fileBoardName" class="board-file-input ` + fileIndex + `" value="` + file + `"></div>`;

    console.log(`name=".files[` + fileIndex + `].fileBoardName"`);
    $(".editor-image-list").append(text);
    fileIndex++;
}

/* 첨부파일 x 클릭 시 div 삭제 */
$(".editor-image-list").on('click', '.delete-badge', function(){
    let index = $(this).eq(0).attr('class').split(" ")[1];

    $(".image-preview." + index).remove();
});
