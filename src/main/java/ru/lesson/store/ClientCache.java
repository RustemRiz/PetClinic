package ru.lesson.store;

import java.util.Collection;
import java.util.List;

import ru.lesson.models.Client;
import ru.lesson.models.Id;
import ru.lesson.models.Pet;

/**
 * Created by Рустем on 02.09.2017.
 */
public class ClientCache implements Storage {
    private static ClientCache ourInstance = new ClientCache();
    private final Storage storage = new HibernateStorage();
    private Client currentClient;


    public static ClientCache getInstance() {
        return ourInstance;
    }

    private ClientCache() {
    }

    public Collection<Client> values() {
        return this.storage.values();
    }

    public int add(final Client client) {
        return this.storage.add(client);
    }

    @Override
    public <T extends Id> int addObject(T object) {
        return this.storage.addObject(object);
    }

    public void edit(final Client client) {
        this.storage.edit(client);
    }

    public void delete(final int id) {
        this.storage.delete(id);
    }

    public Client get(final int id) {
        return this.storage.get(id);
    }

    public List<Client> find(String clientName, String petName, int petType) {
        return this.storage.find(clientName, petName, petType);
    }

    @Override
    public void deletePet(int id) {
        this.storage.deletePet(id);
    }

    public int generateId(){
        return this.storage.generateId();
    }


    public Client getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(int id) {
        this.currentClient = get(id);
    }

    @Override
    public void close() {
        this.storage.close();
    }
}