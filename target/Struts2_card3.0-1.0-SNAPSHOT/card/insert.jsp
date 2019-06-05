<%--
  Created by IntelliJ IDEA.
  User: Yaphets
  Date: 2019/4/22
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.TreeMap" %>
<% String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>
<html>
<head>
    <title>insert</title>
</head>
<%
    TreeMap<String ,String> searchType = new TreeMap<>();
    searchType.put("男", "男");
    searchType.put("女", "女");
    request.setAttribute("searchType",searchType);
%>
<body>
<h3>添加名片</h3>
<s:form action="/card/insert" method="POST">
    <s:textfield label="姓名" name="card.name"></s:textfield>
    <s:radio
            list="#request.searchType" label="性别" value="'男'" name="card.sex">
    </s:radio>
    <s:textfield label="单位" name="card.department"></s:textfield>
    <s:textfield label="手机" name="card.mobile"></s:textfield>
    <s:textfield label="电话" name="card.phone"></s:textfield>
    <s:textfield label="Email" name="card.email"></s:textfield>
    <s:textfield label="地址" name="card.address"></s:textfield>
    <table>
        <tr>
            <td><s:submit value="提交" theme="simple"/> </td>
            <td><s:reset value="取消" theme="simple"/> </td>
        </tr>
    </table>
</s:form>
</body>
</html>
