package ru.lesson.store;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import ru.lesson.models.Client;

/**
 * Created by Рустем on 02.09.2017.
 */
public class ClientCache implements Storage {
    private AtomicInteger ids = new AtomicInteger();
    private static ClientCache ourInstance = new ClientCache();
    private final ConcurrentHashMap<Integer, Client> clients = new ConcurrentHashMap<Integer, Client>();

    public static ClientCache getInstance() {
        return ourInstance;
    }

    private ClientCache() {
    }

    public Collection<Client> values() {
        return this.clients.values();
    }

    public void add(final Client client) {
        this.clients.put(client.getId(), client);
    }

    public void edit(final Client client) {
        this.clients.replace(client.getId(), client);
    }

    public void delete(final int id) {
        this.clients.remove(id);
    }

    public Client get(final int id) {
        return this.clients.get(id);
    }

    public List<Client> finded(String clientName, String petName) {
        List<Client> finded = new CopyOnWriteArrayList<Client>();
        for (Client client : this.values()){
            if ((clientName == "" ||client.getName().equals(clientName)) && (petName == "" ||client.getPet().getName().equals(petName))){
                finded.add(client);
            }
        }
        return finded;
    }

    public int generateId(){
        return ids.incrementAndGet();
    }
}