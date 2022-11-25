/* marketDetail.html */

/* 장바구니 모달 열고 닫기 변수 선언 */
let $basketModal = $("#shop_detail_add_cart_alarm");

/* 상품 드롭메뉴 열고 닫기 변수 선언 */
let $dropdownMenu = $(".form-select-wrap .dropdown-menu");
let check = -1;

/* 아이템 추가 변수 선언 */
let $dropdownItem = $(".dropdown-item a.blocked");
let $itemBox = $(".goods_payment .order_quantity_area");
let index = 0;

/* 상품 개수 조절 변수 선언 */
let $totalItemCount = $(".body_font_color_70.itemCount");
let $totalCount = $totalItemCount.text().split('개')[0].split('(')[1];
let $totalPrice = $(".total_price");

/* 리뷰 사진 첨부 변수 선언 */
const $file = $("#review_image_upload_btn_c202206217f7c30ccb91ca");
let $deleteFileBtn = $("img.delete-badge");



/* 장바구니 모달 열기 */
function basketModalOpen(){
    $basketModal.css('display', 'block');
}

/* 장바구니 모달 닫기 */
function basketModalClose(){
    $basketModal.css('display', 'none');
}


/* 상품 드롭 메뉴 열고 닫기 */
function dropdownMenu(){
    console.log("들어옴");
    if(check < 0){
        $dropdownMenu.css('display', 'block');
        $("#Layer_1").css('transform', 'rotate(180deg)');
    }else {
        $dropdownMenu.css('display', 'none');
        $("#Layer_1").css('transform', 'rotate(0deg)');
    }
    check *= -1;
}

/* 상품 추가 */
function addItem(){
    index++;
    let text = "";
    text += `<div class="area_tit holder" style="margin-top: 25px;">`;
    text += `<span class="option_title inline-blocked" style="margin-bottom: 7px">` + $dropdownItem.children().eq(0).text() + `</span>`;
    text += `</div>`;
    text += `<div class="area_count holder">`;
    text += `<div class="option_btn_wrap" style="top:0;">`;
    text += `<div class="option_btn_tools" style="float: none;">`;
    text += `<a href="javascript: minusItemCount(` + index +`);">`;
    text += `<i class="btl bt-minus" aria-hidden="true"></i>`;
    text += `<span class="sr-only">minus</span>`;
    text += `</a>`;
    text += `<input type="text" title="number" value="1" class="form-control _order_count_mobile ` + index + `">`;
    text += `<a href="javascript: plusItemCount(` + index +`);">`;
    text += `<i class="btl bt-plus" aria-hidden="true"></i>`;
    text += `<span class="sr-only">plus</span>`;
    text += `</a>`;
    text += `</div>`;
    text += `<div class="area_price absolute absolute_right absolute_middle">`;
    text += `<span class="item_price ` + index + `">` + $dropdownItem.children().eq(1).text() +`</span>`;
    text += `</div>`;
    text += `</div>`;
    text += `</div>`;
    $itemBox.append(text);
    $dropdownMenu.css('display', 'none');
    totalItemCount();
}

/* 상품 개수 빼기 */
function minusItemCount(index){
    let identifier = ".form-control._order_count_mobile." + index;
    let $itemCount = $(identifier);
    if($itemCount.val() == 1){
        alert("1개 미만은 주문할 수 없습니다.");
        return;
    }else {
        $itemCount.val(parseInt($itemCount.val()) - 1);
        totalItemCount();
    }
}

/* 상품 개수 추가 */
function plusItemCount(index){
    let identifier = ".form-control._order_count_mobile." + index;
    let $itemCount = $(identifier);
    $itemCount.val(parseInt($itemCount.val()) + 1);
    totalItemCount();
}

/* 상품 개수 변경 이벤트 */

$(".goods_payment").on('change', '.form-control._order_count_mobile', function(){
    totalItemCount();
});

$(".form-control._order_count_mobile").on('change', function(){
    totalItemCount();
});

/* 총 상품 개수 */
function totalItemCount(){
    let text = "";
    let total = 0;
    text += "총 상품금액(";
    for(var i = index; i >= 0; i--) {
        let identifier = ".form-control._order_count_mobile." + i;
        let $itemCount = $(identifier);
        total += parseInt($itemCount.val());
    }
    text += total;
    text += "개)";
    $totalItemCount.html(text);
    totalPrice();
}

/* 총 상품 금액 */
function totalPrice(){
    let text = "";
    let total = 0;
    for(var i = index; i >= 0; i--) {
        let itemIdentifier = ".item_price." + i;
        let $itemPrice = $(itemIdentifier).text().split('원')[0].replace(',', '');
        let countIdentifier = ".form-control._order_count_mobile." + i;
        let $itemCount = $(countIdentifier);
        total += (parseInt($itemCount.val()) * parseInt($itemPrice));
    }
    text += total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    text += "원";
    $totalPrice.html(text);
}

/* 구매하지 않은 제품일 경우 구매평 작성 불가 모달 띄우기 */
function openReviewModal(){
    if(false){
        $(".swal2-container").css('display', 'block');
        return;
    }
    openReview();

}

/* 구매평 작성 불가 모달 닫기 */
function closeReviewModal(){
    $(".swal2-container").css('display', 'none');
}

