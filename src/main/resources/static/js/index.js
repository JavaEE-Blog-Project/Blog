$('#page').bind('keypress', function (event) {
    if (event.keyCode == '13') {
        var val = $('#page').val();
        var total = parseInt($('#total').text());
        if (val < 1) val = 1;
        if (val > total) val = total;
        window.location.href = "/page/" + val;
    }
});