package ru.lesson.models;

/**
 * Created by Рустем on 24.08.2017.
 */
public class Animal implements Pet {
    protected int id;
    protected String name;
    protected Pet_type petType;
    protected Client owner;


    public Animal() {
    }

    public Animal(String name, Pet_type petType) {
        this.name = name;
        this.petType = petType;
    }

    public Animal(String name, Pet_type petType, Client owner) {
        this.name = name;
        this.petType = petType;
        this.owner = owner;
//        this.clientId = clientId;
    }

    protected Animal(String name) {
        this.name = name;
    }

//    public Animal(int id, String name, Pet_type petType, int clientId) {
//        this.id = id;
//        this.name = name;
//        this.petType = petType;
//        this.clientId = clientId;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPetType(Pet_type petType) {
        this.petType = petType;
    }


    public String getName() {
        return this.name;
    }

    public Pet_type getPetType() {
        return this.petType;
    }

    public Client getOwner(){
        return this.owner;
    }
    public void setOwner(Client owner){
        this.owner = owner;
    }
//    @Override
//    public int getClientId() {
//        return clientId;
//    }
//
//    @Override
//    public void setClientId(int clientId) {
//        this.clientId = clientId;
//    }

    //    public Client getOwner() {
//        return owner;
//    }
//
//    public void setOwner(Client owner) {
//        this.owner = owner;
//    }
//


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        return getId() == animal.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", petType=" + petType +
                ", owner=" + owner.getId() +
                '}';
    }
}