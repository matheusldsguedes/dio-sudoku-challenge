package service;

import exception.SudokuException;
import model.Board;
import model.Position;
import validator.SudokuValidator;

public class SudokuService {
    // create exception
    /*
     * I'm trying to make the validations and the service
     * accept any type of Sudoku in the future,
     * not just the classic 9x9.
     * */

    private final SudokuValidator validator = new SudokuValidator();

    public void addNumber(int row, int column, Integer number, Board board){
        validator.validatePosition(row, column, board);
        validator.validateValue(number, board);
        Position position = board.getPosition(row, column);
        if (position.isFixedNumber()) {
            throw new SudokuException("It is not possible to change a fixed position");
        }
        position.setHasError(!validator.checkCorrectValue(row , column, number, board));
        position.setValue(number);

        System.out.println(position.hasError()); //testing
    }
    public void removeNumber(int row, int column, Board board){

        validator.validatePosition(row, column, board);
        Position position = board.getPosition(row, column);
        if (position.isFixedNumber()) {
            throw new SudokuException("It is not possible to change a fixed position");
        }
        position.setValue(null);
    }

    public boolean isGameFinished(Board board) {
        return validator.isSudokuComplete(board);
    }

}
