package com.example.studentlessonservlet.servlet;

import com.example.studentlessonservlet.manager.LessonManager;
import com.example.studentlessonservlet.manager.StudentManager;
import com.example.studentlessonservlet.model.Lesson;
import com.example.studentlessonservlet.model.Student;
import com.example.studentlessonservlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addStudent")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024 * 1
)
public class AddStudentServlet extends HttpServlet {
    StudentManager studentManager = new StudentManager();
    LessonManager lessonManager = new LessonManager();
    private final String UPLOAD_DIRECTORY = "C:\\Users\\User\\IdeaProjects\\student-lesson-servlet\\uploadDirectory";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Lesson> lessons = lessonManager.getLessonByUser(user);
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
        Part picture = req.getPart("picture");
        User user = (User) req.getSession().getAttribute("user");

        String pictureName = null;
        if (picture != null && picture.getSize() > 0) {
            pictureName = System.currentTimeMillis() + "_" + picture.getSubmittedFileName();
            picture.write(UPLOAD_DIRECTORY + File.separator + pictureName);
        }
        Student byEmail = studentManager.getByEmail(studentEmail);
        if (byEmail == null) {
            Lesson lesson = lessonManager.getById(Integer.parseInt(studentLessonId));
            studentManager.add(Student.builder()
                    .name(studentName)
                    .surname(studentSurname)
                    .email(studentEmail)
                    .age(Integer.parseInt(studentAge))
                    .lesson(lesson)
                    .picName(pictureName)
                    .user(user)
                    .build());
            resp.sendRedirect("/students");
        } else {
            req.getSession().setAttribute("msg", "there already is");
            resp.sendRedirect("/students");
        }

    }
}
