
let autoSlideWidth = 970;

const banner = document.querySelector("div.slick-track");
let count = 0;
let current = document.querySelector(".current-slide-no");
let firstDiv = document.createElement("div.banner-item");
let lastDiv = document.createElement("div.banner-item");
current.innerHTML = 1;

resizeWindow();

window.onresize = function(event){
    resizeWindow();
}

// window width 감지
function resizeWindow(){
    let $resize = $(window).width();
    $resize = $resize > 970 ? 970 : $resize;
    console.log($resize);
    $(".slick-slide.slide-banner").css('width', $resize);
    $(".slide-banner.banner-item").css('width', $resize);
    $(".banner-image").css('width', $resize);
    $(".first-slick-list").css('width', $resize * 9);

    autoSlideWidth = $resize;
}


// 자동 슬라이드
function autoSlide(){
    banner.style.transition = "transform 0.5s"
    count ++;
    if(count == 7){

        banner.style.transform = "translate(-" + autoSlideWidth * (count + 1) + "px)";
        setTimeout(function(){
            banner.style.transition = "transform 0s"
            banner.style.transform = "translate(-" + autoSlideWidth + "px)";
        }, 500);
        count = 0;
    }else{
        banner.style.transform = "translate(-" + autoSlideWidth * (count + 1) + "px)";
    }
    current.innerHTML = count + 1;
}
firstDiv.style.outline = 'none';
firstDiv.style.width = autoSlideWidth + 'px';
firstDiv.style.float = 'left';
firstDiv.style.height = '100%';
firstDiv.style.minHeight = '1px';
firstDiv.style.display = 'block';
firstDiv.innerHTML = `<img src="/images/main/slide1.png" alt="포트폴리오 전용 목록 진입 창구 배너" class="banner-image" data-v-e4caeaf8="" data-v-afb0bbd0="">`;
banner.appendChild(firstDiv);


lastDiv.style.outline = 'none';
lastDiv.style.width = autoSlideWidth + 'px';
lastDiv.style.float = 'left';
lastDiv.style.height = '100%';
lastDiv.style.minHeight = '1px';
lastDiv.style.display = 'block';
lastDiv.innerHTML = `<img src="/images/main/slide2.png" alt="이사 기획전" class="banner-image" data-v-e4caeaf8="" data-v-afb0bbd0="">`;
banner.insertBefore(lastDiv, document.querySelector("div.banner div"));

banner.style.transform = "translate(-" + autoSlideWidth + "px)";

var inter = setInterval(autoSlide, 3000);



//  첫번째 서브 배너 이전 버튼, 다음 버튼 구현(지금 바로 원하는 서비스를 받아보세요!)
const bannerOne = document.querySelector("div.slick-first");
const arrowOne = document.querySelectorAll("button.firstBtn");
const arrowNext = document.querySelector("button.firstBtn.firstNext");
count1 = 0;
var arrowButtonCheck = true;

arrowOne.forEach(arrow => {
    arrow.addEventListener("click", function(){
        if(arrowButtonCheck){
            arrowButtonCheck = false;
            clearInterval(inter);
            bannerOne.style.transition = "transform 0.5s"
            let arrowType = arrow.classList[1];
            if(arrowType == 'firstPrev'){
                count1--;
                if(count1 == 0){
                    bannerOne.style.transform = "translate(-" + 494 * count1 + "px)";
                    document.querySelector(".firstBtn.firstPrev").style.display = 'none';
                }else{
                    document.querySelector(".firstBtn.firstNext").style.display = 'block';
                    document.querySelector(".firstBtn.firstPrev").style.display = 'block';
                    bannerOne.style.transform = "translate(-" + 494 * count1 + "px)";
                }
                // nextBtn
            }else{
                count1++;
                console.log("next-part");
                console.log(count1);
                if(count1 == 2) {
                    bannerOne.style.transform = "translate(-" + 494 * count1 + "px)";
                    document.querySelector(".firstBtn.firstNext").style.display = 'none';
                }else{
                    document.querySelector(".firstBtn.firstNext").style.display = 'block';
                    document.querySelector(".firstBtn.firstPrev").style.display = 'block';
                    bannerOne.style.transform = "translate(-" + 494 * count1 + "px)";
                }
            }
            inter = setInterval(autoSlide, 4000);
            setTimeout(function(){
                arrowButtonCheck = true;
            }, 500);
        }
    });
});


