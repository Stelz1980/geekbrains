package ru.geekbrains.lim.task7;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int n) {
        if (this.food >= n) {
            this.food -= n;
            return true;
        }
        return false;
    }

    public boolean increaseFood(int n) {
        if (n > 0) {
            this.food += n;
            return true;
        }
        return false;
    }

    public void info() {
        System.out.println("plate: " + food);
    }
}
