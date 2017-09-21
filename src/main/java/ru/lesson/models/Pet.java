package ru.lesson.models;

/**
 * Питомец
 * Created by Рустем on 24.08.2017.
 */
public interface Pet extends Id {
    String getName();
    void setName(String newName);
    Client getOwner();
    void setOwner(Client owner);
//    int getClientId();
//    void setClientId(int clientId);
//    Client getOwner();
//    void setOwner(Client onwer);
//    PetType getPetType();
}