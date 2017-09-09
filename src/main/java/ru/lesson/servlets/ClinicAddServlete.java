package ru.lesson.servlets;

import ru.lesson.models.Cat;
import ru.lesson.models.Client;
import ru.lesson.models.Dog;
import ru.lesson.store.ClientCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        cache.add(new Client(cache.generateId(), req.getParameter("clientName"), new Dog(req.getParameter("petName"))));
        req.getRequestDispatcher(CLIENT_VIEW).forward(req,resp);
    }

    private void addClient(HttpServletRequest req){
        if(req.getParameter("petType") == "1"){
            cache.add(new Client(cache.generateId(), req.getParameter("clientName"), new Dog(req.getParameter("petName"))));
        }
        else{
            cache.add(new Client(cache.generateId(), req.getParameter("clientName"), new Cat(req.getParameter("petName"))));
        }
    }
}
