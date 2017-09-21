package ru.lesson.store;

import org.junit.Test;
import ru.lesson.models.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Рустем on 15.09.2017.
 */
public class HibernateStorageTest {

    HibernateStorage storage = new HibernateStorage();


    @Test
    public void values() throws Exception {
        List<Client> clients = new ArrayList<Client>();
        clients.addAll(storage.values());
        for (Client client : clients)
            System.out.println(client);
    }

    @Test
    public void addGetDelete() throws Exception {
        String name = "Sergey";
        Client client = new Client(name, null);
        Pet_type dog = new Pet_type(1, "Собака");
        Pet_type cat = new Pet_type(2, "Кошка");
        Set<Pet> pets = new HashSet<>();
        Animal pet1 = new Animal("Spars", cat, client);
        Animal pet2 = new Animal("Miraka", dog, client);
        client.setPets(pets);
        client.getPets().add(pet1);
        client.getPets().add(pet2);
        int id = storage.add(client);

        Client clientOs = storage.get(id);
        assertEquals(clientOs.getName(), name);
//        System.out.println(clientOs.getId());
//        System.out.println(clientOs.getName());
//        Set<Pet> animals = clientOs.getPets();
//        for (Pet pet : animals){
//            System.out.println(pet);
//        }
        storage.delete(id);
        assertNull(storage.get(id));
    }

    @Test
    public void find() {
//        List<Client> clients = storage.find("%%","",1);
//        for(Client client : clients){
//            System.out.println(client);
//        }
//        assertEquals(clients.size(),storage.values().size());
        storage.delete(134);
    }

    @Test
    public void addPet() {
        Client oldClient = storage.get(136);
        Animal pet = new Animal("Mopsy", new Pet_type(2, "Кошка"), storage.get(136));
        int i = storage.addObject(pet);
        Client client = storage.get(136);
        client.getPets().add(pet);
        storage.edit(client);
        Client newClient = storage.get(136);
        System.out.println("s");

    }


}