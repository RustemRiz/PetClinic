package ru.lesson.store;

import org.junit.*;
import ru.lesson.models.Client;
import ru.lesson.models.Pet;
import ru.lesson.models.PetFactory;
import ru.lesson.models.PetType;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

/**
 * Created by Рустем on 11.09.2017.
 */
public class JdbcStorageTest {

    final ClientCache cache = ClientCache.getInstance();
    List<Client> clients = new CopyOnWriteArrayList<>();

    @Test
    public void values() throws Exception {
        assertTrue(true);
    }

    @Test
    public void addGetDelete() throws Exception {
        String clientName = "Dilus";
        String petName = "Bolto";
        PetType petType = PetType.getPetTypeByString("2");

       assertEquals(petType,PetType.CAT);

        Pet pet = PetFactory.createPet(petType, petName);
        int id = cache.add(new Client(clientName, pet));
        Client currentClient = cache.get(id);
        assertNotNull(currentClient);
        assertEquals(clientName, currentClient.getName());
        assertEquals(petName, currentClient.getPet().getName());
        assertEquals(PetType.CAT, currentClient.getPet().getPetType());
        cache.delete(id);
        currentClient =cache.get(id);
        assertNull(currentClient);
      //  cache.close();
    }

    @Test
    public void edit(){
        int id = 3;
        Client client = cache.get(id);
        System.out.println(client);
        String oldName = client.getName();
        String newName = "Gregor";
        Pet oldPet = client.getPet();
        Pet newPet = PetFactory.createPet(PetType.CAT, "Kiska");
        cache.edit(new Client(client.getId(),newName, newPet));
        client = cache.get(id);
        System.out.println(client);
        assertEquals(newName,client.getName());
        assertEquals(newPet,client.getPet());

        cache.edit(new Client(client.getId(),oldName, oldPet));
        client=cache.get(id);
        assertEquals(client.getName(),oldName);
        assertEquals(oldPet,client.getPet());
        cache.close();
    }

    @Test
    public void find(){
        List<Client> findClients = new CopyOnWriteArrayList<>();
        clients.addAll(cache.values());
        findClients.addAll(cache.find("%%","%%","%%"));
        assertEquals(findClients.size(),clients.size());
//        for (Client client : findClients){
//            System.out.println(client);
//        }
       // cache.close();
    }

}