//  두번째 서브 배너 이전 버튼, 다음 버튼 구현(숨고인기서비스)
const bannerTwo = document.querySelector("div.slick-second");
const arrowTwo = document.querySelectorAll("button.secondBtn");
count2 = 0;
var arrowButtonCheck = true;

arrowTwo.forEach(arrow => {
    arrow.addEventListener("click", function(){
        if(arrowButtonCheck){
            arrowButtonCheck = false;
            clearInterval(inter);
            bannerTwo.style.transition = "transform 0.5s"
            let arrowType = arrow.classList[1];
            if(arrowType == 'secondPrev'){
                count2--;
                if(count2 == 0){
                    bannerTwo.style.transform = "translate(-" + 494 * count2 + "px)";
                    document.querySelector(".secondBtn.secondPrev").style.display = 'none';
                }else{
                    document.querySelector(".secondBtn.secondNext").style.display = 'block';
                    document.querySelector(".secondBtn.secondPrev").style.display = 'block';
                    bannerTwo.style.transform = "translate(-" + 494 * count2 + "px)";
                }
                // nextBtn
            }else{
                count2++;
                if(count2 == 2) {
                    bannerTwo.style.transform = "translate(-" + 494 * count2 + "px)";
                    document.querySelector(".secondBtn.secondNext").style.display = 'none';
                }else{
                    document.querySelector(".secondBtn.secondNext").style.display = 'block';
                    document.querySelector(".secondBtn.secondPrev").style.display = 'block';
                    bannerTwo.style.transform = "translate(-" + 494 * count2 + "px)";
                }
            }
            inter = setInterval(autoSlide, 4000);
            setTimeout(function(){
                arrowButtonCheck = true;
            }, 500);
        }
    });
});


// 세번째 서브 배너 이전 버튼, 다음 버튼 구현(지금 인기있는 고수)
const bannerThree = document.querySelector("div.slick-three");
const arrowThree = document.querySelectorAll("button.threeBtn");
count3 = 0;
var arrowButtonCheck = true;

arrowThree.forEach(arrow => {
    arrow.addEventListener("click", function(){
        if(arrowButtonCheck){
            arrowButtonCheck = false;
            clearInterval(inter);
            bannerThree.style.transition = "transform 0.5s"
            let arrowType = arrow.classList[1];
            if(arrowType == 'threePrev'){
                count3--;
                if(count3 == 0){
                    bannerThree.style.transform = "translate(-" + 198 * count3 + "px)";
                    document.querySelector(".threeBtn.threePrev").style.display = 'none';
                }else{
                    document.querySelector(".threeBtn.threeNext").style.display = 'block';
                    document.querySelector(".threeBtn.threePrev").style.display = 'block';
                    bannerThree.style.transform = "translate(-" + 198 * count3 + "px)";
                }
                // nextBtn
            }else{
                count3++;
                if(count3 == 4) {
                    bannerThree.style.transform = "translate(-" + 198 * count3 + "px)";
                    document.querySelector(".threeBtn.threeNext").style.display = 'none';
                }else{
                    document.querySelector(".threeBtn.threePrev").style.display = 'block';
                    document.querySelector(".threeBtn.threeNext").style.display = 'block';
                    bannerThree.style.transform = "translate(-" + 198 * count3 + "px)";
                }
            }
            inter = setInterval(autoSlide, 4000);
            setTimeout(function(){
                arrowButtonCheck = true;
            }, 500);
        }
    });
});

// 네번째 서브 배너 이전 버튼, 다음 버튼 구현(쓱싹쓱싹 청소하는 날)
const bannerFour = document.querySelector("div.slick-four");
const arrowFour = document.querySelectorAll("button.fourBtn");
count4 = 0;
var arrowButtonCheck = true;

