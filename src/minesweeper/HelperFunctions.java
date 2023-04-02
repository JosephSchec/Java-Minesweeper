package minesweeper;

import javax.swing.*;

public class HelperFunctions {
    public static boolean isEmpty(JLabel cell) {
        return cell.getText().isEmpty();
    }
    public static int[] findPositions(int rowsLength,int columnsLength){
        int row =(int) Math.floor(Math.random() * (rowsLength) );
        int col =(int) Math.floor(Math.random() * (columnsLength) );
        return new int[]{row,col};
    }

}
