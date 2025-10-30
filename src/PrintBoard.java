import java.util.ArrayList;

public class PrintBoard {
    private ArrayList<String> borders = new ArrayList<>();       // for board printing
    private int boardSize;

    {
        borders.add("  | ");
        for (int i = 1; i < 10; i++) {
            borders.add("" + i + " ");
        }
        for (int i = 10; i < 21; i++) {
            borders.add("" + i);
        }
    }

    public PrintBoard(int boardSize) {
        this.boardSize = boardSize;
    }

    public void print(char[][] userBoard) {
        for (int i = 0; i <= boardSize; i++) {
            System.out.print(borders.get(i) + " ");
        }
        System.out.print("\n----");
        for (int i = 0; i < boardSize; i++) {
            System.out.print("---");
        }
        System.out.println();

        for (int i = 0; i < boardSize; i++) {
            System.out.print(borders.get(i + 1) + "|  ");
            for (int j = 0; j < boardSize; j++) {
                System.out.print(userBoard[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
