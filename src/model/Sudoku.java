package model;

public class Sudoku {

    private Board board;
    private boolean completed;

    public Sudoku() {
        this.board = new Board(9);
    }
}
