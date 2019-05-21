layui.use(['form', 'layer'], function () {
    const form = layui.form,
        layer = layui.layer;
    if ($.cookie("truename") == null || $.cookie("truename") === "") {
        window.location.href = "/login.html";
    }
    $(function () {
        $.ajax({
            url: "/user",
            type: "post",
            data:{
                id:$.cookie("uid"),
                method: 'getUserInfo'
            },
            dataType:'json',
            success: function (result) {
                $(".id").attr("value", result.data.id);
                $(".userCode").attr("value", result.data.userCode);
                $(".phone").attr("value", result.data.phone);
                $(".userName").attr("value", result.data.userName);
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
                "method": "modify",
                "id": field.id,
                "phone": field.phone,
                "userName": field.userName,
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