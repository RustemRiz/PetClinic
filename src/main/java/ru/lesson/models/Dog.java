package ru.lesson.models;

/**
 * Created by Рустем on 24.08.2017.
 */
public class Dog extends Animal{
    /*
    *Имя питомца
     */

    public Dog(String name) {
        super(name);
    }




    @Override
    public String toString() {
        return "Dog{" +
                "name='" + this.name + '\'' +
                '}';
    }
}
