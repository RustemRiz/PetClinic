package ru.lesson.models;

/**
 * Клиент
 * Created by Рустем on 23.08.2017.
 */
public class Client {
    private int id;
    private String name;
    private final Pet pet;

    /*
    *Конструктор для клиента
    *@param id номер клиета
    *@param ru.lesson.clinic.Pet питомец
     */
    public Client(String id, Pet pet){
        this.name=id;
        this.pet = pet;
    }

    public Client(int id, String name, Pet pet) {
        this.id = id;
        this.name = name;
        this.pet = pet;
    }

    public int getId() {
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    /*
    *Имя питомца
     */
    public Pet getPet(){
        return this.pet;
    }

    /**
     * Переименовать клиента
     * @param newName Новое имя
     */
    public void rename(String newName){
        if (newName.length()==0) throw new IllegalArgumentException("The name must contain at least one character!");
        this.name = newName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (getId() != client.getId()) return false;
        if (getName() != null ? !getName().equals(client.getName()) : client.getName() != null) return false;
        return getPet() != null ? getPet().equals(client.getPet()) : client.getPet() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPet() != null ? getPet().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", pet=" + pet +
                '}';
    }
}
