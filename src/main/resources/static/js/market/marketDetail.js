/* marketDetail.html */
// let $purchaseBtn = $(".purchase-btn");

/* 세션에 있는 멤버 아이디 */
const memberNumber = $("input[name='memberId']").val();

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

/* 리뷰 별점 */
globalThis.starRateTemp = 0;
/* 페이지 */
globalThis.page = 1;

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

/* ================================== MarketDetail ==================================*/

let url = decodeURI(window.location.href).split("/");
let productId = url[url.length - 1];
console.log(productId);


readProduct(productId);

/* 상품 상세 조회 */
function readProduct(productId) {
    marketService.getProductDetail(
        productId, showProductDetail
    );
}


/* 상품 상세 조회 */
function showProductDetail(product){
    // let text = "";

    $(".productType").html(product.productCategory);
    $(".proName").html(product.productName);
    $(".real_price").html(product.productPrice);
    $(".item_price").html(product.productPrice);
    $(".total_price").html(product.productPrice);
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

    globalThis.starRateTemp = $starRate;
    console.log($starRate); /* 작성자가 누른 별점 */

});

/* 댓글 사진 첨부 시 div 생성 */
/*$file.on('change', function (e) {
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
});*/

/* 첨부파일 x 클릭 시 div 삭제 */
$(".textarea_block").on('click', '.delete-badge', function(){
    $file.removeAttr('disabled');
    $(".attach-image-icon").css('cursor', 'pointer');
    $(".attach-image-icon").attr('src', 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Im0xMi43NSAyLjUgMS42NzggMS43NmgyLjkwNWMxLjAwOSAwIDEuODM0Ljc5IDEuODM0IDEuNzU5djEwLjU1NWMwIC45NjgtLjgyNSAxLjc2LTEuODM0IDEuNzZIMi42NjdjLTEuMDA5IDAtMS44MzQtLjc5Mi0xLjgzNC0xLjc2VjYuMDJjMC0uOTY4LjgyNS0xLjc2IDEuODM0LTEuNzZoMi45MDVMNy4yNSAyLjVoNS41ek0xMCA4LjE1NWMtMS44OTggMC0zLjQzOCAxLjUyLTMuNDM4IDMuMzkzIDAgMS44NzIgMS41NCAzLjM5MiAzLjQzOCAzLjM5MiAxLjg5OCAwIDMuNDM4LTEuNTIgMy40MzgtMy4zOTIgMC0xLjg3My0xLjU0LTMuMzkzLTMuNDM4LTMuMzkzeiIgZmlsbD0iIzJEMkQyRCIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=');
    $(".image-preview").remove();
});

/* 포토리뷰 클릭 시 사진 확대하기 */
$(".thumb_detail_img_wrap").on('click', function(){
    console.log("포토사진 클릭");
    let $flag = $(this).find('img').css('width');
    $flag = $flag == '80px'? '350px' : '80px';

    $(this).find('img').eq(0).css({'width':  $flag, 'height':  $flag});
});



/* ================================== Review ==================================*/
let reviewCount = 0;
let realReviewCount = 0;
let $reviewFileForm = $("input[name='uploadFile']");
let $reviewContent = $("textarea[name='review-input']");
let $reviewStar = $("path.starRate");
let $reviewSubmitBtn = $(".write-review-submit");
let $reviewPhotoBtn = $(".photo_button");

/* 주문하기 버튼 누를 시 */
let $purchaseBtn = $(".purchase-btn");

$purchaseBtn.on("click", function() {
    console.log("주문하기 버튼 누름");
    let $productCount = $("input[name='orderCount']").val();
    location.href ='/market/payment?productId='+ productId + '&count='+ $productCount;
});

/* 장바구니 버튼 누를 시 */
let $basketBtn = $(".cart-btn");

$basketBtn.on("click", function(){
    console.log("장바구니 버튼 누름");
    let $productCount = $("input[name='orderCount']").val();
    location.href ='/market/basket?productId='+ productId + '&count='+ $productCount;

})

showReview();

/* 리뷰 목록 보기 */
function showReview() {
    reviewService.getReviewList(
        productId, showReviewList
    )
}

/* 리뷰 더보기 눌렀을 시 */
function clickMoreReviewBtn(){
    if(globalThis.page != 0) {
        $(".comment-more-btn").remove();
    }

    reviewService.getMoreReview(
        productId, globalThis.page, showReviewMore
    );
    globalThis.page++;
}

/* 사진 리뷰만 보기 눌렀을 시 */
$reviewPhotoBtn.on("click", function() {
    console.log("포토 리뷰만 버튼 누름");
    showPhoto();
});

function showPhoto() {
    reviewService.getPhotoReview(productId,showPhotoReview);
}

/* 리뷰 첨부파일 등록 시 upload 저장 */
$reviewFileForm.on('change', function () {
    let file = $reviewFileForm[0].files[0];
    reviewService.uploadReviewFile(
        file, afterUploadReviewFile
    );
});

console.log($reviewSubmitBtn);

/* 리뷰 작성 */
$reviewSubmitBtn.on("click", function(){
    console.log("리뷰 작성 버튼 누름");
    saveReview();
})

function saveReview(){
    if(!$reviewContent.val() || !globalThis.starRateTemp){
        alert("리뷰를 다 입력하세요");
        $reviewSubmitBtn.removeClass("active");
        return;
    }
    reviewService.save({
        productId: productId,
        memberId: 1,
        reviewContent : $reviewContent.val(),
        reviewStar: globalThis.starRateTemp,
        reviewFileName: $("input[name='reviewFileName']").val()
    }, function(){
        $reviewContent.val('');
        $reviewStar.val("");
        $("input[name='reviewFileName']").val('');
        $reviewSubmitBtn.removeClass("active");
        console.log("등록 성공")
        showReview();
    });
}


function showReviewList(reviews) {
    let text = "";

    reviews.forEach(review => {
        text += `<div class="tabled full-width">`;
        text += `<div class="table-cell vertical-top txt_wrap">`;
        text += `<div class="interlock_star_point inline-blocked">`;
        text += `<div class="star_point_wrap">`;
        text += `<a class="bts bt-star active"></a>`;
        text += `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 102 102" width="15" height="15" preserveAspectRatio="xMidYMid meet" style="width: 100%; height: 100%; transform: translate3d(0px, 0px, 0px);">`;
        text += `<defs>`;
        text += `<clipPath id="__lottie_element_2">`;
        text += `<rect width="102" height="102" x="0" y="0"></rect>`;
        text += `</clipPath>`;
        text += `</defs>`;
        text += `<g clip-path="url(#__lottie_element_2)">`;
        text += `<g transform="matrix(1,0,0,1,51,50.42300033569336)" opacity="1" style="display: block;">`;
        text += `<g opacity="1" transform="matrix(3,0,0,3,0,0)">`;
        text += `<path fill="rgb(255,206,33)" fill-opacity="1" d=" M2.369999885559082,-12.887999534606934 C2.369999885559082,-12.887999534606934 5.730000019073486,-6.3480000495910645 5.730000019073486,-6.3480000495910645 C5.730000019073486,-6.3480000495910645 13.229999542236328,-5.297999858856201 13.229999542236328,-5.297999858856201 C15.399999618530273,-4.998000144958496 16.270000457763672,-2.427999973297119 14.699999809265137,-0.9580000042915344 C14.699999809265137,-0.9580000042915344 9.270000457763672,4.131999969482422 9.270000457763672,4.131999969482422 C9.270000457763672,4.131999969482422 10.550000190734863,11.321999549865723 10.550000190734863,11.321999549865723 C10.920000076293945,13.402000427246094 8.649999618530273,14.991999626159668 6.710000038146973,14.01200008392334 C6.710000038146973,14.01200008392334 0,10.612000465393066 0,10.612000465393066 C0,10.612000465393066 -6.710000038146973,14.01200008392334 -6.710000038146973,14.01200008392334 C-8.649999618530273,14.991999626159668 -10.920000076293945,13.402000427246094 -10.550000190734863,11.321999549865723 C-10.550000190734863,11.321999549865723 -9.270000457763672,4.131999969482422 -9.270000457763672,4.131999969482422 C-9.270000457763672,4.131999969482422 -14.699999809265137,-0.9580000042915344 -14.699999809265137,-0.9580000042915344 C-16.270000457763672,-2.427999973297119 -15.399999618530273,-4.998000144958496 -13.229999542236328,-5.297999858856201 C-13.229999542236328,-5.297999858856201 -5.730000019073486,-6.3480000495910645 -5.730000019073486,-6.3480000495910645 C-5.730000019073486,-6.3480000495910645 -2.369999885559082,-12.887999534606934 -2.369999885559082,-12.887999534606934 C-1.399999976158142,-14.777999877929688 1.399999976158142,-14.777999877929688 2.369999885559082,-12.887999534606934"></path>`;
        text += `</g>`;
        text += `</g>`;
        text += `</g>`;
        text += `</svg>`;
        text += `</div>`;
        text += `</div>`;
        text += `<div class="text-13 margin-bottom-xl body_font_color_70">`;
        text += `<span class="prod_option"></span>`;
        text += `</div>`;
        text += `<div class="text-14">`;
        text += `<span class="txt _txt _review_body _block_ _review_code_c2022112817dddb73ae187" block-review-code="c2022112817dddb73ae187" block="false">`;
        text += `<div class="dummy _dummy"><br><br><br><br><br><br><br></div>`;
        text +=  review.reviewContent + `<br>`;
        text += `</span>`;
        text += `<span class="_block_review_command_" style="display: block"></span>`;
        text += `</div>`;
        text += `<div class="clearfix thumb_img_wrap margin-top-xxl _review_img _block_">`;
        text += `<div class="blocked float_l thumb-wrap">`;
        text += `<div class="board_thumb" style="background: url(https://cdn.imweb.me/thumbnail/20220826/944ecbb77f63c.jpg) center center no-repeat; background-size: cover">`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
        text += `<div class="thumb_detail_img_wrap margin-top-xl margin-bottom-xxxl _review_img _block_">`;
        text += review.reviewFileName != null ? `<img src="` + review.reviewFileName + `" style="max-width: 640px; margin-top: 10px;" alt="2">`: '';
        text += `</div>`;
        text += `<span class="_block_review_command_" style="display: block">`;
        text += `<div class="review_comment_wrap_c202208031f8f652f3b1f7">`;
        text += `</div>`;
        text += `</span>`;
        text += `</div>`;
        text += `<div class="table-cell vertical-top width-5 text-13 use_summary">`;
        text += `<div>` + review.memberName + `</div>`;
        text += `<div>` + reviewService.timeForToday(review.createdDate)+ `</div>`;
        text += `<div class="margin-top-xxl text-12 review_tools clearfix">`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
    });

    if(reviews.length >= 5){
        text += `<button onclick="javascript:clickMoreReviewBtn();" class="comment-more-btn" style="width: 100%; background: transparent; border: none; font-size: 12px;">더보기</button>`;
    }

    $(".list_review_inner").html(text);
    $file.removeAttr('disabled');
    $(".attach-image-icon").css('cursor', 'pointer');
    $(".attach-image-icon").attr('src', 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Im0xMi43NSAyLjUgMS42NzggMS43NmgyLjkwNWMxLjAwOSAwIDEuODM0Ljc5IDEuODM0IDEuNzU5djEwLjU1NWMwIC45NjgtLjgyNSAxLjc2LTEuODM0IDEuNzZIMi42NjdjLTEuMDA5IDAtMS44MzQtLjc5Mi0xLjgzNC0xLjc2VjYuMDJjMC0uOTY4LjgyNS0xLjc2IDEuODM0LTEuNzZoMi45MDVMNy4yNSAyLjVoNS41ek0xMCA4LjE1NWMtMS44OTggMC0zLjQzOCAxLjUyLTMuNDM4IDMuMzkzIDAgMS44NzIgMS41NCAzLjM5MiAzLjQzOCAzLjM5MiAxLjg5OCAwIDMuNDM4LTEuNTIgMy40MzgtMy4zOTIgMC0xLjg3My0xLjU0LTMuMzkzLTMuNDM4LTMuMzkzeiIgZmlsbD0iIzJEMkQyRCIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=');

    reviewService.getReviewStarRate(
        productId, reviewCallback
    )
}

/* 별점 평균 */
let total = 0;
let avg = 0;
let totalOne = 0;
let totalTwo = 0;
let totalThree = 0;
let totalFour = 0;
let totalFive = 0;

function reviewCallback(reviews){
    reviews.forEach(review => {
        total += review.reviewStar

        if(review.reviewStar == 5){
            totalFive++;
        }else if(review.reviewStar == 4){
            totalFour++;
        }else if(review.reviewStar == 3){
            totalThree++;
        }else if(review.reviewStar == 2){
            totalTwo++;
        }else{
            totalOne++;
        }
    })
    avg = total / reviews.length;
    $(".rating_point").html(avg.toFixed(1));
    $(".review_count").html("총" + reviews.length + "개의 구매평");
    $(".five_bar").css('width', (totalFive / total * 100) + "%");
    $(".four_bar").css('width', (totalFour / total * 100) + "%");
    $(".three_bar").css('width', (totalThree / total * 100) + "%");
    $(".two_bar").css('width', (totalTwo / total * 100) + "%");
    $(".one_bar").css('width', (totalOne / total * 100) + "%");
    console.log("별점 최대값");
    console.log(Math.max(totalOne, totalTwo, totalThree, totalFour, totalFive));
    (Math.max(totalOne, totalTwo, totalThree, totalFour, totalFive).eq($(".rating_bar").addClass("active")));


    total = 0;
}
/* 더보기 */
function showReviewMore(reviews) {
    let text ="";

    reviews.forEach(review => {
        text += `<div class="tabled full-width">`;
        text += `<div class="table-cell vertical-top txt_wrap">`;
        text += `<div class="interlock_star_point inline-blocked">`;
        text += `<div class="star_point_wrap">`;
        text += `<a class="bts bt-star active"></a>`;
        text += `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 102 102" width="15" height="15" preserveAspectRatio="xMidYMid meet" style="width: 100%; height: 100%; transform: translate3d(0px, 0px, 0px);">`;
        text += `<defs>`;
        text += `<clipPath id="__lottie_element_2">`;
        text += `<rect width="102" height="102" x="0" y="0"></rect>`;
        text += `</clipPath>`;
        text += `</defs>`;
        text += `<g clip-path="url(#__lottie_element_2)">`;
        text += `<g transform="matrix(1,0,0,1,51,50.42300033569336)" opacity="1" style="display: block;">`;
        text += `<g opacity="1" transform="matrix(3,0,0,3,0,0)">`;
        text += `<path fill="rgb(255,206,33)" fill-opacity="1" d=" M2.369999885559082,-12.887999534606934 C2.369999885559082,-12.887999534606934 5.730000019073486,-6.3480000495910645 5.730000019073486,-6.3480000495910645 C5.730000019073486,-6.3480000495910645 13.229999542236328,-5.297999858856201 13.229999542236328,-5.297999858856201 C15.399999618530273,-4.998000144958496 16.270000457763672,-2.427999973297119 14.699999809265137,-0.9580000042915344 C14.699999809265137,-0.9580000042915344 9.270000457763672,4.131999969482422 9.270000457763672,4.131999969482422 C9.270000457763672,4.131999969482422 10.550000190734863,11.321999549865723 10.550000190734863,11.321999549865723 C10.920000076293945,13.402000427246094 8.649999618530273,14.991999626159668 6.710000038146973,14.01200008392334 C6.710000038146973,14.01200008392334 0,10.612000465393066 0,10.612000465393066 C0,10.612000465393066 -6.710000038146973,14.01200008392334 -6.710000038146973,14.01200008392334 C-8.649999618530273,14.991999626159668 -10.920000076293945,13.402000427246094 -10.550000190734863,11.321999549865723 C-10.550000190734863,11.321999549865723 -9.270000457763672,4.131999969482422 -9.270000457763672,4.131999969482422 C-9.270000457763672,4.131999969482422 -14.699999809265137,-0.9580000042915344 -14.699999809265137,-0.9580000042915344 C-16.270000457763672,-2.427999973297119 -15.399999618530273,-4.998000144958496 -13.229999542236328,-5.297999858856201 C-13.229999542236328,-5.297999858856201 -5.730000019073486,-6.3480000495910645 -5.730000019073486,-6.3480000495910645 C-5.730000019073486,-6.3480000495910645 -2.369999885559082,-12.887999534606934 -2.369999885559082,-12.887999534606934 C-1.399999976158142,-14.777999877929688 1.399999976158142,-14.777999877929688 2.369999885559082,-12.887999534606934"></path>`;
        text += `</g>`;
        text += `</g>`;
        text += `</g>`;
        text += `</svg>`;
        text += `</div>`;
        text += `</div>`;
        text += `<div class="text-13 margin-bottom-xl body_font_color_70">`;
        text += `<span class="prod_option"></span>`;
        text += `</div>`;
        text += `<div class="text-14">`;
        text += `<span class="txt _txt _review_body _block_ _review_code_c2022112817dddb73ae187" block-review-code="c2022112817dddb73ae187" block="false">`;
        text += `<div class="dummy _dummy"><br><br><br><br><br><br><br></div>`;
        text +=  review.reviewContent + `<br>`;
        text += `</span>`;
        text += `<span class="_block_review_command_" style="display: block"></span>`;
        text += `</div>`;
        text += `<div class="clearfix thumb_img_wrap margin-top-xxl _review_img _block_">`;
        text += `<div class="blocked float_l thumb-wrap">`;
        text += `<div class="board_thumb" style="background: url(https://cdn.imweb.me/thumbnail/20220826/944ecbb77f63c.jpg) center center no-repeat; background-size: cover">`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
        text += `<div class="thumb_detail_img_wrap margin-top-xl margin-bottom-xxxl _review_img _block_">`;
        text += review.reviewFileName != null ? `<img src="` + review.reviewFileName + `" style="max-width: 640px; margin-top: 10px;" alt="2">`: '';
        text += `</div>`;
        text += `<span class="_block_review_command_" style="display: block">`;
        text += `<div class="review_comment_wrap_c202208031f8f652f3b1f7">`;
        text += `</div>`;
        text += `</span>`;
        text += `</div>`;
        text += `<div class="table-cell vertical-top width-5 text-13 use_summary">`;
        text += `<div>` + review.memberName + `</div>`;
        text += `<div>` + reviewService.timeForToday(review.createdDate)+ `</div>`;
        text += `<div class="margin-top-xxl text-12 review_tools clearfix">`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
    });

    if(reviews.length >= 5){
        text += `<button onclick="javascript:clickMoreReviewBtn();" class="comment-more-btn" style="width: 100%; background: transparent; border: none; font-size: 12px;">더보기</button>`;
    }
    $(".list_review_inner").append(text);
    $file.removeAttr('disabled');
    $(".attach-image-icon").css('cursor', 'pointer');
    $(".attach-image-icon").attr('src', 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Im0xMi43NSAyLjUgMS42NzggMS43NmgyLjkwNWMxLjAwOSAwIDEuODM0Ljc5IDEuODM0IDEuNzU5djEwLjU1NWMwIC45NjgtLjgyNSAxLjc2LTEuODM0IDEuNzZIMi42NjdjLTEuMDA5IDAtMS44MzQtLjc5Mi0xLjgzNC0xLjc2VjYuMDJjMC0uOTY4LjgyNS0xLjc2IDEuODM0LTEuNzZoMi45MDVMNy4yNSAyLjVoNS41ek0xMCA4LjE1NWMtMS44OTggMC0zLjQzOCAxLjUyLTMuNDM4IDMuMzkzIDAgMS44NzIgMS41NCAzLjM5MiAzLjQzOCAzLjM5MiAxLjg5OCAwIDMuNDM4LTEuNTIgMy40MzgtMy4zOTIgMC0xLjg3My0xLjU0LTMuMzkzLTMuNDM4LTMuMzkzeiIgZmlsbD0iIzJEMkQyRCIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=');
}

/* 포토 리뷰만 */
function showPhotoReview(reviews) {
    let text ="";

    reviews.forEach(review => {
        text += `<div class="tabled full-width">`;
        text += `<div class="table-cell vertical-top txt_wrap">`;
        text += `<div class="interlock_star_point inline-blocked">`;
        text += `<div class="star_point_wrap">`;
        text += `<a class="bts bt-star active"></a>`;
        text += `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 102 102" width="15" height="15" preserveAspectRatio="xMidYMid meet" style="width: 100%; height: 100%; transform: translate3d(0px, 0px, 0px);">`;
        text += `<defs>`;
        text += `<clipPath id="__lottie_element_2">`;
        text += `<rect width="102" height="102" x="0" y="0"></rect>`;
        text += `</clipPath>`;
        text += `</defs>`;
        text += `<g clip-path="url(#__lottie_element_2)">`;
        text += `<g transform="matrix(1,0,0,1,51,50.42300033569336)" opacity="1" style="display: block;">`;
        text += `<g opacity="1" transform="matrix(3,0,0,3,0,0)">`;
        text += `<path fill="rgb(255,206,33)" fill-opacity="1" d=" M2.369999885559082,-12.887999534606934 C2.369999885559082,-12.887999534606934 5.730000019073486,-6.3480000495910645 5.730000019073486,-6.3480000495910645 C5.730000019073486,-6.3480000495910645 13.229999542236328,-5.297999858856201 13.229999542236328,-5.297999858856201 C15.399999618530273,-4.998000144958496 16.270000457763672,-2.427999973297119 14.699999809265137,-0.9580000042915344 C14.699999809265137,-0.9580000042915344 9.270000457763672,4.131999969482422 9.270000457763672,4.131999969482422 C9.270000457763672,4.131999969482422 10.550000190734863,11.321999549865723 10.550000190734863,11.321999549865723 C10.920000076293945,13.402000427246094 8.649999618530273,14.991999626159668 6.710000038146973,14.01200008392334 C6.710000038146973,14.01200008392334 0,10.612000465393066 0,10.612000465393066 C0,10.612000465393066 -6.710000038146973,14.01200008392334 -6.710000038146973,14.01200008392334 C-8.649999618530273,14.991999626159668 -10.920000076293945,13.402000427246094 -10.550000190734863,11.321999549865723 C-10.550000190734863,11.321999549865723 -9.270000457763672,4.131999969482422 -9.270000457763672,4.131999969482422 C-9.270000457763672,4.131999969482422 -14.699999809265137,-0.9580000042915344 -14.699999809265137,-0.9580000042915344 C-16.270000457763672,-2.427999973297119 -15.399999618530273,-4.998000144958496 -13.229999542236328,-5.297999858856201 C-13.229999542236328,-5.297999858856201 -5.730000019073486,-6.3480000495910645 -5.730000019073486,-6.3480000495910645 C-5.730000019073486,-6.3480000495910645 -2.369999885559082,-12.887999534606934 -2.369999885559082,-12.887999534606934 C-1.399999976158142,-14.777999877929688 1.399999976158142,-14.777999877929688 2.369999885559082,-12.887999534606934"></path>`;
        text += `</g>`;
        text += `</g>`;
        text += `</g>`;
        text += `</svg>`;
        text += `</div>`;
        text += `</div>`;
        text += `<div class="text-13 margin-bottom-xl body_font_color_70">`;
        text += `<span class="prod_option"></span>`;
        text += `</div>`;
        text += `<div class="text-14">`;
        text += `<span class="txt _txt _review_body _block_ _review_code_c2022112817dddb73ae187" block-review-code="c2022112817dddb73ae187" block="false">`;
        text += `<div class="dummy _dummy"><br><br><br><br><br><br><br></div>`;
        text +=  review.reviewContent + `<br>`;
        text += `</span>`;
        text += `<span class="_block_review_command_" style="display: block"></span>`;
        text += `</div>`;
        text += `<div class="clearfix thumb_img_wrap margin-top-xxl _review_img _block_">`;
        text += `<div class="blocked float_l thumb-wrap">`;
        text += `<div class="board_thumb" style="background: url(https://cdn.imweb.me/thumbnail/20220826/944ecbb77f63c.jpg) center center no-repeat; background-size: cover">`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
        text += `<div class="thumb_detail_img_wrap margin-top-xl margin-bottom-xxxl _review_img _block_">`;
        text += review.reviewFileName != null ? `<img src="` + review.reviewFileName + `" style="max-width: 640px; margin-top: 10px;" alt="2">`: '';
        text += `</div>`;
        text += `<span class="_block_review_command_" style="display: block">`;
        text += `<div class="review_comment_wrap_c202208031f8f652f3b1f7">`;
        text += `</div>`;
        text += `</span>`;
        text += `</div>`;
        text += `<div class="table-cell vertical-top width-5 text-13 use_summary">`;
        text += `<div>` + review.memberName + `</div>`;
        text += `<div>` + reviewService.timeForToday(review.createdDate)+ `</div>`;
        text += `<div class="margin-top-xxl text-12 review_tools clearfix">`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
    });

    if(reviews.length >= 5){
        text += `<button onclick="javascript:clickMoreReviewBtn();" class="comment-more-btn" style="width: 100%; background: transparent; border: none; font-size: 12px;">더보기</button>`;
    }
    $(".list_review_inner").append(text);
    $file.removeAttr('disabled');
    $(".attach-image-icon").css('cursor', 'pointer');
    $(".attach-image-icon").attr('src', 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Im0xMi43NSAyLjUgMS42NzggMS43NmgyLjkwNWMxLjAwOSAwIDEuODM0Ljc5IDEuODM0IDEuNzU5djEwLjU1NWMwIC45NjgtLjgyNSAxLjc2LTEuODM0IDEuNzZIMi42NjdjLTEuMDA5IDAtMS44MzQtLjc5Mi0xLjgzNC0xLjc2VjYuMDJjMC0uOTY4LjgyNS0xLjc2IDEuODM0LTEuNzZoMi45MDVMNy4yNSAyLjVoNS41ek0xMCA4LjE1NWMtMS44OTggMC0zLjQzOCAxLjUyLTMuNDM4IDMuMzkzIDAgMS44NzIgMS41NCAzLjM5MiAzLjQzOCAzLjM5MiAxLjg5OCAwIDMuNDM4LTEuNTIgMy40MzgtMy4zOTIgMC0xLjg3My0xLjU0LTMuMzkzLTMuNDM4LTMuMzkzeiIgZmlsbD0iIzJEMkQyRCIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=');
}

function afterUploadReviewFile(file) {
    let text = "";
    text += `<div data-v-60f50a1e="" class="image-preview after-upload">`;
    text += `<img data-v-60f50a1e="" src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTYiIGhlaWdodD0iMTYiIHZpZXdCb3g9IjAgMCAxNiAxNiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Ik01LjM2NCA0LjIzNGEuNzk4Ljc5OCAwIDEgMC0xLjEzIDEuMTNMNi44NyA4LjAwMWwtMi42MzcgMi42MzZhLjguOCAwIDAgMCAxLjEzIDEuMTI5TDggOS4xM2wyLjYzNiAyLjYzNWEuNzk4Ljc5OCAwIDEgMCAxLjEzLTEuMTNMOS4xMyA4LjAwMmwyLjYzNy0yLjYzN2EuOC44IDAgMCAwLTEuMTMtMS4xMjlMOCA2Ljg3IDUuMzY0IDQuMjM0eiIgZmlsbD0iI0ZGRiIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=" class="delete-badge">`;
    text += `<img data-v-60f50a1e="" src="` + file + `" class="image">`;
    text += `<input type="hidden" name="reviewFileName" value ="` + file + `">`;
    text += `</div>`;


    $(".textarea_block").append(text);
    $file.attr('disabled', 'true');
    $(".attach-image-icon").css('cursor', 'default');
    $(".attach-image-icon").attr('src', 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Im0xMi43NSAyLjUgMS42NzggMS43NmgyLjkwNWMxLjAwOSAwIDEuODM0Ljc5IDEuODM0IDEuNzU5djEwLjU1NWMwIC45NjgtLjgyNSAxLjc2LTEuODM0IDEuNzZIMi42NjdjLTEuMDA5IDAtMS44MzQtLjc5Mi0xLjgzNC0xLjc2VjYuMDJjMC0uOTY4LjgyNS0xLjc2IDEuODM0LTEuNzZoMi45MDVMNy4yNSAyLjVoNS41ek0xMCA4LjE1NWMtMS44OTggMC0zLjQzOCAxLjUyLTMuNDM4IDMuMzkzIDAgMS44NzIgMS41NCAzLjM5MiAzLjQzOCAzLjM5MiAxLjg5OCAwIDMuNDM4LTEuNTIgMy40MzgtMy4zOTIgMC0xLjg3My0xLjU0LTMuMzkzLTMuNDM4LTMuMzkzeiIgZmlsbD0iI0M1QzVDNSIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=');
}
