package ru.geekbrains.lim.task7;

import ru.geekbrains.lim.task5.Person;

import java.util.Arrays;
import java.util.stream.Collectors;

public class HomeWorkApp {
    public static void main(String[] args) {
        Cat[] cats = {
                        new Cat("Barsik", 15),
                        new Cat("Aksik", 35),
                        new Cat("Vaska", 12),
                        new Cat("Tiska", 25),
                        new Cat("Mashka", 11)
                      };
        Plate plate = new Plate(10);
        plate.increaseFood(50);
        Arrays.stream(cats).forEach(p -> System.out.println(p.toString()));
        Arrays.stream(cats).forEach(p -> p.setFull(p.eat(plate)));
        Arrays.stream(cats).forEach(p -> System.out.println(p.toString()));
    }
}
