package ru.lesson.models;

/**
 * Created by Рустем on 24.08.2017.
 */
public abstract class Animal implements Pet{
    protected String name;
    protected Animal(String name){
        this.name = name;
    }

    public void makeSound() {

    }

    public String getName() {
        return this.name;
    }



    /*

        *Переименовать питомца*/
    public void setNamePet(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        return getName() != null ? getName().equals(animal.getName()) : animal.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
