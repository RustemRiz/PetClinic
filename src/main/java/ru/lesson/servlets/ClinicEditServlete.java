package ru.lesson.servlets;

import ru.lesson.models.*;
import ru.lesson.store.ClientCache;

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
   private final String EDIT_PAGE = "/views/clinic/ClinicEdit.jsp";
   private Client currentClient;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        currentClient = cache.get(Integer.valueOf(req.getParameter("id")));
        req.setAttribute("client", currentClient);
        req.getRequestDispatcher(EDIT_PAGE).forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        updateData(req, resp);
    }

    private void updateData(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        editClient(req,resp);
        addPet(req,resp);
//        deletePet(req, resp);
    }

    /**
     *
     * Сохраняем изменения клиента
     * @param req Запрос
     */
    //TODO добавить измененния животных
    private void editClient(HttpServletRequest req,HttpServletResponse resp) throws IOException{
        if(req.getParameter("editSubmit")!= null) {
            if (!currentClient.getName().equals(req.getParameter("clientName"))) {
                currentClient.setName(req.getParameter("clientName"));
                this.cache.edit(currentClient);
            }
            resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/clinic/view"));
        }
    }

    private void addPet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("addPetSubmit") != null){
            //TODO добавить поддержку выбор типа питомца
            int type = Integer.valueOf(req.getParameter("newPetType"));
            Pet pet = new Animal(req.getParameter("newPetName"),new Pet_type(type),currentClient);
            cache.addObject(pet);
            req.setAttribute("id",currentClient.getId());
            doGet(req,resp);
        }
    }


    @Override
    public void destroy(){
        super.destroy();
        cache.close();
    }
}
