package ru.lesson.servlets;

import ru.lesson.models.Client;
import ru.lesson.models.Dog;
import ru.lesson.store.ClientCache;
import sun.io.ByteToCharMacHebrew;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Рустем on 03.09.2017.
 */
public class ClinicEditServlete extends HttpServlet {
   ClientCache cache = ClientCache.getInstance();


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("client", cache.get(Integer.valueOf(req.getParameter("id"))));
        req.getRequestDispatcher(String.format("%s%s",req.getContextPath(),"/views/clinic/ClinicEdit.jsp")).forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.cache.edit(new Client(Integer.valueOf(req.getParameter("id")),req.getParameter("clientName"),new Dog(req.getParameter("petName"))));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/clinic/view"));
    }
}
