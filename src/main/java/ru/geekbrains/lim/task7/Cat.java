package ru.geekbrains.lim.task7;

public class Cat {
    private String name;
    private int appetite;
    private boolean isFull;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.isFull = false;
    }

    public void setFull(boolean isFull) {
        this.isFull = isFull;
    }

    public boolean eat(Plate plate) {
        return plate.decreaseFood(appetite);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", appetite=" + appetite +
                ", isFull=" + isFull +
                '}';
    }
}
