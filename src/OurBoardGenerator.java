import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class OurBoardGenerator {
    private int boardSize, totalBombs;
    private char[][] ourBoard;
    private ArrayList<Integer> aroundFirstI, aroundFirstJ;       // no bombs around first click

    public OurBoardGenerator(int firstClickI, int firstClickJ, int boardSize, int totalBombs) {
        this.boardSize = boardSize;
        this.totalBombs = totalBombs;
        aroundFirstI = new ArrayList<>(Arrays.asList(firstClickI - 1, firstClickI, firstClickI + 1));
        aroundFirstJ = new ArrayList<>(Arrays.asList(firstClickJ - 1, firstClickJ, firstClickJ + 1));
        ourBoard = new char[boardSize][boardSize];
    }


    public char[][] generatedOurBoard() {
        generateBombsOnOurBoard();
        while (!areBombsValidDistributed()) {
            generateBombsOnOurBoard();
        }
        generateNumbersOnOurBoard();

        return ourBoard;
    }

    private void generateBombsOnOurBoard() {
        Random rand = new Random();
        int randI, randJ;                   // indexes for generated bomb

        for (int bombAmount = 0; bombAmount < totalBombs; ) {
            randI = rand.nextInt(boardSize);
            randJ = rand.nextInt(boardSize);

            if (isBombAround(randI, randJ)) continue;

            if (ourBoard[randI][randJ] != '*') {
                ourBoard[randI][randJ] = '*';
                bombAmount++;
            }
        }
    }

    private boolean isBombAround(int cellI, int cellJ) {
        return aroundFirstI.contains(cellI) && aroundFirstJ.contains(cellJ);
    }

    private boolean areBombsValidDistributed() {       // no bomb should be surrounded all by bombs
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (isBombSurroundedByBombs(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isBombSurroundedByBombs(int cellI, int cellJ) {
        for (int i = cellI - 1; i < cellI + 2; i++) {
            for (int j = cellJ - 1; j < cellJ + 2; j++) {
                if (!isValidLocation(i, j)) continue;
                if (ourBoard[i][j] != '*') return false;
            }
        }
        return true;
    }


    private void generateNumbersOnOurBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (ourBoard[i][j] != '*') {
                    ourBoard[i][j] = bombNumberAroundCell(i, j);    // number as a char
                }
            }
        }
    }

    private char bombNumberAroundCell(int cellI, int cellJ) {
        int bombAmount = 0;

        for (int i = cellI - 1; i < cellI + 2; i++) {
            for (int j = cellJ - 1; j < cellJ + 2; j++) {
                if (!isValidLocation(i, j)) continue;
                if (ourBoard[i][j] == '*') bombAmount++;
            }
        }

        return bombAmount == 0 ? ' ' : (char) (bombAmount + '0');
    }

    private boolean isValidLocation(int cellI, int cellJ) {
        if (cellI < 0 || cellI == boardSize) return false;
        if (cellJ < 0 || cellJ == boardSize) return false;
        return true;
    }
}
