$(".ui.dropdown").dropdown();

$("#resetTable").click(function () {
    $('#keyword').val("");
})

layui.use('table', function () {
    let table = layui.table;

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
                , {field: 'views', title: '访问', sort: true, width: 80}
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
    })

    table.on('tool(journalTable)', function (obj) {
        let data = obj.data
            , event = obj.event;
        switch (event) {
            case 'detail':
                $(location).attr("href", '/journals/' + data.id)
                break;
            case 'edit':
                layer.msg('编辑');
                break;
            case 'del':
                layer.confirm('确认删除？');
                break;
        }
    });

    $('#searchTable').click(function () {
        let keyword = $('#keyword').val()
            , type = $('#type').val()
        tableIns.reload({
            url: '/api/admin/journals/search'
            , where: {'keyword': keyword, 'type': type}
        })
    })
});