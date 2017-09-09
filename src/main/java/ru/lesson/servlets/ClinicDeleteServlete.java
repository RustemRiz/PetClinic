package ru.lesson.servlets;

import ru.lesson.store.ClientCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет для удаления клиентов
 * Created by Рустем on 02.09.2017.
 */
public class ClinicDeleteServlete extends HttpServlet {
    private final ClientCache cache = ClientCache.getInstance();
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        this.cache.delete(Integer.parseInt(req.getParameter("id")));
        response.sendRedirect(String.format("%s%s", req.getContextPath(), "/clinic/view"));
    }
}
