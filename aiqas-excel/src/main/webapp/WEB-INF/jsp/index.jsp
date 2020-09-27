<%--
  Created by IntelliJ IDEA.
  User: heqiang
  Date: 2019/3/18
  Time: 8:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h5>欢迎使用JSP！！！</h5>
${param1}<br>
${param2}<br>
${param3}
<hr>
<form name="form" method="post" action="/controller/swagger-ui" title="swagger">
    　　 <input type="submit" value="跳转到swagger-ui">
</form>
<hr>
<a href="${pageContext.request.contextPath}/controller/files"></a>



</body>

</html>
