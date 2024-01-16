<%@ page import="com.example.studentlessonservlet.model.Student" %>
<%@ page import="com.example.studentlessonservlet.model.Lesson" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.01.2024
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddLesson</title>
</head>
<body>
<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons"); %>

<form method="post" action="/addStudent" enctype="multipart/form-data">
    Student name: <input type="text" name="studentName"><br>
    Student surname: <input type="text" name="studentSurname"><br>
    Student email: <input type="text" name="studentEmail"><br>
    Student age: <input type="number" name="studentAge"><br>
    <select name="studentLesson_id">
        <%
            for (Lesson lesson : lessons) {%>
        <option value="<%=lesson.getId()%>">
            <%=lesson.getName()%>
        </option>
        <%}%>
    </select><br>
    <input type="file" name="picture">
    <input type="submit" value="add">
</form>

</body>
</html>
