package ru.geekbrains.lim.task6;

public class Cat extends Animal {
    private static int count;
    private final int MAX_RUN_LENGTH = 200;

    public Cat(String name) {
        super(name);
        count++;
    }

    @Override
    protected void run(int length) {
        System.out.println(getName() + " пробежала " + (length > MAX_RUN_LENGTH ? MAX_RUN_LENGTH : length) + " метров");
    }

    public static int getCount() {
        return count;
    }

    @Override
    protected void swim(int length) {
        System.out.println("Кот не умеет плавать");
    }
}
