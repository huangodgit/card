<%--
  Created by IntelliJ IDEA.
  User: Yaphets
  Date: 2019/4/22
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>
<html>
<head>
    <title>register</title>
</head>
<body>
<s:fielderror cssStyle="color:red;"></s:fielderror>
<font color="red"><s:property value="msg"></s:property> </font><br>
<h3 align="left">欢迎注册我们的系统，请认真填写您的信息</h3>
<form name="register" action="${path }/user/register.action" method="post">
    <table>
        <tr>
            <td align="right">账户名：</td>
            <td><input type="text" name="user.userName" value="${user.userName}"></td>
        </tr>
        <tr>
            <td align="right">为您的账户设置密码：</td>
            <td><input type="password" name="user.userPassword"></td>
        </tr>
        <tr>
            <td align="right">再次确认您的密码：</td>
            <td><input type="password" name="re_password"></td>
        </tr>
        <tr>
            <td align="right">真实姓名：</td>
            <td><input type="text" name="user.userRealName" value="${user.userRealName }"></td>
        </tr>
        <tr>
            <td align="right"><input type="submit" value="提交"></td>
            <td colspan="2"><input type="reset" value="重新填写"></td>
        </tr>
    </table>
</form>
</body>
</html>
