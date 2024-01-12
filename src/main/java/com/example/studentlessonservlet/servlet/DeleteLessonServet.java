package com.example.studentlessonservlet.servlet;

import com.example.studentlessonservlet.manager.LessonManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteLesson")
public class DeleteLessonServet extends HttpServlet {
    LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        lessonManager.delete(id);
        resp.sendRedirect("/lessons");
    }
}
