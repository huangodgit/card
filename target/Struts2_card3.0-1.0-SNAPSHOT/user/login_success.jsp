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
    <title>登录成功</title>
</head>
<body>
欢迎您，${user.userRealName},你登入成功！！<br>
欢迎进入名片管理系统，请点击 <a href="${path }/card/find">名片管理系统</a>
</body>
</html>
