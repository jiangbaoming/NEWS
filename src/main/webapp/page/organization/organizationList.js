var nodeData;
layui.config({
    base: "/statics/js/"
}).extend({
    "atree": "atree"
});
layui.use(['jquery', 'layer', 'element', 'form', 'atree', 'table'], function () {

    window.jQuery = window.$ = layui.jquery;
    window.layer = layui.layer;
    var form = layui.form,
        elem = layui.element,
        table = layui.table;
    if ($.cookie("truename") == null || $.cookie("truename") === "") {
        window.location.href = "/login.html";
    }
    function treeReload() {
        $.ajax({
            url: "/organization",
            type: "POST",
            dataType: "json",
            data: {
                "method": "getAll",
            },
            success: function (result) {
                nodeData = result;
                console.log(nodeData);
                $("#demo").html('');
                var tree = layui.atree({
                    elem: '#demo', //指定元素
                    // check: 'checkbox' //勾选风格

                    skin: 'as', //设定皮肤
                    //target: '_blank' //是否新选项卡打开（比如节点返回href才有效）

                    //  drag: true,
                    spreadAll: true,
                    props: {
                        renameBtnLabel: '修改',
                        addBtnLabel: '新增',
                        deleteBtnLabel: '删除',
                        name: 'name',
                        id: 'id',
                        children: 'children',
                    },
                    click: function (item) { //点击节点回调
                        //layer.msg(item.id + "====" + item.name);
                        tableReload(item.id);
                    },
                    addClick: function (item, elem, add) {
                        layer.msg(item.id);
                        layer.prompt({title: '输入机构名称，并确认', formType: 0}, function (text, index) {
                            layer.close(index);
                            layer.load(2);
                            $.ajax({
                                url: '/organization',
                                data: {
                                    method: 'addChild',
                                    oname: text,
                                    pid: item.id,
                                },
                                dataType: 'json',
                                type: 'post',
                                success: function (result) {
                                    if (result.code == 200) {
                                        layer.msg('添加成功');
                                        treeReload();
                                        setTimeout(function () {
                                            layer.closeAll("loading");
                                        }, 500);
                                    }
                                }
                            });
                        });
                    },
                    renameClick:function(item, elem, rename){
                        layer.prompt({title: '输入新的分类名称，并确认',value:item.name, formType:0}, function(text, index){
                            layer.close(index);
                            console.log(text);
                            //TODO 可以ajax到后台操作，这里只做模拟
                            layer.load(2);
                            $.ajax({
                                url: '/organization',
                                data: {
                                    method: 'reName',
                                    oname: text,
                                    oid: item.id,
                                },
                                dataType: 'json',
                                type: 'post',
                                success: function (result) {
                                    if (result.code == 200) {
                                        layer.msg('修改成功');
                                        treeReload();
                                        setTimeout(function () {
                                            layer.closeAll("loading");
                                        }, 500);
                                    }
                                }
                            });
                        });
                    },
                    deleteClick: function (item, elem, done) {
                        console.log(done);
                        if ( item.children !==undefined &&item.children.length > 0) {
                            layer.msg("该分类下含有子分类不能删除")
                            return;
                        }
                        layer.confirm('确定要删除？', {
                            btn: ['删除', '取消']
                        }, function () {
                            layer.load(2);
                            $.ajax({
                                url: '/organization',
                                data: {
                                    method: 'delete',
                                    oid: item.id,
                                },
                                dataType: 'json',
                                type: 'post',
                                success: function (result) {
                                    if (result.code == 200) {
                                        layer.msg('删除成功');
                                        treeReload();
                                        setTimeout(function () {
                                            layer.closeAll("loading");
                                        }, 500)
                                    }
                                }
                            });
                        });
                    },
                    nodes: nodeData,
                });
            }
        });
    }

    function tableReload(num) {
        table.render({
            elem: '#list',
            url: '/organization',
            where: {
                method: 'list',
                pid: num,
            },
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
            limit: 10,
            id: "dataTable",
            cols: [[
                {field: 'oid', title: 'ID', width: 150, align: 'center'},
                {field: 'oname', title: '机构名称', minWidth: 200, align: "center"},
                {field: 'pname', title: '上级部门名称', minWidth: 300, align: "center"},
                {field: 'createDate', title: '创建时间', minWidth: 200, align: 'center'},
                {field: 'modifyDate', title: '修改时间时间', minWidth: 200, align: 'center'},
            ]]
        });
    }

//初始化部门列表和部门tree
    treeReload();
    tableReload(null);
//添加顶级分类
    $("#addcate").on("click", function () {
        layer.prompt({title: '输入机构名称，并确认', formType: 0}, function (text, index) {
            layer.close(index);
            layer.load(2);
            $.ajax({
                url: '/organization',
                data: {
                    method: 'addParent',
                    oname: text,
                },
                dataType: 'json',
                type: 'post',
                success: function (result) {
                    if (result.code == 200) {
                        layer.msg('添加成功');
                        treeReload();
                        setTimeout(function () {
                            layer.closeAll("loading");
                        }, 500);
                    }
                }
            });
        });
    })

});