arrowFour.forEach(arrow => {
    arrow.addEventListener("click", function(){
        if(arrowButtonCheck){
            arrowButtonCheck = false;
            clearInterval(inter);
            bannerFour.style.transition = "transform 0.5s"
            let arrowType = arrow.classList[1];
            if(arrowType == 'fourPrev'){
                count4--;
                if(count4 == 0){
                    bannerFour.style.transform = "translate(-" + 494 * count4 + "px)";
                    document.querySelector(".fourBtn.fourPrev").style.display = 'none';
                    document.querySelector(".fourBtn.fourNext").style.display = 'block';
                }else{
                    document.querySelector(".fourBtn.fourPrev").style.display = 'block';
                    bannerTwo.style.transform = "translate(-" + 494 * count4 + "px)";
                }
                // nextBtn
            }else{
                count4++;
                if(count4 == 1) {
                    bannerFour.style.transform = "translate(-" + 494 * count4 + "px)";
                    document.querySelector(".fourBtn.fourNext").style.display = 'none';
                    document.querySelector(".fourBtn.fourPrev").style.display = 'block';
                }else{
                    document.querySelector(".fourBtn.fourNext").style.display = 'block';
                    bannerFour.style.transform = "translate(-" + 494 * count4 + "px)";
                }
            }
            inter = setInterval(autoSlide, 4000);
            setTimeout(function(){
                arrowButtonCheck = true;
            }, 500);
        }
    });
});

// 다섯번째 서브 배너 이전 버튼, 다음 버튼 구현(자동차를 부탁해)
const bannerFive = document.querySelector("div.slick-five");
const arrowFive = document.querySelectorAll("button.fiveBtn");
count5 = 0;
var arrowButtonCheck = true;

arrowFive.forEach(arrow => {
    arrow.addEventListener("click", function(){
        if(arrowButtonCheck){
            arrowButtonCheck = false;
            clearInterval(inter);
            bannerFive.style.transition = "transform 0.5s"
            let arrowType = arrow.classList[1];
            if(arrowType == 'fivePrev'){
                count5--;
                if(count5 == 0){
                    bannerFive.style.transform = "translate(-" + 494 * count5 + "px)";
                    document.querySelector(".fiveBtn.fivePrev").style.display = 'none';
                    document.querySelector(".fiveBtn.fiveNext").style.display = 'block';
                }else{
                    document.querySelector(".fiveBtn.fivePrev").style.display = 'block';
                    bannerFive.style.transform = "translate(-" + 494 * count5 + "px)";
                }
                // nextBtn
            }else{
                count5++;
                if(count5 == 1) {
                    bannerFive.style.transform = "translate(-" + 494 * count5 + "px)";
                    document.querySelector(".fiveBtn.fiveNext").style.display = 'none';
                    document.querySelector(".fiveBtn.fivePrev").style.display = 'block';
                }else{
                    document.querySelector(".fiveBtn.fiveNext").style.display = 'block';
                    bannerFive.style.transform = "translate(-" + 494 * count5 + "px)";
                }
            }
            inter = setInterval(autoSlide, 4000);
            setTimeout(function(){
                arrowButtonCheck = true;
            }, 500);
        }
    });
});

// 여섯번째 서브 배너 이전 버튼, 다음 버튼 구현(취미의 재발견)
const bannerSix = document.querySelector("div.slick-six");
const arrowSix = document.querySelectorAll("button.sixBtn");
count6 = 0;
var arrowButtonCheck = true;

