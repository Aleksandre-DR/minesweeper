public enum Hardness {
    EASY(10, 5),
    MEDIUM(16, 50),
    HARD(20, 80);

    int boardSIze, totalBombs;

    Hardness(int boardSize, int totalBombs) {
        this.boardSIze = boardSize;
        this.totalBombs = totalBombs;
    }
}
