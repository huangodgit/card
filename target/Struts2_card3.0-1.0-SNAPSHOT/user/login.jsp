<%--
  Created by IntelliJ IDEA.
  User: Yaphets
  Date: 2019/4/22
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<% String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<s:fielderror cssStyle="color:red;"></s:fielderror>
<font color="red"><s:property value="msg"></s:property> </font><br>
<form action="${path}/user/login.action" method="post">
    <table>
        <tr>
            <th colspan="2">用户登录</th>
        </tr>
        <tr>
            <td align="right">用户名：</td>
            <td><input type="text" name="user.userName" value="${user.userName}"></td>
        </tr>
        <tr>
            <td align="right">密码：</td>
            <td><input type="password" name="user.userPassword"></td>
        </tr>
        <tr>
            <td align="left"><input type="submit" value="登录"></td>
            <td>未注册者，请先注册，单击 <a href="${path }/user/register.jsp">注册</a></td>
        </tr>
    </table>
</form>
</body>
</html>
