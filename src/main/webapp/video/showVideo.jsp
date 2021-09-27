<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<script>
    //延迟加载
    $(function () {
        pageInit();
    });

    //初始化表格的方法
    function pageInit() {

        //选择Table初始化表格
        $("#videoTable").jqGrid({
            url: "${path}/video/queryByPage",
            editurl: "${path}/video/edit",
            datatype: "json",
            rowNum: 3,
            rowList: [3, 20, 30],
            pager: '#videoPage',
            mtype: "get",
            viewrecords: true,  //是否展示总条数
            height: "auto",
            autowidth: true,
            styleUI: "Bootstrap",
            colNames: ['Id', '标题', '内容详情', '视频地址', '封面地址', '上传时间', '点赞数', '播放数', "类别ID", '分组ID', '用户ID'],
            colModel: [
                {name: 'id', width: 55},
                {name: 'title', editable: true, width: 50},
                {name: 'description', editable: true, width: 50},
                {
                    name: 'videoPath', editable: true, width: 60, align: "center", edittype: "file",
                    //cellvalue:headImg对应的值   options：对表格设置的样式操作    rowObject： 一行数据
                    formatter: function (cellvalue, options, row) {
                        return "<video src='" + cellvalue + "' width='100px' height='100px' controls poster='" + row.coverPath + "'>";
                    }
                },
                {name: 'coverPath', hidden: true, width: 80, align: "center"},
                {name: 'uploadTime', width: 80, align: "right"},
                {name: 'likeCount', width: 80},
                {name: 'playCount', width: 60},
                {name: 'cateId', width: 60},
                {name: 'groupId', width: 60},
                {name: 'userId', width: 60}
            ]
        });
        //初始化分页工具栏
        $("#videoTable").jqGrid('navGrid', '#videoPage', {edit: true, add: true, del: true},
            {
                closeAfterEdit: true,
                //修改后的操作
            },
            {
                closeAfterAdd: true,
                //添加后的操作
                afterSubmit: function (data) {
                    $.ajaxFileUpload({
                        url: "${path}/video/fileUpload",
                        type: "post",
                        datatype: "json",
                        data: {
                            "videoId": data.responseText
                        },
                        fileElementId: "videoPath",
                        success: function () {
                            $("#videoTable").trigger("reloadGrid");
                        }
                    });
                    return "hello";
                }
            },
            {},
            {});

    }


</script>


<%--初始化面板--%>
<div class="panel panel-info">

    <%--设置面板头--%>
    <div class="panel panel-heading">视频信息</div>

    <%--设置标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home">视频信息</a></li>
    </ul>
    <%--初始化表格--%>
    <table id="videoTable"/>

    <%--初始化分页工具栏--%>
    <div id="videoPage"/>

</div>