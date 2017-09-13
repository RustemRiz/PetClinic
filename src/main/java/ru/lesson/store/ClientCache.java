package ru.lesson.store;

import java.util.Collection;
import java.util.List;

import ru.lesson.models.Client;

/**
 * Created by Рустем on 02.09.2017.
 */
public class ClientCache implements Storage {
    private static ClientCache ourInstance = new ClientCache();
    private final Storage storage = new JdbcStorage();

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

    public void edit(final Client client) {
        this.storage.edit(client);
    }

    public void delete(final int id) {
        this.storage.delete(id);
    }

    public Client get(final int id) {
        return this.storage.get(id);
    }

    public List<Client> find(String clientName, String petName, String petType) {
        return this.storage.find(clientName, petName, petType);
    }

    public int generateId(){
        return this.storage.generateId();
    }

    @Override
    public void close() {
        this.storage.close();
    }
}