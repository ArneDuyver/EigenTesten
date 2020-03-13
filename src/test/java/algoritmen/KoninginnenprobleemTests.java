package algoritmen;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class KoninginnenprobleemTests {
    KoninginnenProbleem probleem;

    @Before
    public void setUp(){
        KoninginnenProbleem probleem = new KoninginnenProbleem();
    }

    //isVeilig testen
    @Test
    public void isVeilig_rij5_136_False(){
        // 1. ARRANGE
        int rij = 5;
        ArrayList<Integer> vorigeQ = new ArrayList<>();
        vorigeQ.add(1);
        vorigeQ.add(3);
        vorigeQ.add(6);
        // 2. ACT
        boolean result = probleem.isVeilig(rij,vorigeQ);
        // 3. ASSERT
        assertThat(result,is(false));
    }
    @Test
    public void isVeilig_rij4_136_False(){
        // 1. ARRANGE
        int rij = 4;
        ArrayList<Integer> vorigeQ = new ArrayList<>();
        vorigeQ.add(1);
        vorigeQ.add(3);
        vorigeQ.add(6);
        // 2. ACT
        boolean result = probleem.isVeilig(rij,vorigeQ);
        // 3. ASSERT
        assertThat(result,is(false));
    }
    @Test
    public void isVeilig_rij2_136_True(){
        // 1. ARRANGE
        int rij = 2;
        ArrayList<Integer> vorigeQ = new ArrayList<>();
        vorigeQ.add(1);
        vorigeQ.add(3);
        vorigeQ.add(6);
        // 2. ACT
        boolean result = probleem.isVeilig(rij,vorigeQ);
        // 3. ASSERT
        assertThat(result,is(true));
    }

    //nQueens testen
    @Test
    public void nQueens_1_1(){
        // 1. ARRANGE
        int n = 1;
        // 2. ACT
        ArrayList<Integer> result = probleem.nQueens(n);
        // 3. ASSERT
        ArrayList<Integer> oplossing = new ArrayList<>();
        oplossing.add(1);
        assertThat(result,is(oplossing));
    }
    @Test
    public void nQueens_2_Leeg(){
        // 1. ARRANGE
        int n = 2;
        // 2. ACT
        ArrayList<Integer> result = probleem.nQueens(n);
        // 3. ASSERT
        ArrayList<Integer> oplossing = new ArrayList<>();
        assertThat(result,is(oplossing));
    }
    @Test
    public void nQueens_3_Leeg(){
        // 1. ARRANGE
        int n = 3;
        // 2. ACT
        ArrayList<Integer> result = probleem.nQueens(n);
        // 3. ASSERT
        ArrayList<Integer> oplossing = new ArrayList<>();
        assertThat(result,is(oplossing));
    }

}
