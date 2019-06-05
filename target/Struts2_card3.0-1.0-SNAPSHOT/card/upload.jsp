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
</head>
<body>
上传Excel文件，其电子表
<s:form action="/card/upload" method="POST" enctype="multipart/form-data">
    <s:file name="file" label="提交文件"></s:file> <br><br>
    <s:submit value="提交"></s:submit>
</s:form>
</body>
</html>
