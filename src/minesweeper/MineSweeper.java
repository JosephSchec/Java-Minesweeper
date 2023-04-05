package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        new Board(rows, columns,mines);
    }


    public class Board {
        public Board(int rows, int columns,int mines) {

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

                    cell.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {

                            //if (continueGame && isEmpty(cell)) {
                            //   if (continueGame = doTurn(row, col, user)) {
                            //       computerTurn();
                            //  }
                            //   }
                        }
                    });
                    add(cell);
                }
            }

            int validSpots=0;

            while ( validSpots < mines) {
                int[] findSpot = HelperFunctions.findPositions(rows, columns);
                int randRow = findSpot[0];
                int randCol = findSpot[1];
                if (HelperFunctions.isEmpty(cells[randRow][randCol])) {
                    cells[randRow][randCol].setText("Mine");
                    validSpots++;
                }
            }
            Rules.calculateAround(cells);
        }
    }


}
