package ru.geekbrains.lim.task6;

public class Dog extends Animal{
    private static int count;
    private final int MAX_RUN_LENGTH = 500;
    private final int MAX_SWIM_LENGTH = 10;

    public Dog(String name) {
        super(name);
        count++;
    }

    @Override
    protected void run(int length) {
        System.out.println(getName() + " пробежал(а) " + (length > MAX_RUN_LENGTH? MAX_RUN_LENGTH : length) + " метров");
    }

    public static int getCount() {
        return count;
    }
    @Override
    protected void swim(int length) {
        System.out.println(getName() + " проплыла  " + (length > MAX_SWIM_LENGTH? MAX_SWIM_LENGTH : length) + " метров");
    }
}
