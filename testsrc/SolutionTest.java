import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SolutionTest {

    @Test
    public void simpleTest() {
        System.out.println("Simple game...\n ");
        assertEquals(26, Solution.bowling_score("54 72 44"));
        System.out.println();
    }

    @Test
    public void singleStrikeTest() {
        System.out.println("Threw a strike...\n ");
        assertEquals(28, Solution.bowling_score("X 54"));
        System.out.println();
    }

    @Test
    public void twoStrikesTest() {
        System.out.println("Threw two strikes...\n ");
        assertEquals(53, Solution.bowling_score("X X 54"));
        System.out.println();
    }

    @Test
    public void allOnesTest() {
        System.out.println("Maybe this bowler should put bumpers on...\n ");
        assertEquals(20, Solution.bowling_score("11 11 11 11 11 11 11 11 11 11"));
        System.out.println();
    }

    @Test
    public void allStrikesTest() {
        System.out.println("Woah! Perfect game! \n");
        assertEquals(300, Solution.bowling_score("X X X X X X X X X XXX"));
        System.out.println();
    }

    @Test
    public void simpleSpareTest() {
        System.out.println("A spare, nice! \n");
        assertEquals(24, Solution.bowling_score("9/ 54"));
        System.out.println();
    }


    @Test
    public void twoSparesTest() {
        System.out.println("Two spares! Wow! \n");
        assertEquals(27, Solution.bowling_score("8/ 7/"));
        System.out.println();
    }

    @Test
    public void spareAndStrikeTest() {
        System.out.println("Good shot! \n");
        assertEquals(59, Solution.bowling_score("X X 9/"));
        System.out.println();
    }

    @Test
    public void spareStrikeAndRegularTest() {
        System.out.println("Well played! \n");
        assertEquals(171, Solution.bowling_score("X X 9/ 80 X X 90 8/ 7/ 44"));
        System.out.println();
    }

}