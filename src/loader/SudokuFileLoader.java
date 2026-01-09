package loader;

import model.Board;
import model.Position;

import java.io.IOException;

public class SudokuFileLoader {

    public Board startGame(String content, int gridValue) throws IOException {

        String[] entries = content.trim().split("\\s+");
        int totalEntries = entries.length;

        if(gridValue * gridValue != totalEntries){
            throw new IllegalArgumentException("Invalid Sudoku: grid " + gridValue + "x" + gridValue +
                    " but found " + totalEntries + " of entries");
        }

        Board board = new Board(gridValue);

        for(String entry : entries){
            String[] parts = entry.split(";");
            String[] position = parts[0].split(",");
            String[] data = parts[1].split(",");

            int row = Integer.parseInt(position[0]);
            int column = Integer.parseInt(position[1]);

            int value = Integer.parseInt(data[0]);
            boolean isFixed = Boolean.parseBoolean(data[1]);
            Position pos = new Position(row, column);
            if (isFixed) {
                pos.setValue(value);
                pos.setFixedNumber(true);
            }
            board.setPosition(row, column, pos);
        }
        return board;

    }
}