arrowSix.forEach(arrow => {
    arrow.addEventListener("click", function(){
        if(arrowButtonCheck){
            arrowButtonCheck = false;
            clearInterval(inter);
            bannerSix.style.transition = "transform 0.5s"
            let arrowType = arrow.classList[1];
            if(arrowType == 'sixPrev'){
                count6--;
                if(count6 == 0){
                    bannerSix.style.transform = "translate(-" + 494 * count6 + "px)";
                    document.querySelector(".sixBtn.sixPrev").style.display = 'none';
                    document.querySelector(".sixBtn.sixNext").style.display = 'block';
                }else{
                    document.querySelector(".sixBtn.sixPrev").style.display = 'block';
                    bannerSix.style.transform = "translate(-" + 494 * count6 + "px)";
                }
                // nextBtn
            }else{
                count6++;
                if(count6 == 1) {
                    bannerSix.style.transform = "translate(-" + 494 * count6 + "px)";
                    document.querySelector(".sixBtn.sixNext").style.display = 'none';
                    document.querySelector(".sixBtn.sixPrev").style.display = 'block';
                }else{
                    document.querySelector(".sixBtn.sixNext").style.display = 'block';
                    bannerSix.style.transform = "translate(-" + 494 * count6 + "px)";
                }
            }
            inter = setInterval(autoSlide, 4000);
            setTimeout(function(){
                arrowButtonCheck = true;
            }, 500);
        }
    });
});

// 일곱번째 서브 배너 이전 버튼, 다음 버튼 구현(합격 준비는 지금부터 )
const bannerSeven = document.querySelector("div.slick-seven");
const arrowSeven = document.querySelectorAll("button.sevenBtn");
count7 = 0;
var arrowButtonCheck = true;

arrowSeven.forEach(arrow => {
    arrow.addEventListener("click", function(){
        if(arrowButtonCheck){
            arrowButtonCheck = false;
            clearInterval(inter);
            bannerSeven.style.transition = "transform 0.5s"
            let arrowType = arrow.classList[1];
            if(arrowType == 'sevenPrev'){
                count7--;
                if(count7 == 0){
                    bannerSeven.style.transform = "translate(-" + 494 * count7 + "px)";
                    document.querySelector(".sevenBtn.sevenPrev").style.display = 'none';
                    document.querySelector(".sevenBtn.sevenNext").style.display = 'block';
                }else{
                    document.querySelector(".sevenBtn.sevenPrev").style.display = 'block';
                    bannerSeven.style.transform = "translate(-" + 494 * count7 + "px)";
                }
                // nextBtn
            }else{
                count7++;
                if(count7 == 1) {
                    bannerSeven.style.transform = "translate(-" + 494 * count7 + "px)";
                    document.querySelector(".sevenBtn.sevenNext").style.display = 'none';
                    document.querySelector(".sevenBtn.sevenPrev").style.display = 'block';
                }else{
                    document.querySelector(".sevenBtn.sevenNext").style.display = 'block';
                    bannerSeven.style.transform = "translate(-" + 494 * count7 + "px)";
                }
            }
            inter = setInterval(autoSlide, 4000);
            setTimeout(function(){
                arrowButtonCheck = true;
            }, 500);
        }
    });
});

// 여덟번째 서브 배너 이전 버튼, 다음 버튼 구현(외국어 완전 정복)
const bannerEight = document.querySelector("div.slick-eight");
const arrowEight = document.querySelectorAll("button.eightBtn");
count8 = 0;
var arrowButtonCheck = true;

arrowEight.forEach(arrow => {
    arrow.addEventListener("click", function(){
        if(arrowButtonCheck){
            arrowButtonCheck = false;
            clearInterval(inter);
            bannerEight.style.transition = "transform 0.5s"
            let arrowType = arrow.classList[1];
            if(arrowType == 'eightPrev'){
                count8--;
                if(count8 == 0){
                    bannerEight.style.transform = "translate(-" + 494 * count8 + "px)";
                    document.querySelector(".eightBtn.eightPrev").style.display = 'none';
                    document.querySelector(".eightBtn.eightNext").style.display = 'block';
                }else{
                    document.querySelector(".eightBtn.eightPrev").style.display = 'block';
                    bannerEight.style.transform = "translate(-" + 494 * count8 + "px)";
                }
                // nextBtn
            }else{
                count8++;
                if(count8 == 1) {
                    bannerEight.style.transform = "translate(-" + 494 * count8 + "px)";
                    document.querySelector(".eightBtn.eightNext").style.display = 'none';
                    document.querySelector(".eightBtn.eightPrev").style.display = 'block';
                }else{
                    document.querySelector(".eightBtn.eightNext").style.display = 'block';
                    bannerEight.style.transform = "translate(-" + 494 * count8 + "px)";
                }
            }
            inter = setInterval(autoSlide, 4000);
            setTimeout(function(){
                arrowButtonCheck = true;
            }, 500);
        }
    });
});

