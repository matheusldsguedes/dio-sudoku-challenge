package model;

public class Board {
    private Position[][] grid;
    private final int gridValue;

    public Board(int gridValue) {
        this.grid = new Position[gridValue][gridValue];
        this.gridValue = gridValue;
    }

    public int getGridValue() {
        return gridValue;
    }

    public void setPosition(int row,  int column, Position position) {
        grid[row][column] = position;
    }

    public Position getPosition(int row, int column){
        return grid[row][column];
    }

}
