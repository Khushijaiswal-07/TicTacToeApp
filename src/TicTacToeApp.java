import java.util.Random;
import java.util.Scanner;

public class TicTacToeApp {

    // UC1 → Board
    static char[][] board = new char[3][3];

    // UC2 → Game state
    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;

    public static void main(String[] args) {

        // UC2 → Toss
        tossAndAssignSymbols();
        displayTossResult();

        System.out.println();

        // UC1 → Initialize & display board
        initializeBoard();
        printBoard();

        System.out.println();

        // UC3 → Input
        int slot = getUserSlot();

        // UC4 → Convert
        int row = getRowFromSlot(slot);
        int col = getColFromSlot(slot);

        System.out.println("Row: " + row + ", Column: " + col);

        // UC5 → Validate
        if (isValidMove(row, col)) {
            System.out.println("Valid Move ✅");

            // UC6 → Place move
            placeMove(row, col, humanSymbol);

            System.out.println("\nUpdated Board:");
            printBoard();

        } else {
            System.out.println("Invalid Move ❌");
        }
    }

    // -------- UC2 --------
    static void tossAndAssignSymbols() {
        Random rand = new Random();
        int toss = rand.nextInt(2);

        if (toss == 0) {
            isHumanTurn = true;
            humanSymbol = 'X';
            computerSymbol = 'O';
        } else {
            isHumanTurn = false;
            humanSymbol = 'O';
            computerSymbol = 'X';
        }
    }

    static void displayTossResult() {
        if (isHumanTurn) {
            System.out.println("Human starts first");
        } else {
            System.out.println("Computer starts first");
        }

        System.out.println("Human Symbol: " + humanSymbol);
        System.out.println("Computer Symbol: " + computerSymbol);
    }

    // -------- UC1 --------
    static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    static void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // -------- UC3 --------
    static int getUserSlot() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter slot (1-9): ");
        return sc.nextInt();
    }

    // -------- UC4 --------
    static int getRowFromSlot(int slot) {
        return (slot - 1) / 3;
    }

    static int getColFromSlot(int slot) {
        return (slot - 1) % 3;
    }

    // -------- UC5 --------
    static boolean isValidMove(int row, int col) {

        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }

        if (board[row][col] != '-') {
            return false;
        }

        return true;
    }

    // -------- UC6 --------
    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }
}