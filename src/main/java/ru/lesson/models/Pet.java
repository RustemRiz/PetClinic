package ru.lesson.models;

/**
 * Питомец
 * Created by Рустем on 24.08.2017.
 */
public interface Pet {

    /*
    *Подать звук
     */
    void makeSound();

    /*
    *Имя питомца
     */
    String getName();

    void setNamePet(String newName);

    PetType getPetType();
}