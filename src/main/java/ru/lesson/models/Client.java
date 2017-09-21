package ru.lesson.models;

import java.util.Set;

/**
 * Клиент
 * Created by Рустем on 23.08.2017.
 */
public class Client implements Id{

    private int id;
    private String name;
    private Set<Pet> pets;


    /*
        *Конструктор для клиента
        *@param id номер клиета
        *@param pets питомцы
         */
    public Client(){

    }

    public Client(String name, Set<Pet> pets){
        this.name=name;
        this.pets = pets;
    }

    public Client(int id, String name, Set<Pet> pets) {
        this.id = id;
        this.name = name;
        this.pets = pets;

    }

    /**
     * Getters and setters
     */
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Set<Pet> getPets(){
        return this.pets;
    }


    public void setPets(Set<Pet> pets) {
        this.pets = pets;
        for (Pet pet : this.pets){
            pet.setOwner(this);
        }
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
        return getPets() != null ? getPets().equals(client.getPets()) : client.getPets() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPets() != null ? getPets().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id" + id+ '\'' +
                "name='" + name + '\'' +
                ", pets=" + pets +
                '}';
    }


}
