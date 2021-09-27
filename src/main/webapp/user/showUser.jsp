<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<script>
    //延迟加载
    $(function () {
        pageInit();
    });

    function poiData() {
        $.get("${path}/user/poiUserData", null, function (data) {
            alert(data.message);
        }, "json");
    };

    //初始化表格的方法
    function pageInit() {

        //选择Table初始化表格
        $("#userTable").jqGrid({
            url: "${path}/user/queryByPage",
            editurl: "${path}/user/edit",
            datatype: "json",
            rowNum: 3,
            rowList: [3, 20, 30],
            pager: '#userPage',
            mtype: "get",
            viewrecords: true,  //是否展示总条数
            height: "auto",
            autowidth: true,
            styleUI: "Bootstrap",
            colNames: ['Id', '用户名', '微信', '签名', '头像', '手机号', '状态', "创建时间"],
            colModel: [
                {name: 'id', width: 55},
                {name: 'name', editable: true, width: 50},
                {name: 'wechat', editable: true, width: 50},
                {name: 'sign', editable: true, width: 80, align: "right"},
                {
                    name: 'headImg', editable: true, width: 60, align: "center", edittype: "file",
                    //cellvalue:headImg对应的值   options：对表格设置的样式操作    rowObject： 一行数据
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img src='" + cellvalue + "' width='100px' height='100px' >";
                    }
                },
                {name: 'photo', editable: true, width: 80, align: "right"},
                {
                    name: 'status', width: 80, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        if (cellvalue == 1) {
                            //正常用户  点击冻结   //updateStatus("+rowObject.id+","+rowObject.status+")
                            return "<button class='btn btn-success' onclick='updateStatus(\"" + rowObject.id + "\",\"" + rowObject.status + "\")' >冻结</button>";
                        } else {
                            //已冻结用户  点击解除冻结
                            return "<button class='btn btn-danger' onclick='updateStatus(\"" + rowObject.id + "\",\"" + rowObject.status + "\")'>解除冻结</button>";
                        }
                    }
                },
                {name: 'registTime', width: 60}
            ]
        });

        //初始化分页工具栏
        $("#userTable").jqGrid('navGrid', '#userPage', {edit: true, add: true, del: true},
            {
                closeAfterEdit: true,
                //修改后的操作
            },
            {
                closeAfterAdd: true,
                //添加后的操作
                afterSubmit: function (data) {
                    $.ajaxFileUpload({
                        url: "${path}/user/fileUpload",
                        type: "post",
                        datatype: "json",
                        data: {
                            "userId": data.responseText
                        },
                        fileElementId: "headImg",
                        success: function () {
                            $("#userTable").trigger("reloadGrid");
                        }
                    });
                    return "hello";
                }
            },
            {},
            {});

    }

    //修改状态的方法
    function updateStatus(id, status) {
        //修改数据最少要几个参数   id   修改的字段(状态)
        if (status == 0) {
            $.post("${path}/user/updateStatus", {"id": id, "status": "1"}, function () {
                //刷新表单
                $("#userTable").trigger("reloadGrid");
            })
        } else {
            $.post("${path}/user/updateStatus", {"id": id, "status": "0"}, function () {
                //刷新表单
                $("#userTable").trigger("reloadGrid");
            })
        }
    }

</script>


<%--初始化面板--%>
<div class="panel panel-info">

    <%--设置面板头--%>
    <div class="panel panel-heading">用户信息</div>

    <%--设置标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home">用户信息</a></li>
    </ul>

    &emsp;&emsp;<button class="btn btn-success" onclick="poiData()">导出用户信息</button>
    &emsp;&emsp;<button class="btn btn-info">导出用户信息</button>
    <br><br>

    <%--初始化表格--%>
    <table id="userTable"/>

    <%--初始化分页工具栏--%>
    <div id="userPage"/>

</div>