package ru.geekbrains.lim.task5;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HomeWorkApp {
    public static void main(String[] args) {
        Person[] persArray = new Person[5];
        persArray[0] = new Person("Иванов Иван", "Инженер", "ivanov.ivan.gmail.com", "+79261234567", 10000, 25);
        persArray[1] = new Person("Смирнов Иван", "Повар", "smirnov.ivan.gmail.com", "+792612434987934", 20000, 35);
        persArray[2] = new Person("Демидов Иван", "Водитель", "demidov.ivan.gmail.com", "+79261248786234", 30000, 45);
        persArray[3] = new Person("Петров Иван", "Альпинист", "petrov.ivan.gmail.com", "+7926124345234", 40000, 65);
        persArray[4] = new Person("Ощепков Иван", "Врач", "oschepkov.ivan.gmail.com", "+792612312234", 50000, 75);

        for (Person person: persArray) {
            if (person.getAge() > 40) {
                person.print();
            }
        }

        for (Person person: Arrays.stream(persArray).filter((p)->p.getAge() > 40).collect(Collectors.toList())) {
            person.print();
        }
    }
}
