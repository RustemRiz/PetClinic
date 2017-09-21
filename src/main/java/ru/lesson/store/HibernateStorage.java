package ru.lesson.store;

import com.sun.xml.internal.ws.api.config.management.Reconfigurable;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.lesson.models.Animal;
import ru.lesson.models.Client;
import ru.lesson.models.Id;
import ru.lesson.models.Pet;


import javax.persistence.PostRemove;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Рустем on 14.09.2017.
 */
public class HibernateStorage implements Storage{
    private final SessionFactory factory;
    private static final String HQL_FIND_BY_NAME_NICK_PET_TYPE = "from Client where lower(name) LIKE lower(:name)";   //" AND LOWER(pet.name) LIKE LOWER(:petName)";

    public HibernateStorage() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
        sb.applySettings(configuration.getProperties());
        StandardServiceRegistry standardServiceRegistry = sb.build();
        factory = new Configuration().configure().buildSessionFactory(standardServiceRegistry);
    }

    @FunctionalInterface
    public interface Command<T> {
        T process(Session session);
    }

    private <T> T transaction(final Command<T>  command){
       final Session session = factory.openSession();
       final Transaction tx = session.beginTransaction();
       try{
           return command.process(session);
       }finally {
           tx.commit();
           session.close();
       }
    }

    //Выборка всех клиентов
    @Override
    public Collection<Client> values(){
        return transaction((Session session)->session.createQuery("from Client").list());
    }

    public <T extends Id >int addObject(T object){
        return transaction((Session session)->{
            session.save(object);
            return object.getId();
        });
    }

    //Добавление клиента в БД
    @Override
    public int add(Client client) {
        Set<Pet>  pets  = client.getPets();
        int  clientId;
        if(pets == null){
            clientId = addObject(client);
        }
        else{
            client.getPets().clear();
            //Добавляем клиента без питомцев
            clientId = addObject(client);
            client.setPets(pets);
            //Добавляем питомцев
            pets.forEach((this::addObject));
        }
        return clientId;
    }

    //обновить данные клиента
    @Override
    public void edit(Client client){
          transaction((Session session) -> {
            session.update(client);
            return null;
        });
    }

    //Получить клиента по ID
    @Override
    public Client get(int id){
        return transaction(session -> (Client)session.get(Client.class,id));
    }

    //Удалить клиента из БД
    @Override
    public void delete(final int id){
        transaction((session) -> {
            Client client = get(id);
            if(client == null) return null;
            Set<Pet> pets = client.getPets();
            pets.forEach(session::delete);
            client.getPets().clear();
            session.flush();
            session.delete(client);
            return null;
        });
    }

    //Удалить питомца из БД
    public void deletePet(int id){
        transaction((session)->{
            Pet pet = (Animal)session.get(Animal.class,id);
            session.delete(pet);
            return null;
        });
    }

    /**
     * Поиск клиентов по критериям
     * @return Список клиентов, подходящих критериям
     */
    @Override
    public List<Client> find(String clientName, String petName, int petType){
        return transaction((Session session) -> {
            Query query = session.createQuery(HQL_FIND_BY_NAME_NICK_PET_TYPE);
            query.setString("name",clientName);
            List cl = query.list();
            List<Client> clients = new CopyOnWriteArrayList<>();
            cl.forEach(obj -> clients.add((Client)obj));
            return clients;
        });
    }

    @Override
    public int generateId(){
        return 0;
    }

    @Override
    public void close() {
        this.factory.close();
    }
}
