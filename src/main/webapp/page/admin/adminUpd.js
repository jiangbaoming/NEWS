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
   /* var role = $("#role").val();
    if (role==1) {
        $("#role1").attr("selected")
    }else {
        $("#role2").attr("selected")
    };*/
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
    //提交
    form.on("submit(submit)", function (data) {
        const field = data.field;
        //弹出loading
        const index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "/user",
            type: "post",
            dataType: "json",
            data: {
                "method": "update",
                "id": field.id,
                "phone": field.phone,
                "userName": field.userName,
                /*"role": field.role,*/
                "oid": modifyOid
            },
            success: function (result) {
                top.layer.close(index);
                if (result.code == 200) {
                    top.layer.msg("修改成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                } else {
                    layer.alert(result.msg, {icon: 7, anim: 6});
                }
            }
        });
        return false;
    });
});