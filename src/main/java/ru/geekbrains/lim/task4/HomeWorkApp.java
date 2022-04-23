package ru.geekbrains.lim.task4;

import java.util.Random;
import java.util.Scanner;

public class HomeWorkApp {
    public static final int SIZE = 5;
    public static final int DOT_TO_WIN = 4;
    public static final char DOT_EMPTY = '.';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();

    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил компьютер");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Game over");
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    private static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        if (map[y][x] == DOT_EMPTY) {
            return true;
        };
        return false;
    }

    private static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    private static boolean checkWin(char symb) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == 0 || i == SIZE - 1 || j == 0 || j == SIZE - 1) {
                    if (isLine(i, j, 0, 1, symb) ||
                            isLine(i, j, 1, 0, symb) ||
                            isLine(i, j, 1, 1, symb) ||
                            isLine(i, j, 1, -1, symb) ||
                            isLine(i, j, -1, 0, symb) ||
                            isLine(i, j, -1, -1, symb)
                    ) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isLine(int x0, int y0, int stepVertical, int stepHorizontal, char symb) {
        for (int i = 0; i < DOT_TO_WIN; i++) {
            if (!isIndexesInArray(y0 + i * stepVertical, x0 + i * stepHorizontal) || map[y0 + i * stepVertical][x0 + i * stepHorizontal] != symb) {
                return false;
            }
        }
        return true;
    }

    private static boolean isIndexesInArray(int i, int j) {
        return i >= 0 && i < map.length && j >= 0 && j < map.length;
    }

    private static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY)
                    return false;
            }
        }
        return true;
    }
}

