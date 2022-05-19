package ru.geekbrains.lim.task8;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MyWindow extends JFrame {

    private static final int SIZE = 3;
    private static final int DOTS_TO_WIN = 3;
    private static final String GAME_NAME = "Крестики - нолики";
    private static final String DOT_EMPTY = String.valueOf('.');
    private static final String DOT_X = String.valueOf('X');
    private static final String DOT_O = String.valueOf('O');
    private static final int UP = 1;
    private static final int DOWN = -1;
    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    private static final int NO_LEFT_NO_RIGHT = 0;
    private static final int NO_UP_NO_DOWN = 0;
    private static boolean blockUser = false;
    private static JButton[][] buttons = new JButton[SIZE][SIZE];
    private static JLabel statusLBL = new JLabel();

    public MyWindow() {
        Container c = getContentPane();
        setTitle(GAME_NAME);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(300, 300, 500, 600);
        setResizable(false);
        JPanel topPanel = new JPanel();
        statusLBL.setFont(new Font("Serif", Font.PLAIN, 14));
        topPanel.add(statusLBL);
        add(topPanel, BorderLayout.NORTH);
        JPanel bottomPanel = new JPanel();
        JButton startBtn = new JButton("Начать игру снова");
        startBtn.addActionListener(e -> drawEmptyButtons());
        JCheckBox blockModeCB = new JCheckBox("Блокирущий режим");
        blockModeCB.addActionListener(e -> blockUser = blockModeCB.isSelected());
        bottomPanel.add(startBtn);
        bottomPanel.add(blockModeCB);
        add(bottomPanel, BorderLayout.SOUTH);
        JPanel mainPanel = new JPanel(new GridLayout(SIZE, SIZE));
        addButtons(mainPanel);
        drawEmptyButtons();
        add(mainPanel, BorderLayout.CENTER);
        startGame();
        setVisible(true);
    }

    private void addButtons(JPanel panel) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Serif", Font.PLAIN, 20));
                panel.add(buttons[i][j]);
            }
        }
    }

    private void drawEmptyButtons() {
        statusLBL.setText("Ваш ход!");
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                buttons[i][j].setText(DOT_EMPTY);
                buttons[i][j].setEnabled(true);
            }
        }
    }

    private void disableButtons() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void startGame() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                JButton button = buttons[i][j];
                button.addActionListener(e -> {
                    if (DOT_EMPTY.equals(button.getText())) {
                        button.setText(DOT_X);
                        if (checkWin(DOT_X)) {
                            disableButtons();
                            statusLBL.setText("Победил человек.Game Over");
                            return;
                        }
                        if (checkFull()) {
                            disableButtons();
                            statusLBL.setText("Ничья.Game Over");
                            return;
                        }
                        aiTurn();
                        if (checkWin(DOT_O)) {
                            disableButtons();
                            statusLBL.setText("Победил компьютер.Game Over");
                            return;
                        }
                        if (checkFull()) {
                            disableButtons();
                            statusLBL.setText("Ничья.Game Over");
                            return;
                        }
                    }
                });
            }
        }
    }

    private boolean checkFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (buttons[i][j].getText().equals(DOT_EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void aiTurn() {
        int x, y;
        Random random = new Random();
        if (!(blockUser && closeThreats())) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellValid(x, y));
            buttons[y][x].setText(DOT_O);
            System.out.println("Компьютер походил в точку " + y + " " + x);
        }
    }

    private static boolean isCellValid(int x, int y) {
        return buttons[y][x].getText().equals(DOT_EMPTY);
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
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (!isIndexesInArray(y0 + i * stepVertical, x0 + i * stepHorizontal) || !buttons[y0 + i * stepVertical][x0 + i * stepHorizontal].getText().equals(symb)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isIndexesInArray(int i, int j) {
        return i >= 0 && i < buttons.length && j >= 0 && j < buttons.length;
    }

    private static boolean closeThreats() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
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
        return false;
    }

    private static boolean isThreat(int x0, int y0, int stepVertical, int stepHorizontal) {
        int count = 0;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (isIndexesInArray(y0 + i * stepVertical, x0 + i * stepHorizontal)) {
                String cellValue = buttons[y0 + i * stepVertical][x0 + i * stepHorizontal].getText();
                if (cellValue.equals(DOT_X)) {
                    count++;
                } else if (cellValue.equals(DOT_O)) {
                    return false;
                }
            }
        }
        return count == DOTS_TO_WIN - 1;
    }

    private static void killThreat(int x0, int y0, int stepVertical, int stepHorizontal) {
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (isIndexesInArray(y0 + i * stepVertical, x0 + i * stepHorizontal)) {
                if (buttons[y0 + i * stepVertical][x0 + i * stepHorizontal].getText().equals(DOT_EMPTY)) {
                    statusLBL.setText("Компьютер походил в блокирующую точку " + (x0 + i * stepHorizontal + 1) + " " + (y0 + i * stepVertical + 1));
                    buttons[y0 + i * stepVertical][x0 + i * stepHorizontal].setText(DOT_O);
                    return;
                }
            }
        }
    }
}
