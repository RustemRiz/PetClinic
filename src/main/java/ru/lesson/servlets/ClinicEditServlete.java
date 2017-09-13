package ru.lesson.servlets;

import ru.lesson.models.*;
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
        System.out.println(cache.get(Integer.valueOf(req.getParameter("id"))));
        req.getRequestDispatcher(String.format("%s%s",req.getContextPath(),"/views/clinic/ClinicEdit.jsp")).forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Pet pet = PetFactory.createPet(PetType.getPetTypeByString(req.getParameter("petType")),req.getParameter("petName"));
        this.cache.edit(new Client(Integer.valueOf(req.getParameter("id")),req.getParameter("clientName"),pet));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/clinic/view"));
    }
    @Override
    public void destroy(){
        super.destroy();
        cache.close();
    }
}
