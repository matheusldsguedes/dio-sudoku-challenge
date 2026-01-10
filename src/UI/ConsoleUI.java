package UI;

import exception.SudokuException;
import loader.SudokuFileLoader;
import model.Board;
import model.Position;
import model.Sudoku;
import service.SudokuService;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ConsoleUI {

    public ConsoleUI(Sudoku sudoku) {
        this.board = sudoku.getBoard();
    }

    private Board board;
    private final SudokuService service = new SudokuService();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        loadGame();

        int option;
        do {
            /*
            *  Clearing the screen doesn't work in IntelliJ,
            *  so I left it commented out because I haven't tested it yet
            *  and I'll probably have to show the error after the incorrect action,
            *  and only clear the screen after it corrects the action.
            * */
            //clearScreen();
            printBoard();
            printMenu();

            option = readInt("Choose an option:");

            switch (option) {
                case 1 -> insertNumber();
                case 2 -> removeNumber();
                case 3 -> {
                    if(endGame()) option = 0;
                }
                case 0 -> System.out.println("Game over...");
                default -> System.out.println("Invalid option.");
            }

        } while (option != 0);
    }

    private void insertNumber() {
        try{

        int row = readInt("On which line do you want to insert it? (1 a 9)")-1;
        int column = readInt("In which column do you want to insert it? (1 a 9)")-1;
        int value = readInt("What value do you want to enter? (1 a 9)");

        service.addNumber(row, column, value, board);
        System.out.println("Number successfully entered");
        } catch (SudokuException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void removeNumber() {
        int row = readInt("Which line do you want to remove it from? (1 a 9)")-1;
        int column = readInt("Which column do you want to remove it from? (1 a 9)")-1;

        try {
            service.removeNumber(row, column, board);
            System.out.println("Number removed.");
        } catch (SudokuException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void printMenu() {
        System.out.println("""
                
                ===== MENU =====
                1 - Insert number
                2 - Remove number
                3 - Conclude
                0 - Quit
                """);
    }

    private int readInt(String message) {
        while (true) {
            System.out.println(message);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }


    private void printBoard() {

        int size = board.getGridValue();
        int section = (int) Math.sqrt(size);

        int cellWidth = String.valueOf(size).length() + 1;

        System.out.print("    ");
        for (int col = 1; col <= size; col++) {
            System.out.printf("%" + cellWidth + "d", col);
            if (col % section == 0 && col != size) {
                System.out.print(" ");
            }
        }
        System.out.println();

        printHorizontalSeparator(size, section, cellWidth);

        for (int row = 0; row < size; row++) {

            System.out.printf("%3d |", row + 1);

            for (int col = 0; col < size; col++) {

                Position p = board.getPosition(row, col);
                String value = (p.getValue() == null) ? "." : p.getValue().toString();

                System.out.printf("%" + cellWidth + "s", value);

                if ((col + 1) % section == 0) {
                    System.out.print("|");
                }
            }
            System.out.println();

            if ((row + 1) % section == 0) {
                printHorizontalSeparator(size, section, cellWidth);
            }
        }
    }

    private void printHorizontalSeparator(int size, int section, int cellWidth) {

        System.out.print("    +");

        for (int i = 0; i < size; i++) {
            System.out.print("-".repeat(cellWidth));
            if ((i + 1) % section == 0) {
                System.out.print("+");
            }
        }
        System.out.println();
    }

    /*
    private void clearScreen() {
       System.out.print("\033[H\033[2J");
       System.out.flush();
    }
    */

    private void loadGame() {
        try {
            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream("classicSudoku/sudoku1.txt");

            if (is == null) {
                throw new RuntimeException("File not found");
            }

            String conteudo = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            board = new SudokuFileLoader().startGame(conteudo, board.getGridValue());

        } catch (Exception e) {
            throw new RuntimeException("Error loading the game", e);
        }
    }
    private boolean endGame() {
        if (service.isGameFinished(board)) {
            printBoard();
            System.out.println("Sudoku successfully completed!");
            return true;
        } else {
            System.out.println("The Sudoku puzzle is not correct yet");
            return false;
        }
    }


}
