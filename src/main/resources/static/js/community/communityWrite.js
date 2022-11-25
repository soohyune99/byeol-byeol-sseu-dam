/* CommunityWrite.html */

let $title = $("#post-title-input");
let $content = $("textarea[name='post-content']");
let $submitBtn = $("button.write-post-submit");
let $xBtn = $("button.close");
let $okBtn = $("button.btn-block");
let $selectbox = $("select.post-subject-select-box");

const $file = $("#__BVID__4799");
let $deleteFileBtn = $("img.delete-badge");
let fileIndex = 0;

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

/* 글 주제 selectbox 클릭 시 주제선택은 선택 못하도록 */
function selectSubject() {
    $selectbox.children().eq(0).attr("disabled", true);
}

/* 유효성 검사 & 버튼 활성화 */
function submitBtn() {
    if (!$title.val()) {
        $submitBtn.removeClass("active");
        $submitBtn.attr("disabled", true);
        return;
    }

    if (!$content.val()) {
        $submitBtn.removeClass("active");
        $submitBtn.attr("disabled", true);
        return;
    }

    $submitBtn.addClass("active");
    $submitBtn.removeAttr("disabled");
}

/* 사진 첨부 시 div 생성 */
$file.on('change', function (e) {
    var reader = new FileReader();
    let type = e.target.files[0].type;

    reader.readAsDataURL(e.target.files[0]);

    reader.onload = function (e) {
        let url = e.target.result;
        let text = "";

        if (url.includes('image')) {
            text += `<div data-v-7d0a628e="" class="image-preview ` + fileIndex + `">`;
            text += `<img data-v-7d0a628e="" src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTYiIGhlaWdodD0iMTYiIHZpZXdCb3g9IjAgMCAxNiAxNiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Ik01LjM2NCA0LjIzNGEuNzk4Ljc5OCAwIDEgMC0xLjEzIDEuMTNMNi44NyA4LjAwMWwtMi42MzcgMi42MzZhLjguOCAwIDAgMCAxLjEzIDEuMTI5TDggOS4xM2wyLjYzNiAyLjYzNWEuNzk4Ljc5OCAwIDEgMCAxLjEzLTEuMTNMOS4xMyA4LjAwMmwyLjYzNy0yLjYzN2EuOC44IDAgMCAwLTEuMTMtMS4xMjlMOCA2Ljg3IDUuMzY0IDQuMjM0eiIgZmlsbD0iI0ZGRiIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=" class="delete-badge ` + fileIndex +`">`;
            text += `<img data-v-7d0a628e="" src="` + url + `" alt="badge.png" class="view">`;
            text += `</div>`;

            $(".editor-image-list").append(text);
            fileIndex++;
        } else {
            alert("사진파일만 업로드 가능합니다.");
            return;
        }
    }
});

/* 첨부파일 x 클릭 시 div 삭제 */
$(".editor-image-list").on('click', '.delete-badge', function(){
    let index = $(this).eq(0).attr('class').split(" ")[1];

    $(".image-preview." + index).remove();
});


