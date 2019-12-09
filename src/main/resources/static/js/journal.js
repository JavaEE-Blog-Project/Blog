$('.ui.form').form({
    fields: {
        title: {
            identifier: 'content',
            rules: [{
                type: 'empty',
                prompt: '请输入评论内容'
            }]
        }
        , content: {
            identifier: 'nickname',
            rules: [{
                type: 'empty',
                prompt: '请输入昵称'
            }]
        }
        , type: {
            identifier: 'email',
            rules: [{
                type: 'email',
                prompt: '请输入正确的邮箱地址'
            }]
        }
    }
});

$('#comment-submit').click(function () {
    if ($('.ui.form').form('validate form')) {
        let data = {
            "blogId": $("[name='blogId']").val(),
            "parentCommentId": $("[name='parentCommentId']").val(),
            "nickname": $("[name='nickname']").val(),
            "email": $("[name='email']").val(),
            "content": $("[name='content']").val()
        };
        // $("#comment-container").load("/api/admin/comment", data, function (response, status, xhr) {
        //     if (status === 'success') {
        //         alert("添加成功");
        //     }
        // });
        $.ajax({
            url: '/api/admin/comment'
            , type: 'POST'
            , data: JSON.stringify(data)
            , dataType: 'text'
            , contentType: 'application/json'
            , success: function (response) {
                response = JSON.parse(response);
                if (response.status === 200) {
                    alert("评论成功");
                } else {
                    alert("评论失败")
                }
            }
        })
    } else {
        window.alert("校验失败")
    }
});

function reply(obj) {
    let commentId = $(obj).data('id')
        , nickname = $(obj).data('nickname');

    $("[name='content']").attr('placeholder', "@" + nickname).focus();
    $("[name='parentCommentId']").val(commentId);
    $.scrollTo('#comment-form', 500);
}