tocbot.init({
    // Where to render the table of contents.
    tocSelector: '.js-toc',
    // Where to grab the headings to build the table of contents.
    contentSelector: '.js-toc-content',
    // Which headings to grab inside of the contentSelector element.
    headingSelector: 'h1, h2, h3'
});

$('.toc.button').popup({
    popup: $('.toc-container.popup'),
    on: 'click',
    position: 'left center'
});

$('#toTop-button').click(function () {
    $(window).scrollTo(0, 500);
});

let waypoint = new Waypoint({
    element: document.getElementById('waypoint'),
    handler: function (direction) {
        if (direction == 'down') {
            $('#toolbar').show(100);
        } else {
            $('#toolbar').hide(500);
        }
    }
});

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
                    window.location.reload();
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