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
        position.setHasError(validatorValue(number, row , column, board));
    }
    public void removeNumber(int row, int column, Board board){

        validateRanges(row, column, board);
        Position position = board.getPosition(row, column);
        if(position.isFixedNumber()){
            throw new IllegalStateException("It is not possible to change a fixed position");
        }
        position.setValue(null);
    }

    public void validateRanges(int row, int column, Board board) {

        if (row < 0 || column < 0 || row > board.getGridValue() || column > board.getGridValue()){
            throw new IndexOutOfBoundsException("Row or column outside the range");
        }
    }
    public void validateValue(Integer number, Board board) {
        if (number < 1 || number > board.getGridValue()){
            throw new IllegalArgumentException("The value must be between 1 and " + board.getGridValue());
        }

    }
    public boolean validatorValue(Integer value, int row, int column, Board board) {
        return !existsLine(row, value, board)
                && !existsColumn(column, value, board)
                && !existsSection(value, row, column, board);
    }

    public boolean existsLine(Integer value, int row, Board board) {
        for (int col = 0; col < 9; col++) {
            Integer v = board.getPosition(row, col).getValue();
            if (v != null && v.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public boolean existsColumn(Integer value,int column, Board board) {
        for (int lin = 0; lin < 9; lin++) {
            Integer v = board.getPosition(lin, column).getValue();
            if (v != null && v.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public boolean existsSection(Integer value, int row, int column, Board board) {
        int beginningRow = (row / 3) * 3;
        int beginningColumn = (column / 3) * 3;

        for (int i = beginningRow; i < beginningRow + 3; i++) {
            for (int j = beginningColumn; j < beginningColumn + 3; j++) {
                Integer v = board.getPosition(i, j).getValue();
                if (v != null && v.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

}
