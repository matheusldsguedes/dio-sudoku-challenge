package validator;

import exception.SudokuException;
import model.Board;

import java.util.HashSet;
import java.util.Set;

public class SudokuValidator {

    public void validatePosition(int row, int column, Board board) {
        int size = board.getGridValue();

        if (row < 0 || column < 0 || row >= size || column >= size) {
            throw new SudokuException("Row or column outside the range");
        }
    }

    public void validateValue(int value, Board board) {
        if (value < 1 || value > board.getGridValue()) {
            throw new SudokuException(
                    "The value must be between 1 and " + board.getGridValue()
            );
        }
    }

    public boolean checkWrongValue(int row, int column, int value, Board board) {
        return !existsInRow(row, value, board)
                && !existsInColumn(column, value, board)
                && !existsInSection(row, column, value, board);
    }

    private boolean existsInRow(int row, int value, Board board) {
        for (int col = 0; col < board.getGridValue(); col++) {
            Integer v = board.getPosition(row, col).getValue();
            if (v != null && v == value) return true;
        }
        return false;
    }

    private boolean existsInColumn(int column, int value, Board board) {
        for (int row = 0; row < board.getGridValue(); row++) {
            Integer v = board.getPosition(row, column).getValue();
            if (v != null && v == value) return true;
        }
        return false;
    }

    private boolean existsInSection(int row, int column, int value, Board board) {
        int sectionSize = (int) Math.sqrt(board.getGridValue());
        int startRow = (row / sectionSize) * sectionSize;
        int startCol = (column / sectionSize) * sectionSize;

        for (int r = startRow; r < startRow + sectionSize; r++) {
            for (int c = startCol; c < startCol + sectionSize; c++) {
                Integer v = board.getPosition(r, c).getValue();
                if (v != null && v == value) return true;
            }
        }
        return false;
    }
    public boolean isSudokuComplete(Board board) {

        int size = board.getGridValue();
        int sectionSize = (int) Math.sqrt(size);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.getPosition(row, col).getValue() == null) {
                    return false;
                }
            }
        }

        for (int row = 0; row < size; row++) {
            if (isGroupValid(row, 0, 0, 1, board)) return false;
        }

        for (int col = 0; col < size; col++) {
            if (isGroupValid(0, col, 1, 0, board)) return false;
        }

        for (int row = 0; row < size; row += sectionSize) {
            for (int col = 0; col < size; col += sectionSize) {
                if (!isSectionValid(board, row, col, sectionSize)) return false;
            }
        }

        return true;
    }
    private boolean isGroupValid(int startRow, int startCol, int rowStep, int colStep, Board board) {
        Set<Integer> seen = new HashSet<>();

        for (int i = 0; i < board.getGridValue(); i++) {
            int value = board.getPosition(
                    startRow + i * rowStep,
                    startCol + i * colStep
            ).getValue();

            if (!seen.add(value)) return true;
        }
        return false;
    }

    private boolean isSectionValid(Board board, int startRow, int startCol, int size) {
        Set<Integer> seen = new HashSet<>();

        for (int r = startRow; r < startRow + size; r++) {
            for (int c = startCol; c < startCol + size; c++) {
                int value = board.getPosition(r, c).getValue();
                if (!seen.add(value)) return false;
            }
        }
        return true;
    }
}
