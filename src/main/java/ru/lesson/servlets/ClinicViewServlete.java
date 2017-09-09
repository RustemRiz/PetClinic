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
import java.util.concurrent.atomic.AtomicInteger;

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
        initialClinicFilling();
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
            String clientName = req.getParameter("findClientName");
            String petName = req.getParameter("findPetName");
            clientsToShow = cache.finded(clientName,petName);
        }
    }











// Методы предварительного заполнения клиники. Для тестов, чтобы меньше вводить через сайт.
    /**
     * Заполнение клиники клиентами Анна, Иван и Петр
     */
    private void initialClinicFilling() {
        cache.add(createAnna());
        cache.add(createIvan());
        cache.add(createPetr());
    }

    /**
     * Создание клиента Анна
     * @return Клиент Анна
     */
    private Client createAnna() {
        Client anna = new Client(cache.generateId(),"Anna Begunova", new Dog("Zhuchka"));
        return anna;
    }

    /**
     * Создание клиента Иван
     * @return Клиент Иван
     */
    private Client createIvan() {
        Client ivan = new Client(cache.generateId(),"Ivan Petrov", new Dog("Zhuchka"));
        return ivan;
    }

    /**
     * Создание клиента Петр
     * @return Клиент Петр
     */
    private Client createPetr() {
        Client petr = new Client(cache.generateId(),"Petr Sidorov", new Dog("Masha"));
        return petr;
    }
}

