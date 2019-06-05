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
        function moveToRevert() {
            if (confirm("确定要还原记录吗?")) {
                document.f1.action = "${path}/card2/revert";
            }
        }

    function deleteconfirm(id) {
            if (confirm("要删除该记录吗？")) {
                location.href = "${path}/card2/delete?id=" + id;
            }
        }

        function deletechoose() {
            if (confirm("要删除所选吗？")) {
                document.f1.action = "${path}/card2/deleteList";
            }
        }

        function revert() {
            location.href = "${path}/card/find";
        }

        function selectall() {
            var a = f1.checkList.length;
            if (a!=undefined){
                for (var i=0; i<a;i++){
                    f1.checkList[i].checked = true;
                }
                f1.checkList.checked = true;
            }
        }

        function unselectall() {
            var a = f1.checkList.length;
            if (a!=undefined){
                for (var i=0; i<a;i++){
                    f1.checkList[i].checked = false;
                }
                f1.checkList.checked = false;
            }
        }

    </script>
</head>
<body>
<h3 align="center">回收站信息管理</h3>
<form action="${path}/card2/find" method="post">
    <div align="center">
        名片搜索：<input type="text" name="condition"><input type="submit" value="查询">
    </div>
</form>
<br><br>
<s:form method="POST" name="f1">
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
                    <a href="javascript:deleteconfirm('${card.id}')">删除</a>
                </td>
            </tr>
        </s:iterator>
    </table>
    <table align="center">
        <tr>
            <td><input type="button" value="全选"  onclick="selectall()"></td>
            <td><input type="button" value="取消全选"  onclick="unselectall()"></td>
            <td><input type="submit" value="彻底删除所选" onclick="deletechoose()"></td>
            <td><input type="submit" value="还原所选" onclick="moveToRevert()"></td>
            <td><input type="button" value="进入名片管理" onclick="revert()"></td>
        </tr>
    </table>
</s:form>
</body>
</html>
