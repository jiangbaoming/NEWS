//获取系统时间
let newDate = '';
getLangDate();

//值小于10时，在前面补0
function dateFilter(date) {
    if (date < 10) {
        return "0" + date;
    }
    return date;
}

function getLangDate() {
    const dateObj = new Date(); //表示当前系统时间的Date对象
    const year = dateObj.getFullYear(); //当前系统时间的完整年份值
    const month = dateObj.getMonth() + 1; //当前系统时间的月份值
    const date = dateObj.getDate(); //当前系统时间的月份中的日
    const day = dateObj.getDay(); //当前系统时间中的星期值
    const weeks = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
    const week = weeks[day]; //根据星期值，从数组中获取对应的星期字符串
    const hour = dateObj.getHours(); //当前系统时间的小时值
    const minute = dateObj.getMinutes(); //当前系统时间的分钟值
    const second = dateObj.getSeconds(); //当前系统时间的秒钟值
    const timeValue = "" + ((hour >= 12) ? (hour >= 18) ? "晚上" : "下午" : "上午"); //当前时间属于上午、晚上还是下午
    newDate = dateFilter(year) + "年" + dateFilter(month) + "月" + dateFilter(date) + "日 " + " " + dateFilter(hour) + ":" + dateFilter(minute) + ":" + dateFilter(second);
    document.getElementById("nowTime").innerHTML = "亲爱的" + $.cookie("truename") + "，" + timeValue + "好！ 欢迎使用后台管理系统。当前时间为： " + newDate + "　" + week+"（清除浏览器缓存后，请刷新地址栏！）";
    setTimeout("getLangDate()", 1000);
}
layui.use(['form', 'layer', 'table'], function () {
        const form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer,
            $ = layui.jquery,
            table = layui.table;

    if ($.cookie("truename") == null || $.cookie("truename") === "") {
        window.location.href = "/login.html";
    }
        //根据用户角色获取不同的news列表
        //列表
        const tableIns = table.render({
            elem: '#list',
            url: '/news',
            where: {
                method:'getList',
            },
            method: "GET",
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
          /*  height: "full-25",*/
            limits: [5, 10, 15, 20, 25],
            limit: 15,
            id: "dataTable",
            toolbar: '#toolbarDemo',
            defaultToolbar: [],
            cols: [[
                {field: 'nid', title: 'ID', width: 90, align: 'center'},
                {field: 'uName', title: '作者', width: 110, align: 'center'},
                {
                    field: 'title', title: '标题', minWidth: 200, align: "left", templet: function (d) {
                        return '<a lay-event="edit" style="cursor:pointer;color: #01AAED">' + d.title + '</a>';
                    }
                },
                {field: 'introduction', title: '简介', minWidth: 300, align: 'left'},
                {field: 'tName', title: '文章类别', width: 90, align: 'center'},
                {
                    field: 'releaseDate', title: '发布时间', width: 200, align: "center", templet: function (d) {
                        return d.releaseDate;
                    }
                },
                {field: 'times', title: '浏览次数', width: 90, align: 'center'},
                {title: '操作', width: 200, templet: '#userListBar', fixed: "right", align: "center"}
            ]]
        });

        //头工具栏事件
        table.on('toolbar(test)', function (obj) {
            switch (obj.event) {
                case 'search_btn':
                    table.reload("dataTable", {
                        url: '/news',
                        where: {
                            title: $(".searchVal").val(),
                            method: "search",
                        }
                    });
                    break;
                case 'flash_btn':
                    window.location.reload();
                    break;
                case 'add_btn':
                    const index = layui.layer.open({
                        title: "新增资讯",
                        type: 2,
                        area: ["500px", "450px"],
                        content: "/page/news/informationAdd.html",
                        shadeClose: true,
                        success: function () {
                            setTimeout(function () {
                                layui.layer.tips('点击此处关闭', '.layui-layer-setwin .layui-layer-close', {
                                    tips: 3
                                });
                            }, 100)
                        }
                    });
                    layui.layer.full(index);
                    window.sessionStorage.setItem("index", index);
                    //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
                    $(window).on("resize", function () {
                        layui.layer.full(window.sessionStorage.getItem("index"));
                    });
                    break;
            }
        });
        //列表操作
        table.on('tool(test)', function (obj) {
            const layEvent = obj.event,
                data = obj.data;
            console.log(data);
            switch (layEvent) {
                case 'edit'://编辑
                    const index = layui.layer.open({
                        title: "查看/更新资讯",
                        type: 2,
                        content: "/page/news/informationUpd.html",
                        success: function (layero, index) {
                            const body = layui.layer.getChildFrame('body', index);
                            body.find("#demo1").attr("src", data.banner);  //封面图
                            body.find(".id").val(data.nid);//新闻编号
                            body.find(".articleTitle").val(data.title);  //文章标题
                            body.find(".introduction").val(data.introduction);  //文章简介
                            body.find(".createDate").val(data.releaseDate);//发布时间
                            body.find("#tid").val(data.tid);//类别
                            body.find("#newsContent").val(data.content);//内容
                            form.render();
                            setTimeout(function () {
                                layui.layer.tips('点击此处关闭', '.layui-layer-setwin .layui-layer-close', {
                                    tips: 3
                                });
                            }, 100);
                        }
                    });
                    layui.layer.full(index);
                    window.sessionStorage.setItem("index", index);
                    //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
                    $(window).on("resize", function () {
                        layui.layer.full(window.sessionStorage.getItem("index"));
                    });
                    break;
                case 'del'://删除
                    layer.confirm('确定删除此资讯？', {icon: 3, title: '提示信息'}, function (index) {
                        $.ajax({
                            url:'/news',
                            type: "post",
                            data:{
                                method:'delete',
                                nid:data.nid,
                            },
                            success: function (result) {
                                layer.msg("删除成功");
                                // window.location.href = "informationList.html";
                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                // tableIns.reload();
                                layer.close(index);
                            }
                        });
                    });
                    break;
                case 'sort'://排序
                    const sort = layui.layer.open({
                        title: "排序(1-5[最低-最高])",
                        type: 2,
                        area: ["500px", "250px"],
                        content: "/page/news/informationSort.html",
                        shadeClose: true,
                        success: function (layero, index) {
                            const body = layui.layer.getChildFrame('body', index);
                            body.find("input[name=nid]").val(data.nid);
                            body.find("select[name=sort]").val(data.sorting);
                            form.render();
                        }
                    });
                    break;
            }
        });
    }
);

