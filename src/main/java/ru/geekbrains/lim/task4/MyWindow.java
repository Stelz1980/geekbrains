package ru.geekbrains.lim.task4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MyWindow extends JFrame {

    private static final int SIZE = 5;
    private static final int DOT_TO_WIN = 4;
    private static final char DOT_EMPTY = '.';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static final int UP = 1;
    private static final int DOWN = -1;
    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    private static final int NO_LEFT_NO_RIGHT = 0;
    private static final int NO_UP_NO_DOWN = 0;
    private static Random random = new Random();
    private static boolean blockUser = false;
    private static JButton[][] buttons = new JButton[SIZE][SIZE];

/*    private ActionListener buttonListener = e -> {e.get

         JButton button = new JButton(String.valueOf(DOT_EMPTY));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (".".equals(button.getText())) {
                        button.setText("X");

                    }
                }
            });
    };*/

    private static void humanTurn(JButton button) {
        if (".".equals(button.getText())) {
            button.setText("X");
        }
    }

    private static boolean checkWin(String symb) {
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

    private static boolean isLine(int x0, int y0, int stepVertical, int stepHorizontal, String symb) {
        for (int i = 0; i < DOT_TO_WIN; i++) {
            if (!isIndexesInArray(y0 + i * stepVertical, x0 + i * stepHorizontal) || !buttons[y0 + i * stepVertical][x0 + i * stepHorizontal].getText().equals(symb)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isIndexesInArray(int i, int j) {
        return i >= 0 && i < buttons.length && j >= 0 && j < buttons.length;
    }

    public MyWindow() {
        setTitle("Крестики - нолики");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(300, 300, 500, 500);
        setLayout(new GridLayout(SIZE, SIZE));
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setText(String.valueOf(DOT_EMPTY));
                add(buttons[i][j]);
                JButton button = buttons[i][j];
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!String.valueOf(DOT_O).equals(button.getText())) {
                            button.setText(String.valueOf(DOT_X));
                            if (checkWin(button.getText())) {
                                System.out.println("Победил человек");
                            }

                        }
                    }
                });
            }
        }
        setVisible(true);
    }

}
