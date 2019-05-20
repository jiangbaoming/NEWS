layui.use(['form', 'layer', 'layedit', 'upload'], function () {
    const form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        layedit = layui.layedit,
        $ = layui.jquery;



    //获取分类
    $.ajax({
        url:"/newsType",
        type: "POST",
        dataType: "json",
        data: {
           method:"getList",
        },
        success: function (result) {
            if (result.code ===200) {
                var str='<option value="">请选择类别</option>';
                for ( var i=0;i<result.data.length;i++) {
                    str+='<option value="'+result.data[i].tid+'">'+result.data[i].tName+'</option>';
                }
                $("select").html(str);
                form.on('select(type)', function(data){
                    $(".category").val(data.value);
                });
                form.render('select');
            } 
        }
    });

    //创建一个编辑器
   /* const editIndex = layedit.build('news_content', {
        height: 500,
        uploadImage: {
            url: "http://localhost:8080/uploadServlet"
        }
    });*/
    var ue = UE.getEditor('ueditor',{zIndex: 100});

    //封面图上传
    let coverUrl = null;
    const uploadInst = upload.render({
        elem: '#test1'
        , url: "http://localhost:8080/uploadServlet"
        , method: 'post'  //可选项。HTTP类型，默认post
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#demo1').attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res) {
            //如果上传失败
            if (res.code > 0) {
                return layer.msg('图片上传失败');
            } else {
                //上传成功
                coverUrl = res.data.src;
                return layer.msg('图片上传成功');
            }
        }
        , error: function () {
            //演示失败状态，并实现重传
            const demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });

    form.verify({
        articleTitle: function (val) {
            if (val.length > 32) {
                return "文章标题过长";
            }
        },
        introduction: function (val) {
            if (val.length > 127) {
                return "简介内容过长";
            }
        },
        type:function (val) {
            if ($(".category").val()==''|| $(".category").val() ==null) {
                return "请选择栏目类别";
            }
        }
    });

    form.on("submit(addNews)", function (data) {
        //弹出loading
        const index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        setTimeout(function () {
            $.ajax({
                url:"/news",
                type: "POST",
                dataType: "json",
                data: {
                    method:'add',
                    title: $(".articleTitle").val(),
                    introduction: $(".introduction").val(),
                    tid: $(".category").val(),
                    banner: coverUrl,
                    content: ue.getContent(),
                    user:$.cookie('user'),
                },
                success: function (result) {
                    if (result.code ===200) {
                        layer.msg("新增成功");
                        setTimeout(function () {
                            top.layer.close(index);
                            layer.closeAll("iframe");
                            //刷新父页面
                            parent.location.reload();
                        }, 500);
                    } else {
                        layer.msg("新增失败!");
                    }
                }
            });
        },1000);
        return false;
    });
});