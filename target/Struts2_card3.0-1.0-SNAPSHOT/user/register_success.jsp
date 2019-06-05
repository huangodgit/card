<%--
  Created by IntelliJ IDEA.
  User: Yaphets
  Date: 2019/4/22
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>
<html>
<head>
    <title>注册成功</title>
</head>
<body>
<h3>恭喜，${user.userRealName},你成功注册了我们的管理系统！点此</h3>
<a href="${path}/user/login.jsp">登录</a>
</body>
</html>
