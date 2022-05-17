package ru.geekbrains.lim.task7;

import ru.geekbrains.lim.task5.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        plate.increaseFood(80);
        plate.info();
        Arrays.stream(cats).forEach(p -> p.info());
        Arrays.stream(cats).forEach(p -> p.setFull(p.eat(plate)));
        Arrays.stream(cats).forEach(p -> p.info());
        plate.info();
    }
}
