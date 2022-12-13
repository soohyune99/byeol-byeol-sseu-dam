
$(".adminPage").on("click",function () {
    if(memberCategory != "관리자"){
        alert("접근 권한이 없습니다.")
        return;
    }
    location.href="http://localhost:10001/admin"
})

$(".pickupPage").on("click",function () {
    if(memberCategory == "기사회원" || memberCategory =="관리자"){
        location.href="http://localhost:10001/pickup/main"
    }else{
        location.href="http://localhost:10001/join/picker"
    }
})