layui.use(['form', 'layer', 'table'], function () {
    const form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    //列表
    const tableIns = table.render({
        elem: '#list',
        url: '/newsType',
        where: {method: 'getAll'},
        method: "POST",
        request: {
            pageName: 'pageNum' //页码的参数名称，默认：page
            , limitName: 'pageSize' //每页数据量的参数名，默认：limit
        },
        response: {
            statusName: 'code' //数据状态的字段名称，默认：code
            , statusCode: 200 //成功的状态码，默认：0
            , msgName: 'msg' //状态信息的字段名称，默认：msg
            , countName: 'totalCount' //数据总数的字段名称，默认：count
            , dataName: 'data' //数据列表的字段名称，默认：data
        },
        cellMinWidth: 95,
        page: true,
        height: "full-25",
        limits: [5, 10, 15, 20, 25],
        limit: 15,
        id: "dataTable",
        toolbar: '#toolbarDemo',
        defaultToolbar: [],
        cols: [[
            {field: 'tid', title: 'ID', width: 90, align: 'center'},
            {field: 'tName', title: '类别名称', Width: 100, align: "center"},
            {title: '操作', minWidth: 145, templet: '#userListBar', fixed: "right", align: "center"}
        ]]
    });

    //头工具栏事件
    table.on('toolbar(test)', function (obj) {
        const checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
           /* case 'search_btn':
                table.reload("dataTable", {
                    url: "/user",
                    where: {
                        param: $(".searchVal").val(),
                        method: "search",
                    }
                });
                break;*/
            case 'flash_btn':
                window.location.reload();
                break;
            case 'add_btn':
                const index = layui.layer.open({
                    title: "新增类别",
                    type: 2,
                    area: ["500px", "250px"],
                    content: "newsTypeAdd.html",
                    shadeClose: true
                });
                break;
        }
    });

    //监听行工具事件
    table.on('tool(test)', function (obj) {
        const layEvent = obj.event,
            data = obj.data;
        console.log(data);
        switch (layEvent) {
            case 'edit'://编辑
                const index = layui.layer.open({
                    title: "编辑类别",
                    type: 2,
                    area: ["500px", "250px"],
                    content: "newsTypeUpd.html",
                    shadeClose: true,
                    success: function (layero, index) {
                        const body = layui.layer.getChildFrame('body', index);
                        body.find("input[name=tid]").val(data.tid);
                        body.find("input[name=tName]").val(data.tName);
                        form.render();
                    }
                });
                break;
            case 'del'://删除
                layer.confirm('确定删除此类别？', {icon: 3, title: '提示信息'}, function (index) {
                    $.ajax({
                        url: '/newsType',
                        data:{
                            method:'delete',
                            tid:data.tid,
                        },
                        type: "post",
                        dataType:"json",
                        success: function (result) {
                            if(result.code==200){
                                layer.msg("删除成功");
                                // window.location.href = "newsTypeList.html";
                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                // tableIns.reload();
                                layer.close(index);
                            }else {
                                layer.msg(result.msg);
                                layer.close(index);
                            }
                        }
                    });
                });
                break;
        }
    });

    /*// 修改状态开关
    form.on('switch(status)', function (data) {
        // console.log(data.elem.checked); //开关是否开启，true或者false
        // console.log(data.value); //开关value值，也可以通过data.elem.value得到
        $.ajax({
            url: "/user",
            type: "post",
            dataType: "json",
            data:{
                method:"changeRole",
                id: data.value,
                role: data.elem.checked ? "1" : "0"
            },
            success: function (result) {
                if (result.code === 200) {
                    layer.msg("修改成功");
                } else {
                    layer.alert(result.msg, {icon: 7, anim: 6});
                }
            }
        });
    });*/
});
