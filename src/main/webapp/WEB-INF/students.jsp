<%@ page import="java.util.List" %>
<%@ page import="com.example.studentlessonservlet.model.Student" %>
<%@ page import="com.example.studentlessonservlet.manager.UserManager" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.01.2024
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<%
    List<Student> students = (List<Student>) request.getAttribute("students");
%>

<span>Students</span><a href="/addStudent">AddStudent</a>

<%if (session.getAttribute("msg") != null) {%>
<span style="color: red"><%=session.getAttribute("msg")%></span>
<%}%>

<table border="1">
    <tr>
        <th>id</th>
        <th>picture</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>age</th>
        <th>lesson</th>
        <th>User_name</th>
        <th>delete</th>
    </tr>
    <%
        if (!students.isEmpty()) {
            for (Student student : students) {
    %>
    <tr>
        <td><%=student.getId()%>
        </td>
        <td><%if (student.getPicName() != null) {%>
            <img src="/downloadImage?imageName=<%= student.getPicName()%>" width="30">
            <% } else {%>
            <span>no picture</span>
            <%}%>
        </td>
        <td><%=student.getName()%>
        </td>
        <td><%=student.getSurname()%>
        </td>
        <td><%=student.getEmail()%>
        </td>
        <td><%=student.getAge()%>
        </td>
        <td><%=student.getLesson().getName()%>
        </td>
        <td><%=student.getUser().getName()%>
        </td>
        <td><a href="/deleteStudent?id=<%=student.getId()%>">delete</a></td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>
