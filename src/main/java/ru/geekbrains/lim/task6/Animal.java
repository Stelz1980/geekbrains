package ru.geekbrains.lim.task6;

public abstract class Animal {
    private static int count;
    private String name;
    private int maxRun;

    public Animal(String name, int maxRun) {
        this.name = name;
        this.maxRun = maxRun;
        count++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Animal.count = count;
    }

    public void run(int length) {
        System.out.println(this.name + " пробежал(а) " + (length > maxRun ? maxRun : length) + " метров");
    }

    protected abstract void swim(int length);

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }
}
