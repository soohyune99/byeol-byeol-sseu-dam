$('.count').each(function() { 
  var $this = $(this),
      countTo = $this.attr('data-count');
       
  $({ countNum: $this.text()}).animate({
    countNum: countTo 
  },
  {
    duration: 2000, 
    easing:'linear',
    step: function() {
      $this.text(Math.floor(this.countNum));
    },
    complete: function() { 
      $this.text(this.countNum);
    }
  });  
});



const $li1 = $("#li-1");
const $li2 = $("#li-2");
const $li3 = $("#li-3");
const $li4 = $("#li-4");
const $li5 = $("#li-5");
const $nextli = $("#nextli")
const $sumit = $("#submit");


const progressBar = document.getElementById('progress-bar')
const progressPercent = document.getElementById('progress-percent')

function countCheck(){

    let petCount = document.getElementById("petCount")
    let glassCount = document.getElementById("glassCount")


    if (petCount.value == "" && glassCount.value ==""){
        alert("페트병과 유리병의 총 합 개수는 최소 10 이상 입니다");
        petCount.focus();
        return false;
    }else if (parseInt(petCount.value) + parseInt(glassCount.value) < 10){
        alert("페트병과 유리병의 총 합 개수는 최소 10 이상 입니다");
        petCount.focus();
        return false;
    }else if (petCount.value == "" && parseInt(glassCount.value) < 10){
        alert("페트병과 유리병의 총 합 개수는 최소 10 이상 입니다");
        petCount.focus();
        return false;
    }else if (parseInt(petCount.value) < 10 && glassCount.value == ""){
        alert("페트병과 유리병의 총 합 개수는 최소 10 이상 입니다");
        petCount.focus();
        return false;
    }

    console.log(petCount.value)

    console.log(glassCount.value)

    console.log((petCount.value) + (glassCount.value));

    $li1.hide(1000);
    $li2.hide(1000);
    $nextli.hide(1000);


    $li3.show(1000);
    $li4.show(1000);
    $li5.show(1000);
    $sumit.show(1000);

    progressBar.style.width = '88%';
    progressPercent.innerText = '88%';



}



// $(function(){
//
//
//   $("#submit").click(function(){
//     $(".modal").fadeIn();
//     progressBar.style.width = '100%';
//     progressPercent.innerText = '100%';
//   });
//
//   $(".modal_content").click(function(){
//     $(".modal").fadeOut();
//   });
//
// });

// boolean check =

function formCheck(){


    let userName = document.getElementById("userName")
    let userAddress = document.getElementById("userAddress")

    if(userName.value == ""){
        alert("이름을 입력하세요 ")
        userName.focus();
        return false;
    }

    if(userAddress.value == ""){
        alert("주소를 입력하세요 ")
        userAddress.focus();
        return false;
    }

    /*서브밋 자리 */
    /*서브밋 자리 */
    /*서브밋 자리 */


    /*서브밋 자리 */
    /*서브밋 자리 */
    /*서브밋 자리 */


    progressBar.style.width = '100%';
    progressPercent.innerText = '100%';


    $(".modal").fadeIn();

    $(".modal_content").click(function(){
        $(".modal").fadeOut();
    });


}


