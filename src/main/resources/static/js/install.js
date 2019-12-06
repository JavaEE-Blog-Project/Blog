layui.use(['form', 'layer'], function () {
    let form = layui.form
        , layer = layui.layer;

    form.on('submit(install)', function (data) {
        let field = data.field;

        $.ajax({
            url: '/api/admin/install'
            , type: 'POST'
            , data: JSON.stringify(field)
            , dataType: 'text'
            , contentType: 'application/json'
            , success: function (response) {
                response = JSON.parse(response);
                if (response.status === 200) {
                    layer.msg("安装成功");
                    window.location.href = "/";
                } else {
                    layer.msg("该博客已被注册")
                }
            }
        });

        return false;
    })
});