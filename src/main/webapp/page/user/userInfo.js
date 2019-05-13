layui.config({
    base: "/statics/js/"
}).extend({
    "treeSelect": "treeSelect"
});
layui.use(['form', 'layer', 'treeSelect'], function () {
    const form = layui.form,
        layer = layui.layer,
        treeSelect = layui.treeSelect;

    var modifyOid = $("#tree").val();

    treeSelect.render({
        // 选择器
        elem: '#tree',
        // 数据
        data: '/organization?method=getAll',
        // 异步加载方式：get/post，默认get
        type: 'get',
        // 占位符
        //placeholder: '请选择部门',
        // 一些可定制的样式
        style: {
            folder: {
                enable: true
            },
            line: {
                enable: true
            }
        },
        // 点击回调
        click: function (d) {
            //console.log(d);
            modifyOid = d.current.id;
            // layer.msg(updataDid);
        },
//      加载完成后的回调函数
        success: function (d) {
//               console.log(d);
//                选中节点，根据id筛选
            treeSelect.checkNode('tree', modifyOid);
//                获取zTree对象，可以调用zTree方法
//               var treeObj = treeSelect.zTree('tree');
//               console.log(treeObj);
//                刷新树结构
            treeSelect.refresh('tree');
        }
    });

    $(function () {
        $.ajax({
            url: $.cookie("tempUrl") + "admin/selectBySession?token=" + $.cookie("token"),
            type: "GET",
            success: function (result) {
                $(".id").attr("value", result.data.id);
                $(".phone").attr("value", result.data.phone);
                $(".truename").attr("value", result.data.name);
                if (result.data.status === 8) {
                    $(".level").attr("value", "超级管理员");
                } else {
                    $(".level").attr("value", "普通管理员");
                }
            }
        });
    });

    //提交个人资料
    form.on("submit(changeUser)", function (data) {
        const field = data.field;
        var index = layer.msg('提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "/user",
            type: "post",
            dataType: "json",
            data: {
                "method": "update",
                "id": field.id,
                "phone": field.phone,
                "userName": field.userName,
                "role": field.role,
                "oid": modifyOid
            },
            success: function (result) {
                if (result.code === 200) {
                    layer.msg("更新成功,请重新登陆...");
                    setTimeout(function () {
                        top.layer.close(index);
                        layer.closeAll("iframe");
                        //跳转至登陆界面
                        $.cookie('token', "", {path: '/'});
                        parent.location.href = "/login.html";
                    }, 500);
                } else {
                    layer.alert(result.msg, {icon: 7, anim: 6});
                }
            }
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
});