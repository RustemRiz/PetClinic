package ru.lesson.servlets;

import ru.lesson.models.Client;
import ru.lesson.models.Dog;
import ru.lesson.store.ClientCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Рустем on 02.09.2017.
 */
public class ClinicViewServlete extends HttpServlet {
    private static final String ADD_CLIENT = "/views/clinic/ClinicAdd.jsp";
    private static final String CLIENT_VIEW = "/views/clinic/ClinicView.jsp";
     /* *Синглтон для хранения клиентов*/
    ClientCache cache = ClientCache.getInstance();
     /** Список клиентов для отображения (удовлетворяющий фильтрам поиска) */
    private List<Client> clientsToShow = new CopyOnWriteArrayList<>();
    {
        clientsToShow.addAll(cache.values());

    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); //Для корректной работы с кирилицей
        processSearchFilters(req);
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); //Для корректной работы с кирилицей
        processAddClient(req,resp);
        processNotAddClient(req,resp);
   }

   //Переход на страницу добавления клиента
    private void processAddClient(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
        if(req.getParameter("addClient")!=null){
            req.getRequestDispatcher(ADD_CLIENT).forward(req,resp);
        }
    }


    private void processNotAddClient(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        if(req.getParameter("addClient")==null){
            setDoGetAttributes(req);
            req.getRequestDispatcher(CLIENT_VIEW).forward(req,resp);
        }
    }

    /**
     * Установка атрибутов метода doGet
     * @param req Запрос
     */
    private void setDoGetAttributes(HttpServletRequest req) {
        req.setAttribute("clients", clientsToShow);
        req.setAttribute("showClients", req.getParameter("search") != null);
    }



    /**
     * Обработка фильтров поиска заданных пользователем на странице
     * @param req Запрос
     */
    private void processSearchFilters(HttpServletRequest req) {
        if (req.getParameter("search") != null) {
            StringBuffer sb = new StringBuffer();
            sb.append("%").append(req.getParameter("findClientName")).append("%");
            String clientName = sb.toString();
            sb.setLength(0);
            sb.append("%").append(req.getParameter("findPetName")).append("%");
            String petName = sb.toString();
            String petType;
            if (req.getParameter("findPetType").equals("3")) petType = "";
            else petType = req.getParameter("findPetType");
            clientsToShow = cache.find(clientName,petName, petType);
        }
    }

    @Override
    public void destroy(){
        super.destroy();
        cache.close();
    }



}

