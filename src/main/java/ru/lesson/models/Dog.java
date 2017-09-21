package ru.lesson.models;

/**
 * Created by Рустем on 24.08.2017.
 */
public class Dog extends Animal{

      public Dog(String name) {
        super(name);
//        petType= PetType.DOG;
    }


    @Override
    public String toString() {
        return "Dog{" +
                "name='" + this.name + '\'' +
                '}';
    }
}