/* 구매평 작성 클릭 시 리뷰 작성 영역 나오게 하기 */
function openReview(){
    let $status = $(".comment_textarea").css('display');
    if($status == 'none'){
        $(".comment_textarea").css('display', 'block');
        $(".star-rate-wrapper").css('display', 'block');
        $(".phogo_review_btn").css('display', 'none');
        $(".review-btn").text("구매평 닫기");
    }else {
        $(".comment_textarea").css('display', 'none');
        $(".star-rate-wrapper").css('display', 'none');
        $(".phogo_review_btn").css('display', 'block');
        $(".review-btn").text("구매평 작성");
    }
}

/* 리뷰 별점 */
$(".starRate").on('click', function(){
    let $starRate = $(this).prop('classList')[1];
    let text = "";
    $(".starRate").attr('fill', 'rgb(225,225,225)');
    if($starRate == '1') { text = "아쉬워요"; }
    else if($starRate == '2') { text = "그저 그래요"; }
    else if($starRate == '3') { text = "괜찮았어요"; }
    else if($starRate == '4') { text = "좋았어요"; }
    else if($starRate == '5') { text = "최고예요"; }

    for(let i = 0; i < parseInt($starRate); i++){
        $(".starRate." + (i + 1)).attr('fill', 'rgb(255,206,33)');
    }
    $(".score-text").css('display', 'block');
    $(".score-text").html(text);

});

/* 댓글 사진 첨부 시 div 생성 */
$file.on('change', function (e) {
    var reader = new FileReader();
    let type = e.target.files[0].type;

    reader.readAsDataURL(e.target.files[0]);

    reader.onload = function (e) {
        let url = e.target.result;
        let text = "";

        if (url.includes('image')) {
            text += `<div data-v-60f50a1e="" class="image-preview">`;
            text += `<img data-v-60f50a1e="" src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTYiIGhlaWdodD0iMTYiIHZpZXdCb3g9IjAgMCAxNiAxNiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Ik01LjM2NCA0LjIzNGEuNzk4Ljc5OCAwIDEgMC0xLjEzIDEuMTNMNi44NyA4LjAwMWwtMi42MzcgMi42MzZhLjguOCAwIDAgMCAxLjEzIDEuMTI5TDggOS4xM2wyLjYzNiAyLjYzNWEuNzk4Ljc5OCAwIDEgMCAxLjEzLTEuMTNMOS4xMyA4LjAwMmwyLjYzNy0yLjYzN2EuOC44IDAgMCAwLTEuMTMtMS4xMjlMOCA2Ljg3IDUuMzY0IDQuMjM0eiIgZmlsbD0iI0ZGRiIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=" class="delete-badge">`;
            text += `<img data-v-60f50a1e="" src="` + url + `" alt="미리보기 이미지" class="image">`;
            text += `</div>`;

            $(".textarea_block").append(text);
            $file.attr('disabled', 'true');
            $(".attach-image-icon").css('cursor', 'default');
            $(".attach-image-icon").attr('src', 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Im0xMi43NSAyLjUgMS42NzggMS43NmgyLjkwNWMxLjAwOSAwIDEuODM0Ljc5IDEuODM0IDEuNzU5djEwLjU1NWMwIC45NjgtLjgyNSAxLjc2LTEuODM0IDEuNzZIMi42NjdjLTEuMDA5IDAtMS44MzQtLjc5Mi0xLjgzNC0xLjc2VjYuMDJjMC0uOTY4LjgyNS0xLjc2IDEuODM0LTEuNzZoMi45MDVMNy4yNSAyLjVoNS41ek0xMCA4LjE1NWMtMS44OTggMC0zLjQzOCAxLjUyLTMuNDM4IDMuMzkzIDAgMS44NzIgMS41NCAzLjM5MiAzLjQzOCAzLjM5MiAxLjg5OCAwIDMuNDM4LTEuNTIgMy40MzgtMy4zOTIgMC0xLjg3My0xLjU0LTMuMzkzLTMuNDM4LTMuMzkzeiIgZmlsbD0iI0M1QzVDNSIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=');
        } else {
            alert("사진파일만 업로드 가능합니다.");

            return;
        }
    }
});

/* 첨부파일 x 클릭 시 div 삭제 */
$(".textarea_block").on('click', '.delete-badge', function(){
    $file.removeAttr('disabled');
    $(".attach-image-icon").css('cursor', 'pointer');
    $(".attach-image-icon").attr('src', 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Im0xMi43NSAyLjUgMS42NzggMS43NmgyLjkwNWMxLjAwOSAwIDEuODM0Ljc5IDEuODM0IDEuNzU5djEwLjU1NWMwIC45NjgtLjgyNSAxLjc2LTEuODM0IDEuNzZIMi42NjdjLTEuMDA5IDAtMS44MzQtLjc5Mi0xLjgzNC0xLjc2VjYuMDJjMC0uOTY4LjgyNS0xLjc2IDEuODM0LTEuNzZoMi45MDVMNy4yNSAyLjVoNS41ek0xMCA4LjE1NWMtMS44OTggMC0zLjQzOCAxLjUyLTMuNDM4IDMuMzkzIDAgMS44NzIgMS41NCAzLjM5MiAzLjQzOCAzLjM5MiAxLjg5OCAwIDMuNDM4LTEuNTIgMy40MzgtMy4zOTIgMC0xLjg3My0xLjU0LTMuMzkzLTMuNDM4LTMuMzkzeiIgZmlsbD0iIzJEMkQyRCIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=');
    $(".image-preview").remove();
});

/* 포토리뷰 클릭 시 사진 확대하기 */
$(".thumb_detail_img_wrap").on('click', function(){
    let $flag = $(this).find('img').css('width');
    $flag = $flag == '80px'? '350px' : '80px';

    $(this).find('img').eq(0).css({'width':  $flag, 'height':  $flag});
});

