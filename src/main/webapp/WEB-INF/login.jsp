<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 19.01.2024
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body><%if (session.getAttribute("msg") != null) {%>
<span style="color: red"><%=session.getAttribute("msg")%></span>
<%}%>
<form action="/login" method="post">
    email: <input type="text" name="email"><br>
    password: <input type="password" name="password"><br>
    <input type="submit" value="login">
</form>

</body>
</html>
