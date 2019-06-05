<%--
  Created by IntelliJ IDEA.
  User: Yaphets
  Date: 2019/4/22
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();
  pageContext.setAttribute("path", path);
%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>名片管理系统</h1>
  <a href="${path }/user/login.jsp">用户管理子系统</a><br><br>
  <a href="${path }/card/find">名片管理子系统</a><br><br>
  </body>
</html>
