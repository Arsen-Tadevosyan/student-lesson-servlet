<%--
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
<form method="post" action="/addLesson">
    Lesson name: <input type="text" name="lessonName"><br>
    Lesson Duration: <input type="text" name="lessonDuration"><br>
    Lesson LecturerName: <input type="text" name="lessonLecturerName"><br>
    Lesson Price: <input type="number" name="lessonPrice"><br>
    <input type="submit" value="add">
</form>

</body>
</html>
