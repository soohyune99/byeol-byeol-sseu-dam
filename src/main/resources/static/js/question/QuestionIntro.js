$(function(){

    $("#launcher").click(function(){
        $(".modal").fadeIn();
        $("#ch-plugin").fadeOut();
    });

    $(".close").click(function(){
        $(".modal").fadeOut();
        $(".modal2").fadeOut();
        $("#ch-plugin").fadeIn();
    });

});

$(function(){

    $("#launcher2").click(function(){
        $(".modal").fadeOut();
        $(".modal2").fadeIn();
    });

    $(".pre").click(function(){
        $(".modal2").fadeOut();
        $(".modal").fadeIn();

    });


});