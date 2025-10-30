import java.util.Scanner;

public class MineSweeper {
    private static Hardness level = Hardness.EASY;
    private static Scanner scanner = new Scanner(System.in);

    public static void play() {
        int x, y;
        Board board = new Board(level);
        board.intro();

        System.out.print("enter your first move (x, y): ");
        x = scanner.nextInt();
        y = scanner.nextInt();
        board.firstClick(x - 1, y - 1);

        while (true) {
            System.out.print("enter x and y: ");
            x = scanner.nextInt();
            y = scanner.nextInt();
            board.openCell(x - 1, y - 1);
        }
    }
}
