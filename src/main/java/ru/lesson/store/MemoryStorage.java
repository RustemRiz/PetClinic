/*
package ru.lesson.store;

import ru.lesson.models.Client;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

*/
/**
 * Created by Рустем on 09.09.2017.
 *//*

public class MemoryStorage  {
    private AtomicInteger ids = new AtomicInteger();
    private final ConcurrentHashMap<Integer, Client> clients = new ConcurrentHashMap<Integer, Client>();



    public Collection<Client> values() {
        return this.clients.values();
    }

    public int add(final Client client) {
        this.clients.put(client.getId(), client);
        return client.getId();
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

    public List<Client> find( String clientName, String petName, int petType) {
        List<Client> find = new CopyOnWriteArrayList<Client>();
        for (Client client : this.values()){
            if (clientName == "" ||client.getName().equals(clientName)){
                find.add(client);
            }
        }
        return find;
    }

    public int generateId(){
        return ids.incrementAndGet();
    }

    @Override
    public void close() {

    }
}
*/
