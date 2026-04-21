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

        // UC3 → Take input
        int slot = getUserSlot();

        // UC4 → Convert slot to row & column
        int row = getRowFromSlot(slot);
        int col = getColFromSlot(slot);

        System.out.println("Slot entered: " + slot);
        System.out.println("Row: " + row);
        System.out.println("Column: " + col);
    }

    // -------- UC2 METHODS --------
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
            System.out.println("Human won the toss and will play first.");
        } else {
            System.out.println("Computer won the toss and will play first.");
        }

        System.out.println("Human Symbol: " + humanSymbol);
        System.out.println("Computer Symbol: " + computerSymbol);
    }

    // -------- UC1 METHODS --------
    static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    static void printBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    // -------- UC3 METHOD --------
    static int getUserSlot() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter slot number (1-9): ");
        return sc.nextInt();
    }

    // -------- UC4 METHODS --------
    static int getRowFromSlot(int slot) {
        return (slot - 1) / 3;
    }

    static int getColFromSlot(int slot) {
        return (slot - 1) % 3;
    }
}