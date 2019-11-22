$("#bigsearch").bind('keypress', function (event) {
    if (event.keyCode == '13') {
        let val = $("#bigsearch").val();
        if (val == "") {
            return;
        }
        window.location.href = "/search?keyword=" + val;
    }
})