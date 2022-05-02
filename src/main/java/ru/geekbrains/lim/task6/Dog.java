package ru.geekbrains.lim.task6;

public class Dog extends Animal{
    private static int count;
    private static final int MAX_RUN_LENGTH = 500;
    private static final int MAX_SWIM_LENGTH = 10;

    public Dog(String name) {
        super(name, MAX_RUN_LENGTH);
        count++;
    }

    public static int getCount() {
        return count;
    }
    @Override
    protected void swim(int length) {
        System.out.println(getName() + " проплыл(а)  " + (length > MAX_SWIM_LENGTH? MAX_SWIM_LENGTH : length) + " метров");
    }
}
