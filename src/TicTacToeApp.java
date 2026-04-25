import java.util.Random;
import java.util.Scanner;

public class TicTacToeApp {

    static char[][] board = new char[3][3];

    static boolean isHumanTurn;
    static boolean gameOver = false;
    static char humanSymbol;
    static char computerSymbol;

    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {

        tossAndAssignSymbols();
        displayTossResult();

        initializeBoard();

        while (!gameOver) {

            System.out.println("\nCurrent Board:");
            printBoard();

            if (isHumanTurn) {
                System.out.println("\nYour Turn");

                int slot = getUserSlot();
                int row = getRowFromSlot(slot);
                int col = getColFromSlot(slot);

                if (isValidMove(row, col)) {
                    placeMove(row, col, humanSymbol);

                    // ✅ WIN → print final board then stop
                    if (isWin()) {
                        System.out.println("\nFinal Board: (GAME OVER)");
                        printBoard();
                        gameOver = true;
                        break;
                    }

                    isHumanTurn = false;
                } else {
                    System.out.println("Invalid Move ❌ Try again.");
                }

            } else {
                System.out.println("\nComputer Turn");

                computerMove();

                // ✅ WIN → print final board then stop
                if (isWin()) {
                    System.out.println("\nFinal Board: (GAME OVER)");
                    printBoard();
                    gameOver = true;
                    break;
                }

                isHumanTurn = true;
            }

            // Draw condition
            if (isBoardFull()) {
                gameOver = true;
                System.out.println("\nGame Over - It's a Draw!");
                printBoard();
            }
        }
    }

    // UC2
    static void tossAndAssignSymbols() {
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

    // UC1
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

    // UC3
    static int getUserSlot() {
        System.out.print("Enter slot (1-9): ");
        return sc.nextInt();
    }

    // UC4
    static int getRowFromSlot(int slot) {
        return (slot - 1) / 3;
    }

    static int getColFromSlot(int slot) {
        return (slot - 1) % 3;
    }

    // UC5
    static boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        return board[row][col] == '-';
    }

    // UC6
    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    // UC7
    static void computerMove() {
        int slot, row, col;

        while (true) {
            slot = rand.nextInt(9) + 1;
            row = getRowFromSlot(slot);
            col = getColFromSlot(slot);

            if (isValidMove(row, col)) {
                placeMove(row, col, computerSymbol);
                System.out.println("Computer chose slot: " + slot);
                break;
            }
        }
    }

    // UC8 helper
    static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Win check (only for stopping loop)
    static boolean isWin() {

        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' &&
                    board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2]) return true;
        }

        for (int j = 0; j < 3; j++) {
            if (board[0][j] != '-' &&
                    board[0][j] == board[1][j] &&
                    board[1][j] == board[2][j]) return true;
        }

        if (board[0][0] != '-' &&
                board[0][0] == board[1][1] &&
                board[1][1] == board[2][2]) return true;

        if (board[0][2] != '-' &&
                board[0][2] == board[1][1] &&
                board[1][1] == board[2][0]) return true;

        return false;
    }
}