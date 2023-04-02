package minesweeper;

import javax.swing.*;

public class Rules {
    public static boolean calculateAround(JLabel[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (!HelperFunctions.isEmpty(cells[i][j])) {
                    System.out.println(cells[i][j].getText());
                }
            }
        }
        return true;
    }
}
