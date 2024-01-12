package com.example.studentlessonservlet.servlet;

import com.example.studentlessonservlet.manager.LessonManager;
import com.example.studentlessonservlet.model.Lesson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/addLesson")
public class AddLessonServlet extends HttpServlet {
    LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lessonName = req.getParameter("lessonName");
        String lessonDuration = req.getParameter("lessonDuration");
        String lessonLecturerName = req.getParameter("lessonLecturerName");
        double lessonPrice = Double.parseDouble(req.getParameter("lessonPrice"));
        lessonManager.add(Lesson.builder()
                .name(lessonName)
                .duration(lessonDuration)
                .lecturerName(lessonLecturerName)
                .price(lessonPrice)
                .build());
        resp.sendRedirect("/lessons");
    }
}
