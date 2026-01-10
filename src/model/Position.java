package model;

import java.util.Objects;

public class Position {

    private final int row;
    private final int colunm;
    private Integer value;
    private boolean fixedNumber;
    private boolean hasError;

    public Position(int row, int colunm, Integer value, boolean fixedNumber) {
        this.row = row;
        this.colunm = colunm;
        this.value = value;
        this.fixedNumber = fixedNumber;
        this.hasError = false;
    }

    public Position(int row, int colunm) {
        this.row = row;
        this.colunm = colunm;
        this.value = null;
        this.fixedNumber = false;
        this.hasError = false;
    }

    public int getRow() {
        return row;
    }

    public int getColunm() {
        return colunm;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean isFixedNumber() {
        return fixedNumber;
    }

    public void setFixedNumber(boolean fixedNumber) {
        this.fixedNumber = fixedNumber;
    }

    public boolean hasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && colunm == position.colunm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, colunm);
    }
}
