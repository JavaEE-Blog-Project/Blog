layui.use(['table', 'form'], function () {
    let table = layui.table,
        form = layui.form;

    let tableIns = table.render({
        elem: '#categoryTable'
        , url: '/api/admin/category'
        , page: true
        , cols: [
            [
                {type: 'checkbox', fixed: 'left'}
                , {field: 'name', title: '名称', sort: true}
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
                "code": 0,
                "msg": res.msg,
                "count": res.data.totalElements,
                "data": res.data.content
            }
        }
    });

    table.on('tool(categoryTable)', function (obj) {
        let data = obj.data
            , event = obj.event;
        switch (event) {
            case 'edit':
                break;
            case 'del':
                layer.confirm('确认删除？', function (index) {
                    $.ajax({
                        url: '/api/admin/category/' + data.id
                        , type: 'DELETE'
                        , data: null
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

    form.on('submit(save)', function (data) {
        let field = data.field;

        $.ajax({
            url: '/api/admin/category'
            , type: 'POST'
            , data: JSON.stringify(field)
            , dataType: 'text'
            , contentType: 'application/json'
            , success: function (response) {
                response = JSON.parse(response);
                if (response.status === 200) {
                    layer.msg("添加成功");
                    tableIns.reload();
                } else {
                    layer.msg(response.msg)
                }
            }
            , error: function () {
                layer.msg("添加失败, 键值已存在")
            }
        });

        return false;
    })
});