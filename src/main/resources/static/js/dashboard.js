layui.use(['table', 'form'], function () {
    let table = layui.table
        , form = layui.form;

    let tableIns = table.render({
        elem: '#journalTable'
        , url: '/api/admin/journals'
        , totalRow: true
        , page: true
        , toolbar: 'default'
        , cols: [
            [
                {type: 'checkbox', fixed: 'left'}
                , {field: 'title', title: '标题'}
                , {field: 'visits', title: '访问', sort: true, width: 80}
                , {
                field: 'type',
                title: '类型',
                sort: true,
                width: 80,
                templet: "<div>{{d.type === 'PUBLIC' ? '公开': '私密'}}</div>"
            }
                , {field: 'category', title: '分类', sort: true, templet: "<div>{{d.category.name}}</div>"}
                , {
                field: 'createTime',
                title: '发布时间',
                sort: true,
                templet: "<div>{{layui.util.toDateString(d.createTime, 'yyyy-MM-dd HH:mm:ss')}}</div>"
            }
                , {
                field: 'updateTime',
                title: '更新时间',
                sort: true,
                templet: "<div>{{layui.util.toDateString(d.updateTime, 'yyyy-MM-dd HH:mm:ss')}}</div>"
            }
                , {toolbar: '#toolbar', fixed: 'right', align: 'center'}
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

    table.on('tool(journalTable)', function (obj) {
        let data = obj.data
            , event = obj.event;
        switch (event) {
            case 'detail':
                $(location).attr("href", '/journals/' + data.id)
                break;
            case 'edit':
                $(location).attr("href", '/admin/edit/' + data.id)
                break;
            case 'del':
                layer.confirm('确认删除？', function (index) {
                    $.ajax({
                        url: '/api/admin/journals/' + data.id
                        , type: 'DELETE'
                        , data: {}
                        , dateType: 'application/json'
                        , success: function (response) {
                            layer.msg("删除成功")
                            tableIns.reload();
                        }
                        , error: function () {
                            layer.msg("删除失败")
                        }
                    });
                    layer.close(index);
                });
                break;
        }
    });

    form.on('submit(query)', function (data) {
        let field = data.field;

        tableIns.reload({
            url: '/api/admin/journals/search'
            , where: {
                'keyword': field['keyword']
                , 'type': field['type']
                , 'categoryId': field['categoryId']
            }
        });

        return false;
    })
});