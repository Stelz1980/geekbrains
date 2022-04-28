package ru.geekbrains.lim.task6;

import java.util.Random;

public class HomeWorkApp {
    public static void main(String[] args) {
        Animal[] animals = {
                new Dog("Бобик"),
                new Dog("Шарик"),
                new Cat("Барсик"),
                new Cat ("Васька"),
                new Dog ("Тузик"),
        };

        for (Animal animal: animals) {
            animal.run(new Random().nextInt(1000));
            animal.swim(new Random().nextInt(1000));
        }
        System.out.println("Всего у нас - " + Animal.getCount() + " животных");
        System.out.println("из них:");
        System.out.println("Собак у нас - " + Dog.getCount());
        System.out.println("Котов у нас - " + Cat.getCount());
    }
}
