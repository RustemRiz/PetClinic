package ru.lesson.servlets;

import ru.lesson.store.ClientCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Рустем on 21.09.2017.
 */
public class DeletePetServlete extends HttpServlet {

    private ClientCache cache = ClientCache.getInstance();

    //Удаление питомца и возврат на страницу редактирования
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        cache.deletePet(Integer.valueOf(req.getParameter("petId")));
        resp.sendRedirect(String.format("%s%s%s", req.getContextPath(), "/clinic/edit?id=",req.getParameter("id")));
    }

    @Override
    public void destroy(){
        super.destroy();
        cache.close();
    }
}
