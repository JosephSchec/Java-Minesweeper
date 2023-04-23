package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MineSweeper extends JFrame {
    boolean displayBoard = false;
    Thread thread;

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
        int cellSize = 100;
        int rows = 8;
        int columns = 5;
        int mines = 20;
        setSize(cellSize * columns, (cellSize * rows > 800 ? 600 : cellSize * rows));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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
                    cell.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    cell.setOpaque(true);
                    cell.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            if (HelperFunctions.isEmpty(cell)) {
                                GamePlay.setCellText(row, col, cells);
                            }
                            Point clickedPoint = new Point(row, col);
                            if (GamePlay.allValues.containsKey(clickedPoint) && GamePlay.allValues.get(clickedPoint) == 9) {
                                int playAgain = JOptionPane.showConfirmDialog(null, "BOOM! Wanna play again?", "Minesweeper", JOptionPane.YES_NO_OPTION);
                                if (playAgain == JOptionPane.YES_OPTION) {
                                  new MineSweeper().setVisible(true);
                                }
                            }
                        }
                    });
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
        }
    }


}
