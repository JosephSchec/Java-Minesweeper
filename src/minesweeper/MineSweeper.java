package minesweeper;

import javax.swing.*;
import java.awt.*;

public class MineSweeper extends JFrame {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            new MineSweeper().setVisible(true);
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public MineSweeper() {
        setUp();
    }

    public void setUp() {
        int cellsize = 250;
        int rows = 12;
        int columns = 5;
        int mines = 20;
        setSize(1250, 980);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(cellsize * 3, cellsize * 3);
        new Board(rows, columns, mines);
    }


    public class Board {
        public Board(int rows, int columns, int mines) {

            setLayout(new GridLayout(rows, columns));

            JLabel[][] cells = new JLabel[rows][columns];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    final int row = i;
                    final int col = j;
                    JLabel cell = new JLabel();
                    cells[i][j] = cell;
                    cell.setHorizontalAlignment(SwingConstants.CENTER);
                    cell.setFont(new Font("Impact", Font.BOLD, 35));
                    cell.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                    cell.setOpaque(true);
                    add(cell);
                }
            }

            int validSpots = 0;

            while (validSpots < mines) {
                Point spot = HelperFunctions.findPositions(rows, columns);

                int row = spot.x;
                int col = spot.y;

                if (HelperFunctions.isEmpty(cells[row][col])) {
                    // since the highest value of mines around a specific cell is 8 the int describing mine I made as 9
                    GamePlay.allValues.put(new Point(row, col), 9);
                    validSpots++;
                }
            }
            GamePlay.calculateAround(cells);
        }
    }


}
