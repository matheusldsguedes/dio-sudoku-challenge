package UI;

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
            printBoard();
            printMenu();

            option = readInt("Choose an option:");

            switch (option) {
                case 1 -> inserirNumero();
                case 2 -> removerNumero();
                case 0 -> System.out.println("Game over...");
                default -> System.out.println("Invalid option.");
            }

        } while (option != 0);
    }

    private void inserirNumero() {
        int row = readInt("On which line do you want to insert it? (1 a 9)")-1;
        int column = readInt("In which column do you want to insert it? (1 a 9)")-1;
        int value = readInt("What value do you want to enter? (1 a 9)");


        service.addNumber(value, row, column, board);
        System.out.println("Number successfully entered");
    }

    private void removerNumero() {
        int row = readInt("Which line do you want to remove it from? (1 a 9)")-1;
        int column = readInt("Which column do you want to remove it from? (1 a 9)")-1;

        try {
            service.removeNumber(row, column, board);
            System.out.println("Number removed.");
        } catch (Exception e) {
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

    private int readInt(String mensagem) {
        System.out.println(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println("Enter a valid number");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void printBoard() {

        System.out.println();
        System.out.print("    ");
        for (int col = 1; col <= 9; col++) {
            System.out.print(col + " ");
            if (col % 3 == 0 && col != 9) {
                System.out.print("  ");
            }
        }
        System.out.println();

        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.println("  +-------+-------+-------+");
            }
            System.out.print((i + 1) + " | ");

            for (int j = 0; j < 9; j++) {

                Position p = board.getPosition(i, j);
                String value = (p.getValue() == null) ? "." : p.getValue().toString();
                System.out.print(value + " ");

                if ((j + 1) % 3 == 0 && j != 8) {
                    System.out.print("| ");
                }
            }
            System.out.println("|");
        }
        System.out.println("  +-------+-------+-------+");
    }


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
            throw new RuntimeException("Error loading the game.", e);
        }
    }
}
