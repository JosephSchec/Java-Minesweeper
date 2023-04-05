package minesweeper;

import javax.swing.*;

public class Rules {


    private static boolean checkExists(int val, int compareLength) {
        return val >= 0 && val < compareLength;
    }

    private static boolean isMine(int iVal, int jVal, JLabel[][] cells) {
        return checkExists(iVal, cells.length) && checkExists(jVal, cells[iVal].length) && cells[iVal][jVal].getText().equalsIgnoreCase("mine");
    }


    private static int findMinesAround(int i, int j, JLabel[][] cells) {
        int mineCount = 0;
        // down
        if (isMine(i + 1, j, cells)) {
            mineCount++;
        }
        //up
        if (isMine(i - 1, j, cells)) {
            mineCount++;
        }

        // right
        if (isMine(i, j + 1, cells)) {
            mineCount++;
        }

        //left
        if (isMine(i, j - 1, cells)) {
            mineCount++;
        }

        // down right
        if (isMine(i + 1, j + 1, cells)) {
            mineCount++;
        }

        //up right
        if (isMine(i - 1, j + 1, cells)) {
            mineCount++;
        }
        // down left
        if (isMine(i + 1, j - 1, cells)) {
            mineCount++;
        }
        //up left

        if (isMine(i - 1, j - 1, cells)) {
            mineCount++;
        }

        return mineCount;
    }

    public static boolean calculateAround(JLabel[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                JLabel cell = cells[i][j];
                if (HelperFunctions.isEmpty(cell)) {
                    cell.setText(String.format("%d", findMinesAround(i, j, cells)));
                }
            }
        }
        return true;
    }

}
