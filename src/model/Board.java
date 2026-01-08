package model;

public class Board {
    private final Position[][] grid;

    public Board(int gridValue) {
        this.grid = new Position[gridValue][gridValue];
    }

    public Position getPosition(int row, int column){
        return grid[row][column];
    }

}
