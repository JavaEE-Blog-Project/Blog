let editor;
$(function () {
    editor = editormd("md-content", {
        width: "100%"
        , height: 640
        , synScrolling: "single"
        , path: "/lib/editor/lib/"
    })
});

layui.use('form', function () {
    let form = layui.form;

    form.render();

    form.on('submit(post)', function (data) {
        var field = data.field;

        $.ajax({
            url: '/api/admin/journals'
            , type: 'POST'
            , data: JSON.stringify(field)
            , dataType: 'text'
            , contentType: 'application/json'
            , success: function (response) {
                response = JSON.parse(response)
                if (response.status === 200) {
                    layer.msg("添加成功")
                } else if (response.status === 400) {
                    layer.msg("关键字段不能为空")
                } else {
                    layer.msg("添加失败，请重试")
                }
            }
            , error: function () {
                layer.msg("添加失败")
            }
        })

        return false;
    });
});

// $('.ui.dropdown').dropdown({
//     on :'hover'
// });

// $('#save-btn').click(function () {
//     let formObject = {};
//     let formArray = $('#edit-form').serializeArray();
//     $.each(formArray, function (i, item) {
//         formObject[item.name] = item.value;
//     });
//     $.ajax({
//         url:'/api/admin/journals'
//         ,type:'POST'
//         ,data:JSON.stringify(formObject)
//         ,dataType:'text'
//         ,contentType:'application/json'
//         ,success: function (response) {
//             response = JSON.parse(response)
//             if (response.status === 200) {
//                 window.alert("添加成功")
//             }
//         }
//     });
// });