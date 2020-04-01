package algoritmen;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TaakPloegenIndelingTests {
    //TODO: Testen aanvullen
    //<editor-fold desc="Spelverdeling Tests">
    //Spelverdeling Testen
    //Invalid Input Testen
    @Test(expected = InvalidInputException.class)
    public void spelverdeling_onevenPloegen_throwsException(){
        // 1. ARRANGE
        Optional<String[][]> oplossing = TaakPloegenIndeling.spelverdeling(3,3,3,3);
    }
    @Test(expected = InvalidInputException.class)
    public void spelverdeling_0Ploegen_throwsException(){
        // 1. ARRANGE
        Optional<String[][]> oplossing = TaakPloegenIndeling.spelverdeling(0,3,3,3);
    }
    @Test(expected = InvalidInputException.class)
    public void spelverdeling_0Spelletjes_throwsException(){
        // 1. ARRANGE
        Optional<String[][]> oplossing = TaakPloegenIndeling.spelverdeling(4,0,3,3);
    }
    @Test(expected = InvalidInputException.class)
    public void spelverdeling_0dubbels_throwsException(){
        // 1. ARRANGE
        Optional<String[][]> oplossing = TaakPloegenIndeling.spelverdeling(4,3,0,3);
    }
    @Test(expected = InvalidInputException.class)
    public void spelverdeling_neg1rondes_throwsException(){
        // 1. ARRANGE
        Optional<String[][]> oplossing = TaakPloegenIndeling.spelverdeling(4,3,3,-1);
    }
    //</editor-fold>

    //<editor-fold desc="getPairs Tests">
    //getPairs Testen
    @Test(expected = InvalidInputException.class)
    public void getPairs_0_EMPTY(){
        ArrayList<String> solution = TaakPloegenIndeling.getPairs(0);
    }
    @Test(expected = InvalidInputException.class)
    public void getPairs_1_InvalidInputException(){
        ArrayList<String> solution = TaakPloegenIndeling.getPairs(1);
    }
    @Test(expected = InvalidInputException.class)
    public void getPairs_27_InvalidInputException(){
        ArrayList<String> solution = TaakPloegenIndeling.getPairs(27);
    }
    @Test
    public void getPairs_2_AlineB(){
        ArrayList<String> solution = TaakPloegenIndeling.getPairs(2);
        ArrayList<String> realSolution = new ArrayList<>(Collections.singletonList("A-B"));
        assertThat(solution, is(realSolution));
    }
    @Test
    public void getPairs_6_AllUniqueCombABCDEF(){
        ArrayList<String> solution = TaakPloegenIndeling.getPairs(6);
        ArrayList<String> realSolution = new ArrayList<>(Arrays.asList("A-B","A-C","A-D","A-E","A-F","B-C","B-D","B-E","B-F","C-D","C-E","C-F","D-E","D-F","E-F"));
        assertThat(solution, is(realSolution));
    }
    //</editor-fold>

    //<editor-fold desc="getTeamsFromPair Tests">
    //getTeamsFromPair Testen
    @Test
    public void getTeamsFromPair_AlineB_AandB() {
        // 1. ARRANGE
        // 2. ACT
        ArrayList<String> solution = TaakPloegenIndeling.getTeamsFromPair("A-B");
        // 3. ASSERT
        ArrayList<String> realSolution = new ArrayList<>(Arrays.asList("A","B"));
        assertThat(solution, is(realSolution));
    }
    @Test
    public void getTeamsFromPair_YlineZ_YandZ() {
        // 1. ARRANGE
        // 2. ACT
        ArrayList<String> solution = TaakPloegenIndeling.getTeamsFromPair("Y-Z");
        // 3. ASSERT
        ArrayList<String> realSolution = new ArrayList<>(Arrays.asList("Y","Z"));
        assertThat(solution, is(realSolution));
    }
    //</editor-fold>

    //<editor-fold desc="canPairGoThisRound Tests">
    //canPairGoThisRound Testen
    @Test
    public void canPairGoThisRound_NoneInRound_true(){
        /*Temporary gameboard with pair to place [A-C], round 2, game 2 => true
               | Game1 | Game2 | Game3 |
        --------------------------------
        Round1 | "A-B" |       |       |
        Round2 |       | "C-D" |       |
        Round3 |       |       | [A-C] |
         */
        String[][] tempGameBoard = {{"A-B", null, null},
                                    { null,"C-D", null},
                                    { null, null, null}};
        int round = 2;
        int game = 2;
        String pairToPlace = "A-C";

        Boolean solution = TaakPloegenIndeling.canPairGoThisRound(tempGameBoard,pairToPlace,round);
        assertThat(solution, is(true));
    }
    @Test
    public void canPairGoThisRound_OtherInRound_true(){
        /*Temporary gameboard with pair to place [D-E], round 0, game 2 => true
               | Game1 | Game2 | Game3 |
        --------------------------------
        Round1 | "A-B" |       | [D-E] |
        Round2 |       | "C-D" |       |
        Round3 |       |       |       |
         */
        String[][] tempGameBoard =
                        {{"A-B", null, null},
                        { null,"C-D", null},
                        { null, null, null}};
        int round = 0;
        int game = 2;
        String pairToPlace = "D-E";

        Boolean solution = TaakPloegenIndeling.canPairGoThisRound(tempGameBoard,pairToPlace,round);
        assertThat(solution, is(true));
    }
    @Test
    public void canPairGoThisRound_NoneInRound2_true(){
        /*Temporary gameboard with pair to place [D-E], round 2, game 0 => true
               | Game1 | Game2 | Game3 |
        --------------------------------
        Round1 | "A-B" |       |       |
        Round2 |       | "C-D" |       |
        Round3 | [D-E] |       |       |
         */
        String[][] tempGameBoard =
                        {{"A-B", null, null},
                        { null,"C-D", null},
                        { null, null, null}};
        int round = 2;
        int game = 0;
        String pairToPlace = "D-E";

        Boolean solution = TaakPloegenIndeling.canPairGoThisRound(tempGameBoard,pairToPlace,round);
        assertThat(solution, is(true));
    }
    @Test
    public void canPairGoThisRound_OtherInRound1_true(){
        /*Temporary gameboard with pair to place [E-F], round 0, game 1 => true
               | Game1 | Game2 | Game3 |
        --------------------------------
        Round1 | "A-B" |       |       |
        Round2 |       | "C-D" |       |
        Round3 | [D-E] |       |       |
         */
        String[][] tempGameBoard =
                        {{"A-B", null, null},
                        { null,"C-D", null},
                        { null, null, null}};
        int round = 0;
        int game = 1;
        String pairToPlace = "E-F";

        Boolean solution = TaakPloegenIndeling.canPairGoThisRound(tempGameBoard,pairToPlace,round);
        assertThat(solution, is(true));
    }
    @Test
    public void canPairGoThisRound_SameInRound3_false(){
        /*Temporary gameboard with pair to place [A-C], round 0, game 2 => false
               | Game1 | Game2 | Game3 |
        --------------------------------
        Round1 | "A-B" |       | [A-C] |
        Round2 |       | "C-D" |       |
        Round3 |       |       |       |
         */
        String[][] tempGameBoard = {{"A-B", null, null},
                                    { null,"C-D", null},
                                    { null, null, null}};
        int round = 0;
        int game = 2;
        String pairToPlace = "A-C";

        Boolean solution = TaakPloegenIndeling.canPairGoThisRound(tempGameBoard,pairToPlace,round);
        assertThat(solution, is(false));
    }
    @Test
    public void canPairGoThisRound_SameInRound2_false(){
        /*Temporary gameboard with pair to place [A-C], round 1, game 2 => false
               | Game1 | Game2 | Game3 |
        --------------------------------
        Round1 | "A-B" |       |       |
        Round2 |       | "C-D" | [A-C] |
        Round3 |       |       |       |
         */
        String[][] tempGameBoard = {{"A-B", null, null},
                                    { null,"C-D", null},
                                    { null, null, null}};
        int round = 1;
        int game = 2;
        String pairToPlace = "A-C";

        Boolean solution = TaakPloegenIndeling.canPairGoThisRound(tempGameBoard,pairToPlace,round);
        assertThat(solution, is(false));
    }
    @Test
    public void canPairGoThisRound_NoneInRound1_true(){
        /*Temporary gameboard with pair to place [A-C], round 2, game 0 => true
               | Game1 | Game2 | Game3 |
        --------------------------------
        Round1 | "A-B" |       |       |
        Round2 |       | "C-D" |       |
        Round3 | [A-C] |       |       |
         */
        String[][] tempGameBoard = {{"A-B", null, null},
                                    { null,"C-D", null},
                                    { null, null, null}};
        int round = 2;
        int game = 0;
        String pairToPlace = "A-C";

        Boolean solution = TaakPloegenIndeling.canPairGoThisRound(tempGameBoard,pairToPlace,round);
        assertThat(solution, is(true));
    }
    @Test
    public void canPairGoThisRound_SameInRound1_false(){
        /*Temporary gameboard with pair to place [A-C], round 1, game 0 => false
               | Game1 | Game2 | Game3 |
        --------------------------------
        Round1 | "A-B" |       |       |
        Round2 | [A-C] | "C-D" |       |
        Round3 |       |       |       |
         */
        String[][] tempGameBoard =
                {{"A-B", null, null},
                { null,"C-D", null},
                { null, null, null}};
        int round = 1;
        int game = 0;
        String pairToPlace = "A-C";

        Boolean solution = TaakPloegenIndeling.canPairGoThisRound(tempGameBoard,pairToPlace,round);
        assertThat(solution, is(false));
    }
    //</editor-fold>

    //<editor-fold desc="canPairGoThisGame Tests">
    //canPairGoThisGame Testen
    @Test
    public void canPairGoThisGame_NoneInGame_true(){
        /*Temporary gameboard with pair to place [A-C], round 2, game 2 => true
               | Game1 | Game2 | Game3 |
        --------------------------------
        Round1 | "A-B" |       |       |
        Round2 |       | "C-D" |       |
        Round3 |       |       | [A-C] |
         */
        String[][] tempGameBoard = {{"A-B", null, null},
                { null,"C-D", null},
                { null, null, null}};
        int round = 2;
        int game = 2;
        String pairToPlace = "A-C";

        Boolean solution = TaakPloegenIndeling.canPairGoThisGame(tempGameBoard,pairToPlace,game);
        assertThat(solution, is(true));
    }
    @Test
    public void canPairGoThisGame_NoneInGame3_true(){
        /*Temporary gameboard with pair to place [D-E], round 0, game 2 => true
               | Game1 | Game2 | Game3 |
        --------------------------------
        Round1 | "A-B" |       | [D-E] |
        Round2 |       | "C-D" |       |
        Round3 |       |       |       |
         */
        String[][] tempGameBoard =
                {{"A-B", null, null},
                        { null,"C-D", null},
                        { null, null, null}};
        int round = 0;
        int game = 2;
        String pairToPlace = "D-E";

        Boolean solution = TaakPloegenIndeling.canPairGoThisGame(tempGameBoard,pairToPlace,game);
        assertThat(solution, is(true));
    }
    @Test
    public void canPairGoThisGame_OtherInGame1_true(){
        /*Temporary gameboard with pair to place [D-E], round 2, game 0 => true
               | Game1 | Game2 | Game3 |
        --------------------------------
        Round1 | "A-B" |       |       |
        Round2 |       | "C-D" |       |
        Round3 | [D-E] |       |       |
         */
        String[][] tempGameBoard =
                {{"A-B", null, null},
                        { null,"C-D", null},
                        { null, null, null}};
        int round = 2;
        int game = 0;
        String pairToPlace = "D-E";

        Boolean solution = TaakPloegenIndeling.canPairGoThisGame(tempGameBoard,pairToPlace,game);
        assertThat(solution, is(true));
    }
    @Test
    public void canPairGoThisGame_OtherInGame2_true(){
        /*Temporary gameboard with pair to place [E-F], round 0, game 1 => true
               | Game1 | Game2 | Game3 |
        --------------------------------
        Round1 | "A-B" |       |       |
        Round2 |       | "C-D" |       |
        Round3 | [D-E] |       |       |
         */
        String[][] tempGameBoard =
                {{"A-B", null, null},
                        { null,"C-D", null},
                        { null, null, null}};
        int round = 0;
        int game = 1;
        String pairToPlace = "E-F";

        Boolean solution = TaakPloegenIndeling.canPairGoThisGame(tempGameBoard,pairToPlace,game);
        assertThat(solution, is(true));
    }
    @Test
    public void canPairGoThisGame_NoneInGame1_true(){
        /*Temporary gameboard with pair to place [A-C], round 0, game 2 => true
               | Game1 | Game2 | Game3 |
        --------------------------------
        Round1 | "A-B" |       | [A-C] |
        Round2 |       | "C-D" |       |
        Round3 |       |       |       |
         */
        String[][] tempGameBoard = {{"A-B", null, null},
                { null,"C-D", null},
                { null, null, null}};
        int round = 0;
        int game = 2;
        String pairToPlace = "A-C";

        Boolean solution = TaakPloegenIndeling.canPairGoThisGame(tempGameBoard,pairToPlace,game);
        assertThat(solution, is(true));
    }
    @Test
    public void canPairGoThisGame_NoneInGame2_true(){
        /*Temporary gameboard with pair to place [A-C], round 1, game 2 => true
               | Game1 | Game2 | Game3 |
        --------------------------------
        Round1 | "A-B" |       |       |
        Round2 |       | "C-D" | [A-C] |
        Round3 |       |       |       |
         */
        String[][] tempGameBoard = {{"A-B", null, null},
                { null,"C-D", null},
                { null, null, null}};
        int round = 1;
        int game = 2;
        String pairToPlace = "A-C";

        Boolean solution = TaakPloegenIndeling.canPairGoThisGame(tempGameBoard,pairToPlace,game);
        assertThat(solution, is(true));
    }
    @Test
    public void canPairGoThisGame_SameInGame1_false(){
        /*Temporary gameboard with pair to place [A-C], round 2, game 0 => false
               | Game1 | Game2 | Game3 |
        --------------------------------
        Round1 | "A-B" |       |       |
        Round2 |       | "C-D" |       |
        Round3 | [A-C] |       |       |
         */
        String[][] tempGameBoard = {{"A-B", null, null},
                { null,"C-D", null},
                { null, null, null}};
        int round = 2;
        int game = 0;
        String pairToPlace = "A-C";

        Boolean solution = TaakPloegenIndeling.canPairGoThisGame(tempGameBoard,pairToPlace,game);
        assertThat(solution, is(false));
    }
    @Test
    public void canPairGoThisGame_SameInGame2_false(){
        /*Temporary gameboard with pair to place [A-C], round 1, game 0 => false
               | Game1 | Game2 | Game3 |
        --------------------------------
        Round1 | "A-B" |       |       |
        Round2 | [A-C] | "C-D" |       |
        Round3 |       |       |       |
         */
        String[][] tempGameBoard =
                {{"A-B", null, null},
                        { null,"C-D", null},
                        { null, null, null}};
        int round = 1;
        int game = 0;
        String pairToPlace = "A-C";

        Boolean solution = TaakPloegenIndeling.canPairGoThisGame(tempGameBoard,pairToPlace,game);
        assertThat(solution, is(false));
    }
    //</editor-fold>

    //<editor-fold desc="allTeamsInEveryGame Tests">
    //All teams in every game tests
    @Test
    public void allTeamsInEveryGame_2teams1games1doubles1rounds_true(){
        /*gameBoard 2 teams 1 doubles => true
               | Game1 |
        ----------------
        Round1 | "A-B" |
         */
        String[][] board = {{"A-B"}};
        int numberOfTeams = 2;
        boolean solution = TaakPloegenIndeling.allTeamsInEveryGame(numberOfTeams,board);
        assertThat(solution, is(true));
    }
    @Test
    public void allTeamsInEveryGame_2teams2games2doubles2rounds_true(){
        /*gameBoard 2 teams 2 doubles => true
               | Game1 | Game2 |
        ------------------------
        Round1 | "A-B" |       |
        Round2 |       | "A-B" |
         */
        String[][] board = {{"A-B",null},
                            {null,"A-B"}};
        int numberOfTeams = 2;
        boolean solution = TaakPloegenIndeling.allTeamsInEveryGame(numberOfTeams,board);
        assertThat(solution, is(true));
    }
    @Test
    public void allTeamsInEveryGame_2teams2games1doubles2rounds_false(){
        /*gameBoard 2 teams 2 doubles => true
               | Game1 | Game2 |
        ------------------------
        Round1 | "A-B" |       |
        Round2 |       |       |
         */
        String[][] board = {{"A-B",null},
                             {null,null}};
        int numberOfTeams = 2;
        boolean solution = TaakPloegenIndeling.allTeamsInEveryGame(numberOfTeams,board);
        assertThat(solution, is(false));
    }
    @Test
    public void allTeamsInEveryGame_2teams1games1doubles2rounds_true(){
        /*gameBoard 2 teams 1 doubles => true
               | Game1 |
        ----------------
        Round1 | "A-B" |
        Round2 |       |
         */
        String[][] board = {{"A-B"},
                            {null}};
        int numberOfTeams = 2;
        boolean solution = TaakPloegenIndeling.allTeamsInEveryGame(numberOfTeams,board);
        assertThat(solution, is(true));
    }
    @Test
    public void allTeamsInEveryGame_4teams3games1doubles6rounds_true(){
        /*gameBoard 4 teams 1 doubles => true
                 | Game 1 |  Game 2 |  Game 3 |
        ---------------------------------------
        Round 1  |  A-B   |         |         |
        Round 2  |        |   A-C   |         |
        Ronde 3  |        |         |   A-D   |
        Ronde 4  |        |         |   B-C   |
        Ronde 5  |        |   B-D   |         |
        Ronde 6  |  C-D   |         |         |
         */
        String[][] board = {{"A-B",null,null},
                            {null,"A-C",null},
                            {null,null,"A-D"},
                            {null,null,"B-C"},
                            {null,"B-D",null},
                            {"C-D",null,null}};
        int numberOfTeams = 4;
        boolean solution = TaakPloegenIndeling.allTeamsInEveryGame(numberOfTeams,board);
        assertThat(solution, is(true));
    }
    @Test
    public void allTeamsInEveryGame_4teams3games1doubles6rounds_false(){
        /*gameBoard 4 teams 1 doubles => false
                 | Game 1 |  Game 2 |  Game 3 |
        ---------------------------------------
        Round 1  |  A-B   |         |         |
        Round 2  |        |   A-C   |         |
        Ronde 3  |        |         |   A-D   |
        Ronde 4  |        |         |   B-C   |
        Ronde 5  |        |   B-D   |         |
        Ronde 6  |        |         |         |
         */
        String[][] board = {{"A-B",null,null},
                            {null,"A-C",null},
                            {null,null,"A-D"},
                            {null,null,"B-C"},
                            {null,"B-D",null},
                            {null,null,null}};
        int numberOfTeams = 4;
        boolean solution = TaakPloegenIndeling.allTeamsInEveryGame(numberOfTeams,board);
        assertThat(solution, is(false));
    }
    //</editor-fold>
}
