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
        $("#logTable").jqGrid({
            url: "${path}/log/queryByPage",
            datatype: "json",
            rowNum: 3,
            rowList: [3, 20, 30],
            pager: '#logPage',
            mtype: "get",
            viewrecords: true,  //是否展示总条数
            height: "auto",
            autowidth: true,
            styleUI: "Bootstrap",
            colNames: ['Id', '管理员名称', '操作时间', '方法名称', '状态'],
            colModel: [
                {name: 'id', width: 55},
                {name: 'adminName', width: 50},
                {name: 'optionTime', width: 50},
                {name: 'methodName', width: 60},
                {name: 'status', width: 60}
            ]
        });
        //初始化分页工具栏
        $("#logable").jqGrid('navGrid', '#logPage', {edit: true, add: true, del: true},
            {
                closeAfterEdit: true,
                //修改后的操作
            },
            {
                closeAfterAdd: true,
            },
            {},
            {});

    }


</script>


<%--初始化面板--%>
<div class="panel panel-info">

    <%--设置面板头--%>
    <div class="panel panel-heading">日志信息</div>

    <%--设置标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home">日志信息</a></li>
    </ul>
    <%--初始化表格--%>
    <table id="logTable"/>

    <%--初始化分页工具栏--%>
    <div id="logPage"/>

</div>