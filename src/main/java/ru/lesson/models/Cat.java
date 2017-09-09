package ru.lesson.models;

/**
 * Created by Рустем on 24.08.2017.
 */
public class Cat extends Animal {


    public Cat(String name) {
        super(name);
    }


    @Override
    public String toString() {
        return "Cat{" +
                "name='" + this.name + '\'' +
                '}';
    }
}
