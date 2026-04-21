import java.util.Random;

public class TicTacToeApp {

    // UC1 → Board
    static char[][] board = new char[3][3];

    // UC2 → Game state
    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;

    public static void main(String[] args) {

        // UC2 → Toss first
        tossAndAssignSymbols();
        displayTossResult();

        System.out.println();

        // UC1 → Then show board
        initializeBoard();
        printBoard();
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
}