package ru.lesson.store;

import ru.lesson.models.*;

import java.sql.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Рустем on 10.09.2017.
 */
public class JdbcStorage implements Storage{
    //Connection to DB
    private final Connection connection;
    //Текущий клиент
    private Client currentClient = null;

/*    Запросы БД
                    */
public static final String SQL_ADD_CLIENT = "INSERT INTO client (name) VALUES (?)";
public static final String SQL_ADD_PET_TO_CURRENT_CLIENT ="INSERT INTO pet(nick,client_id,type_id) VALUES (?,?,?)";
public static final String SQL_REMOVE_ALL_PETS_OF_CLIENT = "DELETE from pet WHERE client_id = ?";
public static final String SQL_REMOVE_CLIENT_BY_ID = "DELETE from client WHERE uid = ?";
public static final String SQL_EDIT_CLIENT = "UPDATE client set name = ? where uid = ?";
public static final String SQL_EDIT_PET = "UPDATE pet set nick = ?, type_id = ? where client_id = ?";
public static final String SQL_GET_ALL_CLIENTS_WITH_PETS = "SELECT pet.nick AS nick, client.name, client.uid, pet.type_id  FROM pet AS pet INNER JOIN client AS client ON client.uid=pet.client_id";
public static final String SQL_SELECT_CLIENT_WITH_PET_BY_CLIENT_ID = "SELECT client.uid,client.name, pet.type_id, pet.nick from client as client INNER JOIN pet as pet on pet.client_id=client.uid WHERE client.uid = ?";
public static final String SQL_FIND_BY_NAME_NICK_PET_TYPE = "SELECT client.uid,client.name, pet.type_id, pet.nick from client as client INNER JOIN pet as pet on pet.client_id=client.uid WHERE LOWER(client.name) LIKE LOWER(?) AND LOWER(pet.nick) LIKE LOWER(?) ";

//Конструктор соединения с БД
    public JdbcStorage() {
        try {
            Class.forName("org.postgresql.Driver");  // Не удалось настроить без этого
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/clinic",
                    "postgres","root");
        }catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

//Выборка всех клиентов с животными
    @Override
    public List<Client> values() {
        final List<Client> clients = new CopyOnWriteArrayList<>();
        try (final Statement statement=this.connection.createStatement();
        ResultSet rs = statement.executeQuery(SQL_GET_ALL_CLIENTS_WITH_PETS)) {
            while (rs.next()){
                Pet pet = PetFactory.createPet(PetType.getPetTypeByString(rs.getString("type_id")),rs.getString("nick"));
                clients.add(new Client(rs.getInt("uid"),rs.getString("name"),pet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    /**
     * Добавление клиента в базу
     * @param client Клиент для добавления
     * @return ID добавленного клиента
     */
    @Override
    public int add(Client client) {
        try(final PreparedStatement statement = connection.prepareStatement(SQL_ADD_CLIENT,Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,client.getName());
            statement.executeUpdate();
            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    int client_uid = generatedKeys.getInt("uid");
                    client.setId(client_uid);
                    Pet pet = client.getPet();
                    addPetToClient(pet.getName(),client_uid,pet.getPetType().getValue());
                    return client_uid;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Could not create new client");
        }
        return -1;
    }

    /**
     * Добавление питомца клиента
     * @param name Кличка питомца
     * @param client_uid    ID клиента
     * @param type_id       ID типа животного
     */
    public void addPetToClient(String name,int client_uid,int type_id){
        executeUpdateForPreparedStatement(SQL_ADD_PET_TO_CURRENT_CLIENT,name,client_uid,type_id);
    }

    /**
     * Изменяет параметры клиента с таким же ID на параметры нового клиента
     * @param client Новый клиент для вставки в бд
     */
    @Override
    public void edit(Client client) {
        executeUpdateForPreparedStatement(SQL_EDIT_CLIENT,client.getName(),client.getId());
        Pet pet = client.getPet();
        int type = pet.getPetType().getValue();
        executeUpdateForPreparedStatement(SQL_EDIT_PET, pet.getName(),type,client.getId());
    }


    @Override
    /**
     * Удаляет клиента с данным ID из БД вместе со всеми его животными
     * @param id ID клиента
     */
    public void delete(int id) {
        executeUpdateForPreparedStatement(SQL_REMOVE_ALL_PETS_OF_CLIENT,id);
        executeUpdateForPreparedStatement(SQL_REMOVE_CLIENT_BY_ID, id);
    }

    /**
     * Возвращает клиента по ID вместе с питомцем
     * @param id ID клиента
     * @return Клиент с данным ID
     */
    @Override
    public Client get(final int id) {
        Client client = null;
        try(PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_CLIENT_WITH_PET_BY_CLIENT_ID)){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    Pet pet = PetFactory.createPet(PetType.getPetTypeByString(resultSet.getString("type_id")),resultSet.getString("nick"));
                    client = new Client(resultSet.getInt("uid"),resultSet.getString("name"),pet);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public List<Client> find(String clientName, String petName, String petType) {
        final List<Client> clients = new CopyOnWriteArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_NAME_NICK_PET_TYPE)) {
            statement.setString(1,clientName);
            statement.setString(2,petName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Pet pet = PetFactory.createPet(PetType.getPetTypeByString(resultSet.getString("type_id")),resultSet.getString("nick"));
                Client client = new Client(resultSet.getInt("uid"),resultSet.getString("name"),pet);
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
        //Заглушка
//        return this.values();

    }
//Генерация ID не поддерживается
    @Override
    public int generateId() {
        throw new UnsupportedOperationException("PostgreSQL generate ID!");
    }



    public void executeUpdateForPreparedStatement(String sqlQuery,int uid,String... params){
        try(PreparedStatement statement = connection.prepareStatement(sqlQuery)){
            int i=1;
            for (i=1;i<=params.length;i++){
                statement.setString(i,params[i-1]);
            }
            statement.setInt(i,uid);
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void executeUpdateForPreparedStatement(String sqlQuery,String name,int... params){
        try(PreparedStatement statement = connection.prepareStatement(sqlQuery)){
            statement.setString(1,name);
            for (int i=2;i<=params.length+1;i++){
                int ir = params[i-2];
                statement.setInt(i,ir);
            }
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //Закрытие соединения
    @Override
    public void close() {
        try{
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
