<%@ page import="java.util.List" %>
<%@ page import="com.example.studentlessonservlet.model.Student" %><%--
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

<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>age</th>
        <th>lesson</th>
        <th>delete</th>
    </tr>
    <%
        if (!students.isEmpty()) {
            for (Student student : students) {
    %>
    <tr>
        <td><%=student.getId()%>
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
        <td><a href="/deleteStudent?id=<%=student.getId()%>">delete</a></td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>
