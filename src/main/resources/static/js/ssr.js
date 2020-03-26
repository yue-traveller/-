layui.use('table', function() {
    var table = layui.table;

    //第一个实例
    table.render({
        elem: '#test'
        , url: '/user/articleList' //数据接口
        , page: true //开启分页
        , cols: [[
            {field: 'name', title: '资源名称', width: 300, fixed: 'left'}
            , {field: 'email', title: '所属类别', width: 100}
            , {field: 'points', title: '积分', width: 80, sort: true}
            , {field: 'publishDate', title: '发布日期', width: 177, sort: true}
            , {field: 'state', title: '审核状态', width: 100}
            , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 150}
        ]]
        ,request:{
            limitName:'limit'
            ,pageName:'page'
        }
        ,response:{
            statusName:'code'
            ,countName:'count'
            ,dataName:'data'
            ,statusCode:0
        }
    });

});