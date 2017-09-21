package ru.lesson.servlets;

import ru.lesson.models.*;
import ru.lesson.store.ClientCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Сервлет для добавления клиента
 * Created by Рустем on 02.09.2017.
 */
public class ClinicAddServlete extends HttpServlet {
    private final ClientCache cache = ClientCache.getInstance();
    //Страница добавления клиента
    private static final String ADD_CLIENT = "/views/clinic/ClinicAdd.jsp";
    // Страница обзора клиентов
    private static final String CLIENT_VIEW = "/views/clinic/ClinicView.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher(ADD_CLIENT).forward(req,resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        addClient(req);
        req.setAttribute("addClient",null);
        req.getRequestDispatcher(CLIENT_VIEW).forward(req,resp);
    }
    //Добавление клиента в БД
    private void addClient(HttpServletRequest req){
        cache.add(new Client(req.getParameter("clientName"), null));
    }
    @Override
    public void destroy(){
        super.destroy();
        cache.close();
    }
}
