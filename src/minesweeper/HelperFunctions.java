package minesweeper;

import javax.swing.*;
import java.awt.*;

public class HelperFunctions {
    public static boolean isEmpty(JLabel cell) {
        return cell.getText().isEmpty();
    }

    public static Point findPositions(int rowsLength, int columnsLength) {
        int row = (int) Math.floor(Math.random() * (rowsLength));
        int col = (int) Math.floor(Math.random() * (columnsLength));
        return new Point(row, col);
    }

}
