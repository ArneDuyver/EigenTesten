package algoritmen;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class VerdeelHeersTests {

    @Test
    public void getVolgordeFouten_12_0(){
        int[] array = {1,2};
        int solution = VerdeelHeers.getVolgordeFouten(array);
        assertThat(solution,is(0));
    }

    @Test
    public void getVolgordeFouten_21_1(){
        int[] array = {2,1};
        int solution = VerdeelHeers.getVolgordeFouten(array);
        assertThat(solution,is(1));
    }

    @Test
    public void getVolgordeFouten_321_3(){
        int[] array = {3,2,1};
        int solution = VerdeelHeers.getVolgordeFouten(array);
        assertThat(solution,is(3));
    }

    @Test
    public void getVolgordeFouten_231_2(){
        int[] array = {2,3,1};
        int solution = VerdeelHeers.getVolgordeFouten(array);
        assertThat(solution,is(2));
    }

    @Test
    public void getVolgordeFouten_127634_5(){
        int[] array = {1,2,7,6,3,4};
        int solution = VerdeelHeers.getVolgordeFouten(array);
        assertThat(solution,is(5));
    }

    @Test
    public void getVolgordeFouten_1435867_3(){
        int[] array = {1,4,3,5,8,6,7};
        int solution = VerdeelHeers.getVolgordeFouten(array);
        assertThat(solution,is(3));
    }

    @Test
    public void getVolgordeFouten_10987654321_45(){
        int[] array = {10,9,8,7,6,5,4,3,2,1};
        int solution = VerdeelHeers.getVolgordeFouten(array);
        assertThat(solution,is(45));
    }

    @Test
    public void getVolgordeFouten_Min10_0(){
        int[] array = {-1,0};
        int solution = VerdeelHeers.getVolgordeFouten(array);
        assertThat(solution,is(0));
    }

    @Test
    public void getVolgordeFouten_0Min1_1(){
        int[] array = {0,-1};
        int solution = VerdeelHeers.getVolgordeFouten(array);
        assertThat(solution,is(1));
    }
}
