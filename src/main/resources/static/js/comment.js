layui.use(['table', 'form'], function () {
    let table = layui.table
        , form = layui.form;

    let tableIns = table.render({
        elem: '#commentTable'
        , url: '/api/admin/comment'
        , totalRow: true
        , page: true
        , cols: [
            [
                {type: 'checkbox', fixed: 'left'}
                , {field: 'nickname', title: '昵称'}
                , {field: 'content', title: '内容'}
                , {field: 'journal', title: '评论文章', sort: true, templet: "<div>{{d.journal.title}}</div>"}
                , {
                field: 'createTime',
                title: '评论时间',
                sort: true,
                templet: "<div>{{layui.util.toDateString(d.createTime, 'yyyy-MM-dd HH:mm:ss')}}</div>"
            }
                , {toolbar: '#toolbar', title: '操作'}
            ]
        ]
        , parseData: function (res) {
            return {
                "code": res.status === 200 ? 0 : 1,
                "msg": res.msg,
                "count": res.data.totalElements,
                "data": res.data.content
            }
        }
    });

    table.on('tool(commentTable)', function (obj) {
        let data = obj.data;
        layer.confirm("确认删除该评论及其子评论？", function (index) {
            $.ajax({
                url: '/api/admin/comment/' + data.id
                , type: 'DELETE'
                , data: {}
                , dataType: 'application/json'
                , error: function () {
                    layer.msg("删除成功");
                    tableIns.reload();
                }
            });
            layer.close(index);
        });
    });

    form.on('submit(query)', function (data) {
        let field = data.field;

        tableIns.reload({
            url: '/api/admin/comment/search'
            , where: {
                'keyword': field['keyword']
            }
        });

        return false;
    })
});