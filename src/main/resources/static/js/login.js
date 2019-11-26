layui.use(['form','layer','jquery'], function () {
    // 操作对象
    var layer = layui.layer
        ,form = layui.form
        ,$ = layui.jquery;

    form.on('submit(login)',function (data) {
        var field = data.field;

        $.ajax({
            url:'/api/admin/login'
            ,type:'POST'
            ,data:JSON.stringify(field)
            ,dataType:'text'
            ,contentType:'application/json'
            ,success:function (response) {
                response = JSON.parse(response)
                if (response.status === 200) {
                    var token = response.data
                    window.localStorage.setItem("token",token)
                    window.location.href = ""
                }

                if (response.status === 400) {
                    layer.msg("账号或用户名错误")
                }
            }
        });

        return false;
    });
});