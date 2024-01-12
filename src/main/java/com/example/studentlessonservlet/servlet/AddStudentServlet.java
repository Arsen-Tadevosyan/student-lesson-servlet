package com.example.studentlessonservlet.servlet;

import com.example.studentlessonservlet.manager.LessonManager;
import com.example.studentlessonservlet.manager.StudentManager;
import com.example.studentlessonservlet.model.Lesson;
import com.example.studentlessonservlet.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addStudent")
public class AddStudentServlet extends HttpServlet {
    StudentManager studentManager = new StudentManager();
    LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonManager.getLessons();
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/WEB-INF/addStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentName = req.getParameter("studentName");
        String studentSurname = req.getParameter("studentSurname");
        String studentEmail = req.getParameter("studentEmail");
        String studentAge = req.getParameter("studentAge");
        String studentLessonId = req.getParameter("studentLesson_id");
        Lesson lesson = lessonManager.getById(Integer.parseInt(studentLessonId));
        studentManager.add(Student.builder()
                .name(studentName)
                .surname(studentSurname)
                .email(studentEmail)
                .age(Integer.parseInt(studentAge))
                .lesson(lesson)
                .build());
        resp.sendRedirect("/students");
    }
}
