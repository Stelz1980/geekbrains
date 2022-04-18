package ru.geekbrains.lim.task3;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class HomeWorkApp {
    public static void main(String[] args) {

       /* reverseNumbersInArray();
        fillNumbersInArray();
        doubleValuesInArrayWhichLess6();
        drawXInArray();
        System.out.println(Arrays.toString(initArray(20, 50)));
        findMinMaxValuesInArray();*/
        if (checkBalance(new int [] {1, 1, 1, 2, 1})) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }

    private static int[] initRandomArray(int maxLength, int maxValue) {
        int [] arr = new int[maxLength];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(maxValue + 1);
        }
        return arr;
    }

    private static void reverseNumbersInArray() {
        int [] arr = initRandomArray(10, 1) ;
        System.out.println("Начальный массив - " + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == 1? 0: 1;
        }
        System.out.println("Обновленный массив - " + Arrays.toString(arr));
    }

    private static void fillNumbersInArray() {
        int [] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println("Значения массива - " + Arrays.toString(arr));
    }

    private static void doubleValuesInArrayWhichLess6() {
        int [] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] < 6? arr[i]*2: arr[i];
        }
        System.out.println("Значения массива - " + Arrays.toString(arr));
    }

    private static void drawXInArray() {
        int [][] arr = new int[20][20];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i == j) {
                    System.out.printf("%2d", 1);
                } else if (i == arr.length - j - 1) {
                    System.out.printf("%2d", 1);
                }
                else {
                    System.out.printf("%2d", 0);
                }
            }
            System.out.println();
        }
    }
    private static int[] initArray(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }

    private static void findMinMaxValuesInArray() {
        int [] arr = initRandomArray(10, 20) ;
        int minValue = arr[0];
        int maxValue = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (minValue > arr[i]) {
                    minValue = arr[i];
            }
            if (maxValue < arr[i]) {
                maxValue = arr[i];
            }
        }
        System.out.println("Значения массива - " + Arrays.toString(arr));
        System.out.println("Минимальное значение - " + minValue);
        System.out.println("Максимальное значение - " + maxValue);
    }

    private static boolean checkBalance(int [] arr) {
        int firstSum = 0;
        for (int i = 0; i < arr.length; i++) {
            firstSum +=  arr[i];
            if (firstSum == sumOfOtherValuesInArray(i + 1, arr)) {
                return true;
            }
        }
        return false;
    }

    private static Integer sumOfOtherValuesInArray(int startIndex, int[] arr) {
        int result = 0;
        for (int i = startIndex; i < arr.length; i++) {
            result += arr[i];
        }
        return result;
    }

}
