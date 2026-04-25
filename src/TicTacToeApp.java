import java.util.Random;
import java.util.Scanner;

public class TicTacToeApp {

    // UC1
    static char[][] board = new char[3][3];

    // UC2
    static boolean isHumanTurn;
    static boolean gameOver = false;
    static char humanSymbol;
    static char computerSymbol;

    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {

        // UC2
        tossAndAssignSymbols();
        displayTossResult();

        // UC1
        initializeBoard();

        // UC8 (Game Loop)
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

                    // UC9 → Win check
                    if (hasWon(humanSymbol)) {
                        System.out.println("\nFinal Board:");
                        printBoard();
                        System.out.println("🎉 Human Wins!");
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

                // UC9 → Win check
                if (hasWon(computerSymbol)) {
                    System.out.println("\nFinal Board:");
                    printBoard();
                    System.out.println("💻 Computer Wins!");
                    gameOver = true;
                    break;
                }

                isHumanTurn = true;
            }

            // UC10 → Draw check
            if (isDraw()) {
                System.out.println("\nFinal Board:");
                printBoard();
                System.out.println("Game Over - It's a Draw!");
                gameOver = true;
            }
        }
    }

    // -------- UC2 --------
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
        return board[row][col] == '-';
    }

    // -------- UC6 --------
    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    // -------- UC7 --------
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

    // -------- UC9 --------
    static boolean hasWon(char symbol) {

        // Rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol &&
                    board[i][1] == symbol &&
                    board[i][2] == symbol) return true;
        }

        // Columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == symbol &&
                    board[1][j] == symbol &&
                    board[2][j] == symbol) return true;
        }

        // Diagonals
        if (board[0][0] == symbol &&
                board[1][1] == symbol &&
                board[2][2] == symbol) return true;

        if (board[0][2] == symbol &&
                board[1][1] == symbol &&
                board[2][0] == symbol) return true;

        return false;
    }

    // -------- UC10 --------
    static boolean isDraw() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == '-') {
                    return false; // still empty → not draw
                }
            }
        }
        return true; // no empty cells → draw
    }
}