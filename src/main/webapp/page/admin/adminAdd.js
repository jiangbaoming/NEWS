layui.config({
    base: "/statics/js/"
}).extend({
    "treeSelect": "treeSelect"
});
layui.use(['form', 'layer', 'treeSelect'], function () {
    const form = layui.form,
        layer = layui.layer,
        treeSelect = layui.treeSelect;

    //添加验证规则
    form.verify({
        password: function (value, item) {
            if (value.length < 6) {
                return "密码长度不能小于6位";
            } else if (value.length > 13) {
                return "密码长度不能大于13位";
            }
        },
        rePassword: function (value, item) {
            if (!new RegExp($("#password").val()).test(value)) {
                return "两次输入密码不一致，请重新输入！";
            }
        },
        phone: function (value, item) {
            var reg = /^1[34578]\d{9}$/;
            if (!reg.test(value)) {
                return "手机号只能是13|14|15|17|18开头的11位数字！";
            }
        },
        userCode: function (value, item) {
            var  result='';
            $.ajax({
                url: '/user',
                dataType: 'json',
                type: 'post',
                async: false,
                data: {
                    method: 'userCodeIsExit',
                    userCode: value,
                    user:$.cookie('user'),
                },
                success: function (data) {
                    if (data.code !=200) {
                        result=data.msg;
                    }
                }
            });
            return result;
        }
    });

    treeSelect.render({
        // 选择器
        elem: '#tree',
        // 数据
        data: '/organization?method=getAll',
        // 异步加载方式：get/post，默认get
        type: 'get',
        // 占位符
        placeholder: '请选择部门',
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
            updataDid = d.current.id;
            // layer.msg(updataDid);
        },
        // 加载完成后的回调函数
        success: function (d) {
            //               console.log(d);
//                选中节点，根据id筛选
//                treeSelect.checkNode('tree', did);
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
            type: "POST",
            dataType: "json",
            data: {
                "method": "add",
                "userCode": field.userCode,
                "userName": field.userName,
                "password": field.password,
                "oid": updataDid,
                "phone": field.phone,
                "role": field.role,
                user:$.cookie('user'),
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