package ru.geekbrains.lim.task2;

public class HomeWorkApp {
    public static void main(String[] args) {
        if (checkValues(3, 9)) {
            System.out.println("Value in 10..20");
        } else {
            System.out.println("Value not in 10..20");
        }
        printPositiveOrNegativeValue(-3);
        if (!checkPositiveOrNegativeValue(5)) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
        printValue("Тестовая строка", 7);
        if (checkLeapYear(800)) {
            System.out.println("Year is a leap");
        } else {
            System.out.println("Year is not a leap");
        }
    }

    private static boolean checkValues(int a, int b) {
        if ((a + b) >= 10 && (a + b) <= 20) {
            return true;
        } else {
            return false;
        }
    }

    private static void printPositiveOrNegativeValue(int a) {
        if (a >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    private static boolean checkPositiveOrNegativeValue(int a) {
        return a < 0;
    }

    private static void printValue(String a, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(a);
        }
    }

    private static boolean checkLeapYear(int a) {
        return (a % 4 == 0) && (a % 100 != 0 || a % 400 == 0);
    }
}


