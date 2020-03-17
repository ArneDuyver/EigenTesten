package algoritmen;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TaakPloegenIndelingTests {

    //INVALID INPUT TESTEN
    @Test(expected = InvalidSpelverdelingInputException.class)
    public void spelverdeling_onevenPloegen_throwsException(){
        // 1. ARRANGE
        Optional<String[][]> oplossing = TaakPloegenIndeling.spelverdeling(3,3,3,3);
    }
    @Test(expected = InvalidSpelverdelingInputException.class)
    public void spelverdeling_0Ploegen_throwsException(){
        // 1. ARRANGE
        Optional<String[][]> oplossing = TaakPloegenIndeling.spelverdeling(0,3,3,3);
    }
    @Test(expected = InvalidSpelverdelingInputException.class)
    public void spelverdeling_0Spelletjes_throwsException(){
        // 1. ARRANGE
        Optional<String[][]> oplossing = TaakPloegenIndeling.spelverdeling(4,0,3,3);
    }
    @Test(expected = InvalidSpelverdelingInputException.class)
    public void spelverdeling_0dubbels_throwsException(){
        // 1. ARRANGE
        Optional<String[][]> oplossing = TaakPloegenIndeling.spelverdeling(4,3,0,3);
    }
    @Test(expected = InvalidSpelverdelingInputException.class)
    public void spelverdeling_neg1rondes_throwsException(){
        // 1. ARRANGE
        Optional<String[][]> oplossing = TaakPloegenIndeling.spelverdeling(4,3,3,-1);
    }

    @Test
    //TODO: testnaam aanpassen
    public void spelverdeling_2en1en1en1_11AB_() {
        // 1. ARRANGE
        // 2. ACT
        Optional<String[][]> oplossing = TaakPloegenIndeling.spelverdeling(2,1,1,1);
        String[][] deOplossing = oplossing.get();
        // 3. ASSERT
        String[][] echteOplossing = new String[1][1];
        echteOplossing[0][0]="A-B";
        assertThat(deOplossing, is(echteOplossing));
    }
}
