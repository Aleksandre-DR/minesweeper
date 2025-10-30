import java.util.Arrays;
import java.util.Scanner;

public class Board {
    private int boardSize, totalBombs;
    private char[][] userBoard;             // front end board for design
    private char[][] ourBoard;              // back end board for calculations
    private PrintBoard pb;                  // class for printing an user board
    private int closedCells;                // how many more cells should be opened
    Scanner sc;


    Board(Hardness level) {
        configureLevel(level);
        userBoard = new char[boardSize][boardSize];
        pb = new PrintBoard(boardSize);
        sc = new Scanner(System.in);
    }

    private void configureLevel(Hardness hardness) {
        boardSize = hardness.boardSIze;
        totalBombs = hardness.boardSIze;
        closedCells = boardSize * boardSize - totalBombs;
    }

    public void firstClick(int x, int y) {
        OurBoardGenerator bg = new OurBoardGenerator(x, y, boardSize, totalBombs);
        ourBoard = bg.generatedOurBoard();
        openCell(x, y);
    }

    public void openCell(int x, int y) {
        while (userBoard[x][y] != 0) {
            System.out.print("cell is already open, try again: ");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        }

        char iconInCell = ourBoard[x][y];
        if (iconInCell == '*') {
            bombGotClicked();
        }

        if (iconInCell == ' ') {
            zeroCellGotClicked(x, y);
            return;
        }

        userBoard[x][y] = iconInCell;
        closedCells--;
        didWeWin();
        printBoard(userBoard);
    }

    private void bombGotClicked() {
        System.out.println("\nBOMB, you lost");
        printBoard(ourBoard);
        System.exit(1);
    }

    private void zeroCellGotClicked(int x, int y) {
        userBoard[x][y] = ' ';
        closedCells--;
        openCellsWithZero(x, y);
        didWeWin();
        printBoard(userBoard);
    }

    // opens all zero-containing cells around the cell that had zero
    private void openCellsWithZero(int x, int y) {
        for (int i = x - 1; i < x + 2; i++) {           // checking all around cells
            for (int j = y - 1; j < y + 2; j++) {
                if (!isValidLocation(i, j)) continue;
                if (userBoard[i][j] == 0) {             // if cell is closed
                    if (ourBoard[i][j] == ' ') {            // if that cell has ' ' in it
                        userBoard[i][j] = ' ';
                        openCellsWithZero(i, j);            // examine even further
                    } else {
                        userBoard[i][j] = ourBoard[i][j];   // assign whatever it was
                    }
                    closedCells--;
                }
            }
        }
    }

    private boolean isValidLocation(int cellI, int cellJ) {
        if (cellI < 0 || cellI == boardSize) return false;
        if (cellJ < 0 || cellJ == boardSize) return false;
        return true;
    }

    private void didWeWin() {
        if (closedCells == 0) {
            System.out.println("\ncongretulations, you won");
            printBoard(ourBoard);
            System.exit(1);
        }
    }

    public void intro() {
        System.out.println("\nboard's size is " + boardSize + "x" + boardSize);
        System.out.println("there are " + totalBombs + " mines");
        System.out.println();
        printBoard(userBoard);
    }

    public void printBoard(char[][] board) {
        System.out.println();
        pb.print(board);
    }
}
