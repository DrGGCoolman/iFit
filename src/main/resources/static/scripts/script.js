$(".jumbotron-sport").mouseover(function () {
    $('#jumbo-sport').removeClass('col-lg-3');
    $('#jumbo-sport').addClass('col-lg-12')
    $('#jumbo-sportplus').hide();
    $('#jumbo-suv').hide();
    $('#jumbo-luxus').hide();
    $("#sport").show();
    $("#text-sport").show();
});
$(".jumbotron-sport").mouseout(function () {
    $('#jumbo-sport').addClass('col-lg-3');
    $('#jumbo-sport').removeClass('col-lg-12');
    $('#jumbo-sportplus').show();
    $('#jumbo-suv').show();
    $('#jumbo-luxus').show();
    $("#sport").hide();
    $("#text-sport").hide();
});

$(".jumbotron-sportplus").mouseover(function () {
    $('#jumbo-sportplus').removeClass('col-lg-3');
    $('#jumbo-sportplus').addClass('col-lg-12');
    $('#jumbo-sport').hide();
    $('#jumbo-suv').hide();
    $('#jumbo-luxus').hide();
    $("#text-sportplus").show();
});
$(".jumbotron-sportplus").mouseout(function () {
    $('#jumbo-sportplus').addClass('col-lg-3');
    $('#jumbo-sportplus').removeClass('col-lg-12');
    $('#jumbo-sport').show();
    $('#jumbo-suv').show();
    $('#jumbo-luxus').show();
    $("#text-sportplus").hide();
});

$(".jumbotron-suv").mouseover(function () {
    $('#jumbo-suv').removeClass('col-lg-3');
    $('#jumbo-suv').addClass('col-lg-12');
    $('#jumbo-sport').hide();
    $('#jumbo-sportplus').hide();
    $('#jumbo-luxus').hide();
    $("#text-suv").show();
});
$(".jumbotron-suv").mouseout(function () {
    $('#jumbo-suv').addClass('col-lg-3');
    $('#jumbo-suv').removeClass('col-lg-12');
    $('#jumbo-sport').show();
    $('#jumbo-sportplus').show();
    $('#jumbo-luxus').show();
    $("#text-suv").hide();
});

$(".jumbotron-luxus").mouseover(function () {
    $('#jumbo-luxus').removeClass('col-lg-3');
    $('#jumbo-luxus').addClass('col-lg-12');
    $('#jumbo-suv').hide();
    $('#jumbo-sport').hide();
    $('#jumbo-sportplus').hide();
    $("#text-luxus").show();
});
$(".jumbotron-luxus").mouseout(function () {
    $('#jumbo-luxus').addClass('col-lg-3');
    $('#jumbo-luxus').removeClass('col-lg-12');
    $('#jumbo-suv').show();
    $('#jumbo-sport').show();
    $('#jumbo-sportplus').show();
    $("#text-luxus").hide();
});
