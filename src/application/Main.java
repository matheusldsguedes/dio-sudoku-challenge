package application;

import UI.ConsoleUI;
import model.Sudoku;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        ConsoleUI console = new ConsoleUI(sudoku);
        console.start();
    }
}