layui.use(['form', 'layer', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    //全局URL设置
    /*  $.cookie('tempUrl', "http://localhost:8080/", {path: '/'});*/

    //禁止后退按钮
    if (window.history && window.history.pushState) {
        $(window).on('popstate', function () {
            window.history.pushState('forward', null, '#');
            window.history.forward(1);
        });
    }
    window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
    window.history.forward(1);
    /* $.cookie("tempUrl") + */
    //加载验证码
    $("#imgCodeimg").attr("src", "/imageCode?method=getImageCode");
    //点击更换验证码
    $("#imgCodeimg").on("click", function (e) {
        this.src = "/imageCode?method=getImageCode&" + Math.random();
    });
    var flag=false;
    $("#code").blur(function () {
        $.ajax({
            url: "imageCode",
            type: "post",
            data: {
                method: "checkImageCode",
                imgCode: $("#code").val()
            },
            dataType: "json",
            success: function (data) {
                if (data.status == 200) {
                    /*layer.msg(data.msg);*/
                    flag=true;
                } else {
                    layer.msg(data.msg);
                    flag=false;
                }
            }
        });
    });
    //登录按钮
    form.on("submit(login)", function (data) {
        if (flag) {
            $(this).text("登录中...").attr("disabled", "disabled").addClass("layui-disabled");
            setTimeout(function () {
                $.ajax({
                    url: "user?method=login",
                    type: "POST",
                    dataType: "json",
                    /* contentType: "application/json;charset=utf-8",*/
                    data: {
                        method: "login",
                        userCode: $("#userName").val(),
                        password: $("#password").val(),
                    },
                    success: function (result) {
                        if (result.status == 200) {
                            layer.msg(result.msg);
                            /* $.cookie('token', result.data.accessToken, {path: '/'});*/
                            $.cookie('truename', result.data.userName, {path: '/'});
                            $.cookie('userRole', result.data.role, {path: '/'});
                            setTimeout(function () {
                                window.location.href = "index.html";
                            }, 1000);
                        } else {
                            layer.alert("登陆失败，"+result.msg, {icon: 7, anim: 6});
                            $("#imgCodeimg").attr("src", "/imageCode?method=getImageCode&" + Math.random());
                            $(".layui-disabled").text("登录").attr("disabled", false).removeClass("layui-disabled").addClass("layui-btn-warm");
                        }
                    }
                });
            }, 1000);
            return false;
        }else {
            layer.msg("请先填写正确的验证码！");
        }
    });

    //表单输入效果
    $(".loginBody .input-item").click(function (e) {
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    });
    $(".loginBody .layui-form-item .layui-input").focus(function () {
        $(this).parent().addClass("layui-input-focus");
    });
    $(".loginBody .layui-form-item .layui-input").blur(function () {
        $(this).parent().removeClass("layui-input-focus");
        if ($(this).val() != '') {
            $(this).parent().addClass("layui-input-active");
        } else {
            $(this).parent().removeClass("layui-input-active");
        }
    })
});
