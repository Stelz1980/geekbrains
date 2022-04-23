package ru.geekbrains.lim.task4;


import java.util.Random;
import java.util.Scanner;

public class HomeWorkApp {
    public static final int SIZE = 5;
    public static final int DOT_TO_WIN = 4;
    public static final char DOT_EMPTY = '.';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static final int UP = 1;
    public static final int DOWN = -1;
    public static final int LEFT = -1;
    public static final int RIGHT = 1;
    public static final int NO_LEFT_NO_RIGHT = 0;
    public static final int NO_UP_NO_DOWN = 0;
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();
    public static boolean blockUser = false;

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
        System.out.println("Включить блокирующий режим? Y/N ");
        blockUser = sc.nextLine().equals("Y");
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
        }
        return false;
    }

    private static void aiTurn() {
        int x, y;
        if (!(blockUser && closeThreats())) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellValid(x, y));
            System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
            map[y][x] = DOT_O;
        }
    }

    private static boolean checkWin(char symb) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == 0 || i == SIZE - 1 || j == 0 || j == SIZE - 1) {
                    if (isLine(i, j, NO_UP_NO_DOWN, RIGHT, symb) ||
                            isLine(i, j, NO_UP_NO_DOWN, LEFT, symb) ||
                            isLine(i, j, UP, NO_LEFT_NO_RIGHT, symb) ||
                            isLine(i, j, DOWN, NO_LEFT_NO_RIGHT, symb) ||
                            isLine(i, j, UP, RIGHT, symb) ||
                            isLine(i, j, UP, LEFT, symb) ||
                            isLine(i, j, DOWN, RIGHT, symb) ||
                            isLine(i, j, DOWN, LEFT, symb)
                    ) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean closeThreats() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == 0 || i == SIZE - 1 || j == 0 || j == SIZE - 1) {
                    if (isThreat(i, j, NO_UP_NO_DOWN, RIGHT)) {
                        killThreat(i, j, NO_UP_NO_DOWN, RIGHT);
                        return true;
                    } else if (isThreat(i, j, NO_UP_NO_DOWN, LEFT)) {
                        killThreat(i, j, NO_UP_NO_DOWN, LEFT);
                        return true;
                    } else if (isThreat(i, j, UP, NO_LEFT_NO_RIGHT)) {
                        killThreat(i, j, UP, NO_LEFT_NO_RIGHT);
                        return true;
                    } else if (isThreat(i, j, DOWN, NO_LEFT_NO_RIGHT)) {
                        killThreat(i, j, DOWN, NO_LEFT_NO_RIGHT);
                        return true;
                    } else if (isThreat(i, j, UP, RIGHT)) {
                        killThreat(i, j, UP, RIGHT);
                        return true;
                    } else if (isThreat(i, j, UP, LEFT)) {
                        killThreat(i, j, UP, LEFT);
                        return true;
                    } else if (isThreat(i, j, DOWN, RIGHT)) {
                        killThreat(i, j, DOWN, RIGHT);
                        return true;
                    } else if (isThreat(i, j, DOWN, LEFT)) {
                        killThreat(i, j, DOWN, LEFT);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isThreat(int x0, int y0, int stepVertical, int stepHorizontal) {
        int count = 0;
        for (int i = 0; i < DOT_TO_WIN; i++) {
            if (isIndexesInArray(y0 + i * stepVertical, x0 + i * stepHorizontal)) {
                char cellValue = map[y0 + i * stepVertical][x0 + i * stepHorizontal];
                if (cellValue == DOT_X) {
                    count++;
                } else if (cellValue == DOT_O) {
                    return false;
                }
            }
        }
        return count == DOT_TO_WIN - 1;
    }

    private static void killThreat(int x0, int y0, int stepVertical, int stepHorizontal) {
        for (int i = 0; i < DOT_TO_WIN; i++) {
            if (map[y0 + i * stepVertical][x0 + i * stepHorizontal] == DOT_EMPTY) {
                System.out.println("Компьютер походил в блокирующую точку " + (x0 + i * stepHorizontal + 1) + " " + (y0 + i * stepVertical));
                map[y0 + i * stepVertical][x0 + i * stepHorizontal] = DOT_O;
                return;
            }
        }
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