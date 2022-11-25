$('.moving').each(function() {
    var $this = $(this),
        countTo = $this.attr('data-moving');

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
                let result = this.countNum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                result += "+";
                $this.text(result);
            }
        });
});