// 아홉번째 서브배너 이전 버튼, 다음 버튼 구현(숨고 이야기)
const bannerNine = document.querySelector("div.slick-nine");
const arrowNine = document.querySelectorAll("button.nineBtn");
count9 = 0;
var arrowButtonCheck = true;

arrowNine.forEach(arrow => {
    arrow.addEventListener("click", function(){
        if(arrowButtonCheck){
            arrowButtonCheck = false;
            clearInterval(inter);
            bannerNine.style.transition = "transform 0.5s"
            let arrowType = arrow.classList[1];
            if(arrowType == 'ninePrev'){
                count9--;
                if(count9 == 0){
                    bannerNine.style.transform = "translate(-" + 329 * count9 + "px)";
                    document.querySelector(".nineBtn.ninePrev").style.display = 'none';
                }else{
                    document.querySelector(".nineBtn.nineNext").style.display = 'block';
                    document.querySelector(".nineBtn.ninePrev").style.display = 'block';
                    bannerNine.style.transform = "translate(-" + 329 * count9 + "px)";
                }
                // nextBtn
            }else{
                count9++;
                if(count9 == 3) {
                    bannerNine.style.transform = "translate(-" + 329 * count9 + "px)";
                    document.querySelector(".nineBtn.nineNext").style.display = 'none';
                }else{
                    document.querySelector(".nineBtn.ninePrev").style.display = 'block';
                    document.querySelector(".nineBtn.nineNext").style.display = 'block';
                    bannerNine.style.transform = "translate(-" + 329 * count9 + "px)";
                }
            }
            inter = setInterval(autoSlide, 4000);
            setTimeout(function(){
                arrowButtonCheck = true;
            }, 500);
        }
    });
});

// 열번째 서브배너 이전 버튼, 다음 버튼 구현(고수 가입)
const bannerTen = document.querySelector("div.slick-ten");
const arrowTen = document.querySelectorAll("button.tenBtn");
count10 = 0;
var arrowButtonCheck = true;

arrowTen.forEach(arrow => {
    arrow.addEventListener("click", function(){
        if(arrowButtonCheck){
            arrowButtonCheck = false;
            clearInterval(inter);
            bannerTen.style.transition = "transform 0.5s"
            let arrowType = arrow.classList[1];
            if(arrowType == 'tenPrev'){
                count10--;
                if(count10 == 0){
                    bannerTen.style.transform = "translate(-" + 641 * count10 + "px)";
                    document.querySelector(".tenBtn.tenPrev").style.display = 'none';
                }else{
                    document.querySelector(".tenBtn.tenNext").style.display = 'block';
                    document.querySelector(".tenBtn.tenPrev").style.display = 'block';
                    bannerTen.style.transform = "translate(-" + 641 * count10 + "px)";
                }
                // nextBtn
            }else{
                count10++;
                if(count10 == 2) {
                    bannerTen.style.transform = "translate(-" + 641 * count10 + "px)";
                    document.querySelector(".tenBtn.tenNext").style.display = 'none';
                }else{
                    document.querySelector(".tenBtn.tenNext").style.display = 'block';
                    document.querySelector(".tenBtn.tenPrev").style.display = 'block';
                    bannerTen.style.transform = "translate(-" + 641 * count10 + "px)";
                }
            }
            inter = setInterval(autoSlide, 4000);
            setTimeout(function(){
                arrowButtonCheck = true;
            }, 500);
        }
    });
});