package ru.geekbrains.lim.task6;

public class Cat extends Animal {
    private static int count;
    private static final int MAX_RUN_LENGTH = 200;

    public Cat(String name) {
        super(name, MAX_RUN_LENGTH);
        count++;
    }

    public static int getCount() {
        return count;
    }

    @Override
    protected void swim(int length) {
        System.out.println("Кот не умеет плавать");
    }
}
