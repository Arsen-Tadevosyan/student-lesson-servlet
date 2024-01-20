package com.example.studentlessonservlet.servlet;

import com.example.studentlessonservlet.manager.UserManager;
import com.example.studentlessonservlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private UserManager userManager = new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User userByEmail = userManager.getUserByEmail(email);
        if (userByEmail == null) {
            userManager.add(User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .build());
            resp.sendRedirect("/");
        } else {
            resp.sendRedirect("/");
        }

    }
}
