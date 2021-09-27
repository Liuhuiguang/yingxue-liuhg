<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script>
    $(function () {
        pageInit();
    });

    function pageInit() {
        $("#categoryTable").jqGrid(
            {
                url: "${path}/category/queryByPage",
                datatype: "json",
                colNames: ['id', '类别名', '类别', '父类别'],
                rowNum: 8,
                rowList: [8, 10, 20, 30],
                pager: '#categoryPage',
                viewrecords: true,
                styleUI: "Bootstrap",
                autowidth: true,
                height: "auto",
                subGrid: true,
                mtype: "get",
                caption: "分类信息",
                colModel: [
                    {name: 'id', width: 55},
                    {name: 'cateName', width: 90},
                    {name: 'levels', width: 100},
                    {name: 'parent_id', width: 55, hidden: true},
                ],
                subGridRowExpanded: function (subgrid_id, row_id) {
                    // we pass two parameters
                    // subgrid_id is a id of the div tag created whitin a table data
                    // the id of this elemenet is a combination of the "sg_" + id of the row
                    // the row_id is the id of the row
                    // If we wan to pass additinal parameters to the url we can use
                    // a method getRowData(row_id) - which returns associative array in type name-value
                    // here we can easy construct the flowing
                    addSubGrid(subgrid_id, row_id);
                }

            });

        $("#categoryTable").jqGrid('navGrid', '#categoryPage', {
            add: true,
            edit: true,
            del: true
        });

        function addSubGrid(subgrid_id, row_id) {
            // this function is called before removing the data
            //var subgrid_table_id;
            //subgrid_table_id = subgrid_id+"_t";
            //jQuery("#"+subgrid_table_id).remove(); var subgrid_table_id, pager_id;
            subgrid_table_id = subgrid_id + "_t";
            pager_id = "p_" + subgrid_table_id;
            $("#" + subgrid_id).html(
                "<table id='" + subgrid_table_id
                + "' class='scroll'></table><div id='"
                + pager_id + "' class='scroll'></div>");
            jQuery("#" + subgrid_table_id).jqGrid(
                {
                    url: "${path}/category/queryByPage?parent_id=" + row_id,
                    datatype: "json",
                    colNames: ['ID', '类别名', '类别', '父类别ID'],
                    rowNum: 20,
                    pager: pager_id,
                    autowidth: true,
                    styleUI: "Bootstrap",
                    height: "auto",
                    colModel: [
                        {name: "id", width: 80},
                        {name: "cateName", width: 130},
                        {name: "levels", width: 70},
                        {name: "parentId", width: 70},
                    ]

                });
            $("#" + subgrid_table_id).jqGrid('navGrid',
                "#" + pager_id, {
                    edit: true,
                    add: true,
                    del: true
                });
        }
    }
</script>


<%--初始化面板--%>
<div class="panel panel-success">

    <%--设置面板头--%>
    <div class="panel panel-heading">分类信息</div>

    <%--设置标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home">分类信息</a></li>
    </ul>

    <%--初始化表格--%>
    <table id="categoryTable"/>

    <%--初始化分页工具栏--%>
    <div id="categoryPage"/>

</div>