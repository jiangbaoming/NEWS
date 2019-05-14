var nodeData;
layui.use(['jquery', 'layer', 'element', 'form', 'tree', 'table'], function () {

    window.jQuery = window.$ = layui.jquery;
    window.layer = layui.layer;
    var form = layui.form,
        elem = layui.element,
        table = layui.table,
        topcateid = 0;  //为模拟顶级分类id用
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
                click: function(node){
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

    function tableReload(num) {
        table.render({
            elem: '#list',
            url: '/organization',
            where: {method: 'list',pid:num},
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
                {field: 'createDate', title: '创建时间', minWidth: 200,align: 'center'},
                {field: 'modifyDate', title: '修改时间时间', minWidth: 200,align: 'center'},
            ]]
        });
    }
//列表
    tableReload(null);
//添加顶级分类
    $("#addcate").on("click", function () {
        layer.prompt({title: '输入机构名称，并确认', formType: 0}, function (text, index) {
            layer.close(index);
            //TODO 可以ajax到后台操作，这里只做模拟
            layer.load(2);
            setTimeout(function () {
                layer.closeAll("loading");
                //手动添加节点，肯定有更好的方法=.=！这里的方法感觉有点LOW
                // li里面的pid属性为父级类目的id,顶级分类的pid为0
                topcateid = topcateid + 1;
                $("ul#demo").append("<li  pid='0' id=" + (topcateid) + ">" +
                    "<a ><cite>" + text + "</cite> </a>" +
                    "<i class='layui-icon select hide add'></i>" +
                    "<i class='layui-icon edit select hide'></i>" +
                    "<i class='layui-icon del select hide'></i>" +
                    "</li>");
            }, 1000)
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

            //TODO 可以ajax到后台操作，这里只做模拟
            layer.load(2);
            setTimeout(function () {
                layer.closeAll("loading");
                topcateid = topcateid + 1;
                if (that.children("ul").length == 0) {
                    //表示要新增   i 以及 ul 标签
                    that.prepend('<i class="layui-icon layui-tree-spread"></i>')
                    that.append("<ul class='layui-show'><li  pid=" + pid + "   id=" + (topcateid) + "><a    ><cite>" + text + "</cite> </a><i  class='layui-icon select hide add' )'></i> <i    class='layui-icon edit select hide'></i> <i    class='layui-icon del select hide'></i></li></ul>")
                } else {
                    that.children("ul").append("<li pid=" + pid + "    id=" + (topcateid) + "><a  ><cite>" + text + "</cite> </a><i  class='layui-icon select hide add' )'></i> <i    class='layui-icon edit select hide'></i> <i    class='layui-icon del select hide'></i></li>");
                }
            }, 1000)
        });


    })
//重命名
    $("ul#demo ").on("click", "li .edit", function () {
        var node = $(this).parent().children("a").children("cite");
        var id = $(this).parent().attr("id")
        var that = $(this).closest("li");
        layer.prompt({title: '输入新的分类名称，并确认', value: node.text(), formType: 0}, function (text, index) {
            layer.close(index);

            //TODO 可以ajax到后台操作，这里只做模拟
            layer.load(2);
            setTimeout(function () {
                layer.closeAll("loading");
                node.text(text);
            }, 1000)
        });


    })
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

            //TODO 可以ajax到后台操作，这里只做模拟
            layer.load(2);
            setTimeout(function () {
                layer.closeAll("loading");
                if ((that.parent("ul").children("li").length == 1) && (that.parent("ul").parent("li").children("i.layui-tree-spread").length = 1)) {
                    //要把分类名前的三角符号和ul标签删除
                    that.parent("ul").parent("li").children("i.layui-tree-spread").remove();
                }
                that.remove()
            }, 1000)
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