package ru.job4j.servlet;

import ru.job4j.model.User;
import ru.job4j.store.UserServStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        req.setCharacterEncoding("UTF-8");
        if (UserServStore.instOf().findByEmailUser(email) != null) {
            req.setAttribute("error", "Пользователь с указанным email уже зарегистрирован");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }
        String username = req.getParameter("name");
        String password = req.getParameter("password");
        UserServStore.instOf().createUser(new User(username, email, password));
        resp.sendRedirect("login.jsp");
    }
}
