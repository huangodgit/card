<%--
  Created by IntelliJ IDEA.
  User: Yaphets
  Date: 2019/4/22
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>
<html>
<head>
    <title>Title</title>
    <script>
        function deleteconfirm(id) {
            if (confirm("要删除该记录吗？")) {
                location.href = "${path}/card/delete?id=" + id;
            }
        }

        function deletechoose() {
            if (confirm("要删除所选吗？")) {
                document.f2.action = "${path}/card/deleteList";
            }
        }

        function insert() {
            location.href = "${path}/card/insert.jsp";
        }

        function retrieve() {
            location.href = "${path}/card2/find";
        }

        function upload() {
            location.href = "${path}/card/upload.jsp";
        }

        function download() {
            location.href = "${path}/card/download";
        }

        function selectall() {
            var a = f2.checkList.length;
            if (a!=undefined){
                for (var i=0; i<a;i++){
                    f2.checkList[i].checked = true;
                }
                    f2.checkList.checked = true;
            }
        }

        function unselectall() {
            var a = f2.checkList.length;
            if (a!=undefined){
                for (var i=0; i<a;i++){
                    f2.checkList[i].checked = false;
                }
                f2.checkList.checked = false;
            }
        }

        function moveToRetrieve() {
            if (confirm("确实要将选择的记录移到回收站吗?")) {
                document.f2.action = "${path }/card/retrieve";
            }
        }
    </script>
</head>
<body>
<h3 align="center">名片浏览与查询</h3>
<form action="${path}/card/find" method="post">
    <div align="center">
        名片搜索：<input type="text" name="condition"><input type="submit" value="查询">
    </div>
</form>
<br><br>
<s:form method="POST" name="f2">
    <table align="center">
        <tr>
            <td><input type="button" value="添加" onclick="insert()"></td>
            <td><input type="button" value="全选"  onclick="selectall()"></td>
            <td><input type="button" value="取消全选"  onclick="unselectall()"></td>
            <td><input type="submit" value="彻底删除所选" onclick="deletechoose()"></td>
            <td><input type="submit" value="将所选移到回收站" onclick="moveToRetrieve()"></td>
            <td><input type="button" value="导入名片" onclick="upload()"></td>
            <td><input type="button" value="导出查询结果" onclick="download()"></td>
            <td><input type="button" value="进入回收站" onclick="retrieve()"></td>
        </tr>
    </table>
    <table width="70%" border="0" cellpadding="3" cellspacing="1" align="center">
        <tr bgcolor="#8899cc">
            <td></td>
            <td>编号</td>
            <td>姓名</td>
            <td>性别</td>
            <td>单位</td>
            <td>手机</td>
            <td>电话</td>
            <td>Email</td>
            <td>通讯地址</td>
            <td>操作</td>
        </tr>
        <s:iterator var="card" value="listCard" status="list">
            <tr>
                <td><input type="checkbox" name="checkList" value="${card.id}"></td>
                <td><s:property value="#card.id"/></td>
                <td><s:property value="#card.name"/></td>
                <td><s:property value="#card.sex"/></td>
                <td><s:property value="#card.department"/></td>
                <td><s:property value="#card.mobile"/></td>
                <td><s:property value="#card.phone"/></td>
                <td><s:property value="#card.email"/></td>
                <td><s:property value="#card.address"/></td>
                <td>
                    <a href="${path}/card/findUpdate?id=${card.id}">修改</a>
                    <a href="javascript:deleteconfirm('${card.id}')">删除</a>
                </td>
            </tr>
        </s:iterator>
    </table>
</s:form>
</body>
</html>
