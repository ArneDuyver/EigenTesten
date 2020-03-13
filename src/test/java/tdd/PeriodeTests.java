package tdd;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PeriodeTests {
    private Periode periode;

    @Before
    public void setUp(){
        //this.periode = new Periode();
    }

    @After
    public void cleanUp(){
        //runt na elke test
        //bv: this.periode = null;
    }

    @Test
    public void isInPeriode_datumLigtInPeriode_true(){ //public void altijd voor testen en naam is het scenario met de oplossing
        // 1. ARRANGE
        Periode periode = new Periode(
                new Date(2020,03,01),
                new Date(2020,03,20)
        );

        // 2. ACT
        boolean result = periode.isInPeriode(new Date(2020,03,02));

        // 3. ASSERT
        //Assert.assertEquals(true,result);
        //BETER
        assertThat(result,is(true));
    }

    @Test
    public void isInPeriode_datumLigtVoorStart_false(){
        // 1. ARRANGE
        Periode periode = new Periode(
                new Date(2020,03,01),
                new Date(2020,03,20)
        );

        // 2. ACT
        boolean result = periode.isInPeriode(new Date(2020,02,10));

        // 3. ASSERT
        //Assert.assertEquals(false,result);
        //BETER
        assertThat(result,is(false));
    }

    @Test(expected = InvalidPeriodInputException.class)
    //eigen gemaakte exception = nieuwe javaclass met de naam van je exception die erft van runtimeException
    public void isInPeriode_invoerDatumNull_throwsException(){
        // 1. ARRANGE
        Periode periode = new Periode(
                new Date(2020,03,01),
                new Date(2020,03,20)
        );

        // 2. ACT
        periode.isInPeriode(null);

        // 3. ASSERT
        //Zit in de @Test blablabla
    }
}
