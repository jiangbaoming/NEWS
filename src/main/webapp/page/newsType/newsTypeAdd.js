layui.use(['form', 'layer'], function () {
    const form = layui.form,
        layer = layui.layer;

    //提交
    form.on("submit(submit)", function (data) {
        const field = data.field;
        //弹出loading
        const index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "/newsType",
            type: "POST",
            dataType: "json",
            data: {
                "method": "add",
                "tName": field.tName,
            },
            success: function (result) {
                top.layer.close(index);
                if (result.code === 200) {
                    top.layer.msg("添加成功！");
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