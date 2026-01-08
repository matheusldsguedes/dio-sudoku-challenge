package service;

import model.Board;
import model.Position;

public class SudokuService {
    // create exception
    /*
     * I'm trying to make the validations and the service
     * accept any type of Sudoku in the future,
     * not just the classic 9x9.
     * */
    public void addNumber(Integer number, int row, int column, Board board){
        validateRanges(row, column, board);
        validateValue(number, board);
        Position position = board.getPosition(row, column);
        if(position.isFixedNumber()){
            throw new IllegalStateException("It is not possible to change a fixed position");
        }
        position.setValue(number);
    }
    public void removeNumber(int row, int column, Board board){

        validateRanges(row, column, board);
        Position position = board.getPosition(row, column);
        if(position.isFixedNumber()){
            throw new IllegalStateException("It is not possible to change a fixed position");
        }
        position.setValue(null);
    }

    private void validateRanges(int row, int column, Board board) {

        if (row < 0 || column < 0 || row > board.getGridValue() || column > board.getGridValue()){
            throw new IndexOutOfBoundsException("Row or column outside the range");
        }
    }
    private void validateValue(Integer number, Board board) {
        if (number < 1 || number > board.getGridValue()){
            throw new IllegalArgumentException("The value must be between 1 and " + board.getGridValue());
        }

    }

}
