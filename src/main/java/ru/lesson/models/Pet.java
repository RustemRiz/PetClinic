package ru.lesson.models;

/**
 * Питомец
 * Created by Рустем on 24.08.2017.
 */
public interface Pet {
    /*
    *Подать звук
     */
    public void makeSound();

    /*
    *Имя питомца
     */
    public String getName();

    public void setNamePet(String newName);


}