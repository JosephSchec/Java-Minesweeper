package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class GamePlay {

    public static Map<Point, Integer> allValues = new HashMap<>();

    private static boolean checkExists(int val, int compareLength) {
        return val >= 0 && val < compareLength;
    }

    private static boolean isMine(int iVal, int jVal, JLabel[][] cells) {
        Point clickedPoint = new Point(iVal, jVal);
        return checkExists(iVal, cells.length) && checkExists(jVal, cells[iVal].length) && allValues.containsKey(clickedPoint) && allValues.get(clickedPoint) == 9;
    }

    private static int findMinesAround(int i, int j, JLabel[][] cells) {
        Point thisPoint = new Point(i, j);
        // This can be considered as an optimization when the click to reveal is mine
        if (allValues.containsKey(thisPoint)) {
            return allValues.get(thisPoint);
        } else {
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
            allValues.put(thisPoint, mineCount);
            return mineCount;
        }
    }

    public static void setCellText(int i, int j, JLabel[][] cells) {
        JLabel cell = cells[i][j];
        int mineCount = findMinesAround(i, j, cells);
        String display = mineCount == 9 ? "mine" : Integer.toString(mineCount);
        cell.setText(String.format("%s", display));
        if (mineCount == 9) {
            for (int getI = 0; getI < cells.length; getI++) {
                for (int getJ = 0; getJ < cells[getI].length; getJ++) {
                    JLabel newCell = cells[getI][getJ];
                    if (HelperFunctions.isEmpty(newCell)) {
                        setCellText(getI, getJ, cells);
                    }
                }
            }
        }
    }
}
