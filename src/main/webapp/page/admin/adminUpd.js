layui.use(['form', 'layer'], function () {
    const form = layui.form,
        layer = layui.layer;

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
                "method":"update",
                "id": field.id,
                "phone": field.phone,
                "userName": field.trueName,
            },
            success: function (result) {
                top.layer.close(index);
                if (result.code == 200) {
                    top.layer.msg("管理员修改成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                } else {
                    layer.alert(result.data.msg, {icon: 7, anim: 6});
                }
            }
        });
        return false;
    });
});