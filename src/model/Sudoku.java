package model;

public class Sudoku {

    private Board board;
    private boolean completed;
    private final int typeSudoku = 9;

    public Sudoku() {
        this.board = new Board(typeSudoku);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
