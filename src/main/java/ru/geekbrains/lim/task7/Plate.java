package ru.geekbrains.lim.task7;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int n) {
        if (food >= n) {
            food -= n;
            return true;
        } else {
            return false;
        }
    }

    public void increaseFood(int n) {
        if (n > 0) {
            food += n;
        }
    }

    public void info() {
        System.out.println("plate: " + food);
    }
}
