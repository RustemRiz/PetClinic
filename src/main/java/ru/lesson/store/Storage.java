package ru.lesson.store;

import ru.lesson.models.Animal;
import ru.lesson.models.Client;
import ru.lesson.models.Id;
import ru.lesson.models.Pet;

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

    int add(final Client client);

    <T extends Id>int addObject(T object);

    void edit(final Client client);

    void delete(final int id);

    Client get(final int id);

    List<Client> find(String clientName, String petName, int petType);

    void deletePet(int id);

    int generateId();

    void close();

}
