layui.use(['form', 'layer', 'table'], function () {
        const form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer,
            $ = layui.jquery,
            table = layui.table;

        //根据用户角色获取不同的news列表
        //列表
        const tableIns = table.render({
            elem: '#list',
            url: '/news',
            where: {
                method:'getList',
                user:$.cookie('user'),
            },
            method: "GET",
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
                {field: 'nid', title: 'ID', width: 90, align: 'center'},
                {field: 'uName', title: '作者', width: 110, align: 'center'},
                {
                    field: 'title', title: '标题', minWidth: 200, align: "left", templet: function (d) {
                        return '<a lay-event="edit" style="cursor:pointer;color: #01AAED">' + d.title + '</a>';
                    }
                },
                {field: 'introduction', title: '简介', minWidth: 300, align: 'left'},
                {field: 'tName', title: '文章类别', width: 90, align: 'center'},
                {
                    field: 'releaseDate', title: '发布时间', width: 200, align: "center", templet: function (d) {
                        return d.releaseDate;
                    }
                },
                {field: 'times', title: '浏览次数', width: 90, align: 'center'},
                {title: '操作', width: 145, templet: '#userListBar', fixed: "right", align: "center"}
            ]]
        });

        //头工具栏事件
        table.on('toolbar(test)', function (obj) {
            switch (obj.event) {
                case 'search_btn':
                    table.reload("dataTable", {
                        url: '/news',
                        where: {
                            title: $(".searchVal").val(),
                            method: "search",
                            user:$.cookie('user'),
                        }
                    });
                    break;
                case 'flash_btn':
                    window.location.reload();
                    break;
                case 'add_btn':
                    const index = layui.layer.open({
                        title: "新增资讯",
                        type: 2,
                        area: ["500px", "450px"],
                        content: "informationAdd.html",
                        shadeClose: true,
                        success: function () {
                            setTimeout(function () {
                                layui.layer.tips('点击此处关闭', '.layui-layer-setwin .layui-layer-close', {
                                    tips: 3
                                });
                            }, 100)
                        }
                    });
                    layui.layer.full(index);
                    window.sessionStorage.setItem("index", index);
                    //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
                    $(window).on("resize", function () {
                        layui.layer.full(window.sessionStorage.getItem("index"));
                    });
                    break;
            }
        });
        //列表操作
        table.on('tool(test)', function (obj) {
            const layEvent = obj.event,
                data = obj.data;
            console.log(data);
            switch (layEvent) {
                case 'edit'://编辑
                    const index = layui.layer.open({
                        title: "查看/更新资讯",
                        type: 2,
                        content: "informationUpd.html",
                        success: function (layero, index) {
                            const body = layui.layer.getChildFrame('body', index);
                            body.find("#demo1").attr("src", data.banner);  //封面图
                            body.find(".id").val(data.nid);//新闻编号
                            body.find(".articleTitle").val(data.title);  //文章标题
                            body.find(".introduction").val(data.introduction);  //文章简介
                            body.find(".createDate").val(data.releaseDate);//发布时间
                            body.find("#tid").val(data.tid);//类别
                            body.find("#newsContent").val(data.content);//内容
                            form.render();
                            setTimeout(function () {
                                layui.layer.tips('点击此处关闭', '.layui-layer-setwin .layui-layer-close', {
                                    tips: 3
                                });
                            }, 100);
                        }
                    });
                    layui.layer.full(index);
                    window.sessionStorage.setItem("index", index);
                    //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
                    $(window).on("resize", function () {
                        layui.layer.full(window.sessionStorage.getItem("index"));
                    });
                    break;
                case 'del'://删除
                    layer.confirm('确定删除此资讯？', {icon: 3, title: '提示信息'}, function (index) {
                        $.ajax({
                            url:'/news',
                            type: "post",
                            data:{
                                method:'delete',
                                nid:data.nid,
                            },
                            success: function (result) {
                                layer.msg("删除成功");
                                // window.location.href = "informationList.html";
                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                // tableIns.reload();
                                layer.close(index);
                            }
                        });
                    });
                    break;
            }
        });
    }
);
