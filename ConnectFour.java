// andrew rios 
// ta : Shreya Nambi
// 04/17/2024
// C1 Abstract Strategy Game

/*
 * this class creates a stragetgy game called Connect 4 which players make a sequence of moves 
 * according to a set of rules hoping to achieve a particular outcome to win the game. Within
 * the rules players have free choice about what move to make with no luck or randomness involved 
 */

import java.util.*;

public class ConnectFour extends AbstractStrategyGame {
    private final String[][] GRID = new String[6][7];
    private final String RED = "🔴";
    private final String YELLOW = "🟡";
    private final String EMPTY = "⬜️";

    private List<String> players;
    private String piece;
    private int totalTurns;

    // creates a ConnectFour object with the given players and sets up the grid to contain
    // all empty spots
    // if either of the players names are null or empty an IllegalArgumentException is thrown
    public ConnectFour(String player1, String player2) {
        if (player1 == null || player1.isEmpty() || player2 == null || player2.isEmpty()) {
            throw new IllegalArgumentException("Player names cannot be null or empty.");
        }
        players = new ArrayList<>(List.of(player1, player2));
        piece = null;
        totalTurns = 0;
        setGird();
    }

    /**
     * Constructs and returns a String describing how to play the game. Including
     * relevant details on how to interpret the game state as, how to make moves, 
     * the game end condition, and how to win.
     */
    @Override
    public String instructions() {
        return "Connect Four Instructions:\n" +
                "-------------------------------------\n" +
                "Connect Four is a two-player game where the objective is to be the first to\n" +
                "connect four of your colored pieces in a row, horizontally, vertically, or " + 
                "diagonally.\n\n" +
                "Game State Representation:\n" +
                "---------------------------\n" +
                "The game state is represented by a 6x7 grid. Each cell can be empty (⬜️), " + 
                "contain a\n" +
                "red piece (🔴), or a yellow piece (🟡). Players' names and their corresponding " + 
                "piece colors\n" +
                "are displayed below the grid.\n\n" +
                "Making Moves:\n" +
                "--------------\n" +
                "Players take turns placing their pieces into one of the seven columns of the" +  
                " grid.\n" +
                "To make a move, enter the column number (1 - 7) where you want to place your " +
                "piece.\n\n" +
                "Game End Condition:\n" +
                "-------------------\n" +
                "The game ends when one player successfully connects four of their pieces in a " +
                "row,\n" +
                "or when the grid is completely filled with pieces and no player has achieved a "+ 
                "win.\n\n" +
                "How to Win:\n" +
                "------------\n" +
                "The first player to successfully connect four of their pieces in a row wins the "
                + "game.\n";
    }

    // develops and returns a String represenation of the current state of the game
    // including information about the current state of the grid, players and their
    // corresponding piece color
    @Override
    public String toString() {
        String output = "";
        for(String[] row : GRID) {
            for(String col : row) {
                output += col + "  ";
            }
            output += "\n";
        }
        output += "\n" + players.get(0) + ": " + RED + "     " + 
                  players.get(1) + ": " + YELLOW + "\n";
        return output;
    }

    /**
    * Returns the index of the player who has won the game,
    * or -1 if the game is not over.
    */
    @Override
    public int getWinner() {
        if (fourInaRow()) {
            if (totalTurns % 2 == 0) {
                return 2;
            } else {
                return 1;
            }
        } else if (totalTurns == 42) {
            return 0;
        }
        return -1;
    }


    /**
    * Returns the index of the player who will take the next turn.
    * If the game is over, returns -1.
    */
    @Override
    public int getNextPlayer() {
        if(fourInaRow() || totalTurns == 42) {
            return -1;
        } else if(totalTurns%2 == 0) {
            return 1;
        }
        return 2;
    }

    /**
    * Takes input from the parameter to specify the move the player
    * with the next turn wishes to make, then executes that move. 
    * If any part of the move is illegal, throws an IllegalArgumentException.
    */
    @Override
    public void makeMove(Scanner console) {
        System.out.println();
        System.out.println("Where would you like to add your piece (1 - 7)?");
        System.out.print("-> ");
        String input = console.next();
        if(!isValidInput(input)) {
            throw new IllegalArgumentException("Invalid input");
        }
        int col = Integer.parseInt(input) - 1;
        int player = getNextPlayer();

        if(player == 1) {
            piece = RED;
        } else {
            piece = YELLOW;
        }
        
        int row = GRID.length - 1;
        while(GRID[row][col] != EMPTY) {
            row --;
        }
        GRID[row][col] = piece;
        totalTurns ++;
    }

    // determines if a connect 4 has been made
    // returns true if so, and false otherwise
    public boolean fourInaRow() {
        // horizontal
        for (int row = 0; row < GRID.length; row++) {
            for (int col = 0; col < 4; col++) {
                if (!GRID[row][col].equals(EMPTY) &&
                    GRID[row][col].equals(GRID[row][col + 1]) &&
                    GRID[row][col].equals(GRID[row][col + 2]) &&
                    GRID[row][col].equals(GRID[row][col + 3])) {
                    return true;
                }
            }
        }
    
        // vertical
        for (int col = 0; col < GRID[0].length; col++) {
            for (int row = 0; row < 3; row++) {
                if (!GRID[row][col].equals(EMPTY) &&
                    GRID[row][col].equals(GRID[row + 1][col]) &&
                    GRID[row][col].equals(GRID[row + 2][col]) &&
                    GRID[row][col].equals(GRID[row + 3][col])) {
                    return true;
                }
            }
        }
    
        // positive slope
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                if (!GRID[row][col].equals(EMPTY) &&
                    GRID[row][col].equals(GRID[row + 1][col + 1]) &&
                    GRID[row][col].equals(GRID[row + 2][col + 2]) &&
                    GRID[row][col].equals(GRID[row + 3][col + 3])) {
                    return true;
                }
            }
        }
    
        // negative slope
        for (int row = 0; row < 3; row++) {
            for (int col = 3; col < 7; col++) {
                if (!GRID[row][col].equals(EMPTY) &&
                    GRID[row][col].equals(GRID[row + 1][col - 1]) &&
                    GRID[row][col].equals(GRID[row + 2][col - 2]) &&
                    GRID[row][col].equals(GRID[row + 3][col - 3])) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // this method checks if the input is valid, returns true if so, false if not
    public boolean isValidInput(String input) {
        Scanner inputScan = new Scanner(input);
        if(inputScan.hasNextInt()) {
            int num = inputScan.nextInt();
            if(inputScan.hasNext() || num<1 || num>7 || GRID[0][num-1] != EMPTY) {
                return false;
            }
            return true;
        }
        return false;
    }

    // returns a list of players currently playing
    public List<String> getPlayers() {
        return new ArrayList<>(players);
    }

    // gets the most current piece that was placed down
    // will return null if no piece has been placed yet
    public String getPiece() {
        return piece;
    }

    // returns the amount of turns that have been played so far
    public int getTotalTurns() {
        return totalTurns;
    }

    // returns the current state of the grid
    public String[][] getGrid() {
        String[][] copyGrid = new String[GRID.length][GRID[0].length];
        for (int row = 0; row < GRID.length; row++) {
            for(int col = 0; col<GRID[row].length; col++) {
                copyGrid[row][col] = GRID[row][col];
            }
        }
        return copyGrid;
    }

    // sets the current grid to contain all empty pieces
    private void setGird() {
        for(int row = 0; row<GRID.length; row++) {
            for(int col = 0; col<GRID[row].length; col++) {
                GRID[row][col] = EMPTY;
            }
        }
    }
}