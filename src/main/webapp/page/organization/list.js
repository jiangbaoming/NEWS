var nodeData;
layui.use(['jquery', 'layer', 'element', 'form', 'tree', 'table'], function () {

    window.jQuery = window.$ = layui.jquery;
    window.layer = layui.layer;
    var form = layui.form,
        elem = layui.element,
        table = layui.table;

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
                layui.tree({
                    elem: '#demo',
                    spreadable: true,
                    nodes: nodeData,
                    click: function (node) {
                        //layer.msg('name:'+node.name+'-----id:'+node.id) //node即为当前点击的节点数据
                        tableReload(node.id);
                    },
                })
                //删除layui-tree 自带的样式
                $("i.layui-tree-branch").remove();
                $("i.layui-tree-leaf").remove();
                //添加操作的图标(即鼠标划过时显示的添加，修改，删除的按钮组)
                $("ul#demo").find("a").after("<i class='layui-icon add select hide ' )'></i>" +
                    "<i class='layui-icon edit select hide'></i>" +
                    "<i class='layui-icon del select hide'></i>");
            }
        });
    }
    function tableReload(num) {
        table.render({
            elem: '#list',
            url: '/organization',
            where: {method: 'list', pid: num},
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

//显示/隐藏 分类的操作栏
    $("ul#demo").on({
        mouseover: function (event) {
            event.stopPropagation();
            $(this).children(".select").removeClass("hide")
        },
        mouseout: function (event) {
            event.stopPropagation();
            $(this).children(".select").addClass("hide")
        },

    }, "li", "a")

//添加子分类
    $("ul#demo ").on("click", "li .add", function () {
        var pid = $(this).closest("li").attr("id");//将父级类目的id作为父类id
        var that = $(this).closest("li");
        layer.prompt({title: '输入子机构名称，并确认', formType: 0}, function (text, index) {
            layer.close(index);
            layer.load(2);
            $.ajax({
                url: '/organization',
                data: {
                    method: 'addChild',
                    oname: text,
                    pid: pid,
                },
                dataType: 'json',
                type: 'post',
                success: function (result) {
                    if (result.code == 200) {
                        layer.msg('添加成功');
                        treeReload();
                        setTimeout(function () {
                            layer.closeAll("loading");
                        }, 500)
                    }
                }
            });
        });


    })
//重命名
    $("ul#demo ").on("click", "li .edit", function () {
        var node = $(this).parent().children("a").children("cite");
        var id = $(this).parent().attr("id")
        layer.prompt({title: '输入新的分类名称，并确认', value: node.text(), formType: 0}, function (text, index) {
            layer.close(index);
            layer.load(2);
            $.ajax({
                url: '/organization',
                data: {
                    method: 'reName',
                    oname: text,
                    oid: id,
                },
                dataType: 'json',
                type: 'post',
                success: function (result) {
                    if (result.code == 200) {
                        layer.msg('重命名成功');
                        treeReload();
                        setTimeout(function () {
                            layer.closeAll("loading");
                        }, 500)
                    }
                }
            });
        });
    });
//删除分类
    $("ul#demo ").on("click", "li .del", function () {
        var that = $(this).closest("li");
        if (that.children("ul").length > 0) {
            layer.msg("该分类下含有子分类不能删除")
            return;
        }
        var id = $(this).parent().attr("id")
        layer.confirm('确定要删除？', {
            btn: ['删除', '取消']
        }, function () {
            layer.load(2);
            $.ajax({
                url: '/organization',
                data: {
                    method: 'delete',
                    oid: id,
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
    })

//打开/关闭菜单
    $("ul#demo").on({
        click: function (event) {
            event.stopPropagation();
            event.preventDefault();
            if ($(this).parent().children("ul").hasClass("layui-show")) {
                $(this).html("");
                $(this).parent().children("ul").removeClass("layui-show");
                return;
            } else {
                $(this).html("");
                $(this).parent().children("ul").addClass("layui-show");
                return;
            }
            return;
        }
    }, 'i.layui-tree-spread');
});