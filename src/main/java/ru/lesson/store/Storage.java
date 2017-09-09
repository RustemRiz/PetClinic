package ru.lesson.store;

import ru.lesson.models.Client;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Рустем on 09.09.2017.
 */
public interface Storage {

    Collection<Client> values();

    void add(final Client client);

    void edit(final Client client);

    void delete(final int id);

    Client get(final int id);

    List<Client> finded(String clientName, String petName);

    int generateId();
}
