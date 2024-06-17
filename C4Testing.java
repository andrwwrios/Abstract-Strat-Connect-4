import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class C4Testing {
    
    @Test
    @DisplayName("Correct output for a tie")
    public void testTie() {
        AbstractStrategyGame test = new ConnectFour("Andrew", "Jessica");

        for(int i = 0; i<6; i++) {
            if(i%2 == 0) {
                test.makeMove(new Scanner("1"));
            } else {
                test.makeMove(new Scanner("2"));                
            }
        }
        for(int i = 0; i<6; i++) {
            if(i%2 == 0) {
                test.makeMove(new Scanner("2"));              
            } else {
                test.makeMove(new Scanner("1"));                
            }
        }
        for(int i = 0; i<6; i++) {
            if(i%2 == 0) {
                test.makeMove(new Scanner("3"));                
            } else {
                test.makeMove(new Scanner("4"));               
            }
        }
        for(int i = 0; i<6; i++) {
            if(i%2 == 0) {
                test.makeMove(new Scanner("4"));               
            } else {
                test.makeMove(new Scanner("3"));                
            }
        }
        for(int i = 0; i<6; i++) {
            if(i%2 == 0) {
                test.makeMove(new Scanner("5"));                
            } else {
                test.makeMove(new Scanner("6"));               
            }
        }
        for(int i = 0; i<6; i++) {
            if(i%2 == 0) {
                test.makeMove(new Scanner("6"));               
            } else {
                test.makeMove(new Scanner("5"));                
            }
        }
        for(int i = 0; i<6; i++) {
            test.makeMove(new Scanner("7"));               
        }

        assertFalse(((ConnectFour)test).fourInaRow());
        assertEquals(-1, ((ConnectFour)test).getNextPlayer());
        assertEquals(0, ((ConnectFour)test).getWinner());
        assertEquals(42, ((ConnectFour)test).getTotalTurns());
        assertEquals("🟡", ((ConnectFour)test).getPiece());
    }

    @Test
    @DisplayName("Connect 4 Initialization")
    public void testConstructor() {
        AbstractStrategyGame test = new ConnectFour("Andrew", "Jessica");

        assertEquals(new ArrayList<String>(List.of("Andrew", "Jessica")),
                                    ((ConnectFour)test).getPlayers());
        assertNull(((ConnectFour)test).getPiece());
        assertEquals(0, ((ConnectFour)test).getTotalTurns());
        assertArrayEquals(grid(), ((ConnectFour)test).getGrid());
        assertFalse(test.isGameOver());
        assertEquals(1, test.getNextPlayer());
        assertFalse(((ConnectFour)test).fourInaRow());
        assertThrows(IllegalArgumentException.class, () -> {new ConnectFour(null, "");});
    }

    @Test
    @DisplayName("Correct Implementation in checking for valid input")
    public void testIsValidInput() {
        AbstractStrategyGame test = new ConnectFour("Andrew", "Jessica");

        Random rand = new Random();
        for(int i = 0; i<10; i++) {
            int num = rand.nextInt(1,8);
            String input = "" + num;

            assertTrue(((ConnectFour)test).isValidInput(input));

        }

        assertFalse(((ConnectFour)test).isValidInput("0"));
        assertFalse(((ConnectFour)test).isValidInput("8"));
        assertFalse(((ConnectFour)test).isValidInput("0 1"));
        assertFalse(((ConnectFour)test).isValidInput("1 0"));
        assertFalse(((ConnectFour)test).isValidInput("this"));
    }

    @Test
    @DisplayName("Horizontal Win - RED")
    public void testHorizontalWin() {
        AbstractStrategyGame test = new ConnectFour("Andrew", "Jessica");
        test.makeMove(new Scanner("1"));
        test.makeMove(new Scanner("1"));
        test.makeMove(new Scanner("2"));
        test.makeMove(new Scanner("1"));
        test.makeMove(new Scanner("3"));
        test.makeMove(new Scanner("1"));
        test.makeMove(new Scanner("4"));

        String[][] grid = grid();
        grid[5][0] = "🔴";
        grid[5][1] = "🔴";
        grid[5][2] = "🔴";
        grid[5][3] = "🔴";
        grid[4][0] = "🟡";
        grid[3][0] = "🟡";
        grid[2][0] = "🟡";

        assertArrayEquals(grid, ((ConnectFour)test).getGrid());
        assertTrue(((ConnectFour)test).fourInaRow());
        assertEquals(-1, ((ConnectFour)test).getNextPlayer());
        assertEquals(1, ((ConnectFour)test).getWinner());
        assertEquals(7, ((ConnectFour)test).getTotalTurns());
        assertEquals("🔴", ((ConnectFour)test).getPiece());

    }

    @Test
    @DisplayName("Vertical Win - YELLOW")
    public void testVerticalWin() {
        AbstractStrategyGame test = new ConnectFour("Andrew", "Jessica");
        for(int i = 0; i<6; i++) {
            if(i%2 == 0) {
                test.makeMove(new Scanner("1"));
            } else {
                test.makeMove(new Scanner("2"));
            }
        }
        test.makeMove(new Scanner("3"));
        test.makeMove(new Scanner("2"));
        String[][] grid = grid();
        grid[5][0] = "🔴";
        grid[4][0] = "🔴";
        grid[3][0] = "🔴";
        grid[5][2] = "🔴";
        grid[5][1] = "🟡";
        grid[4][1] = "🟡";
        grid[3][1] = "🟡";
        grid[2][1] = "🟡";

        assertArrayEquals(grid, ((ConnectFour)test).getGrid());
        assertTrue(((ConnectFour)test).fourInaRow());
        assertEquals(-1, ((ConnectFour)test).getNextPlayer());
        assertEquals(2, ((ConnectFour)test).getWinner());
        assertEquals(8, ((ConnectFour)test).getTotalTurns());
        assertEquals("🟡", ((ConnectFour)test).getPiece());
    }

    @Test
    @DisplayName("Positive Diagonal Win - RED")
    public void testPosDiagWin() {
        AbstractStrategyGame test = new ConnectFour("Andrew", "Jessica");
        test.makeMove(new Scanner("1"));
        test.makeMove(new Scanner("2"));
        test.makeMove(new Scanner("2"));
        test.makeMove(new Scanner("3"));
        test.makeMove(new Scanner("3"));
        test.makeMove(new Scanner("4"));
        test.makeMove(new Scanner("3"));
        test.makeMove(new Scanner("4"));
        test.makeMove(new Scanner("4"));
        test.makeMove(new Scanner("2"));
        test.makeMove(new Scanner("4"));
        String[][] grid = grid();
        grid[5][0] = "🔴";
        grid[4][1] = "🔴";
        grid[4][2] = "🔴";
        grid[3][2] = "🔴";
        grid[3][3] = "🔴";
        grid[2][3] = "🔴";
        grid[5][1] = "🟡";
        grid[3][1] = "🟡";
        grid[5][2] = "🟡";
        grid[5][3] = "🟡";
        grid[4][3] = "🟡";

        assertArrayEquals(grid, ((ConnectFour)test).getGrid());
        assertTrue(((ConnectFour)test).fourInaRow());
        assertEquals(-1, ((ConnectFour)test).getNextPlayer());
        assertEquals(1, ((ConnectFour)test).getWinner());
        assertEquals(11, ((ConnectFour)test).getTotalTurns());
        assertEquals("🔴", ((ConnectFour)test).getPiece());
    }

    @Test
    @DisplayName("Negative Diagonal Win - YELLOW")
    public void testNegDiagWin() {
        AbstractStrategyGame test = new ConnectFour("Andrew", "Jessica");
        test.makeMove(new Scanner("1"));
        test.makeMove(new Scanner("4"));
        test.makeMove(new Scanner("1"));
        test.makeMove(new Scanner("3"));
        test.makeMove(new Scanner("1"));
        test.makeMove(new Scanner("3"));
        test.makeMove(new Scanner("2"));
        test.makeMove(new Scanner("1"));
        test.makeMove(new Scanner("2"));
        test.makeMove(new Scanner("2"));
        String[][] grid = grid();
        grid[5][0] = "🔴";
        grid[4][0] = "🔴";
        grid[3][0] = "🔴";
        grid[5][1] = "🔴";
        grid[4][1] = "🔴";
        grid[2][0] = "🟡";
        grid[3][1] = "🟡";
        grid[4][2] = "🟡";
        grid[5][3] = "🟡";
        grid[5][2] = "🟡";

        assertArrayEquals(grid, ((ConnectFour)test).getGrid());
        assertTrue(((ConnectFour)test).fourInaRow());
        assertEquals(-1, ((ConnectFour)test).getNextPlayer());
        assertEquals(2, ((ConnectFour)test).getWinner());
        assertEquals(10, ((ConnectFour)test).getTotalTurns());
        assertEquals("🟡", ((ConnectFour)test).getPiece());
    }

    // creates empty grid for connect 4 tests
    private String[][] grid() {
        String[][] result = new String [6][7];
        for(int row = 0; row<result.length; row++) {
            for(int col = 0; col<result[row].length; col ++) {
                result[row][col] = "⬜️";
            }
        }
        return result;
    }
}
