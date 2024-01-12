<%@ page import="com.example.studentlessonservlet.model.Lesson" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.01.2024
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lessons</title>
</head>
<body>
<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons"); %>

<span>Lessons</span><a href="/addLesson">AddLesson</a>

<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>duration</th>
        <th>lecturerName</th>
        <th>price</th>
        <th>delete</th>
    </tr>
    <%
        if (!lessons.isEmpty()) {
            for (Lesson lesson : lessons) {%>
    <tr>
        <td><%=lesson.getId()%>
        </td>
        <td><%=lesson.getName()%>
        </td>
        <td><%=lesson.getDuration()%>
        </td>
        <td><%=lesson.getLecturerName()%>
        </td>
        <td><%=lesson.getPrice()%>
        </td>
        <td><a href="/deleteLesson?id=<%=lesson.getId()%>">delete</a></td>
    </tr>
    <% }
    }
    %>
</table>

</body>
</html>
