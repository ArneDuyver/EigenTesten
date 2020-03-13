package Algoritmen;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FaculteitTests {
    Faculteit faculteit;

    @Before
    public void setUp(){
        this.faculteit = new Faculteit();
    }

    @Test
    public void faculteit_0_1(){
        // 1. ARRANGE
        int n = 0;
        // 2. ACT
        int result = faculteit.faculteit(n);
        // 3. ASSERT
        assertThat(result,is(1));
    }
    @Test
    public void faculteit_1_1(){
        // 1. ARRANGE
        int n = 1;
        // 2. ACT
        int result = faculteit.faculteit(n);
        // 3. ASSERT
        assertThat(result,is(1));
    }
    @Test
    public void faculteit_3_6(){
        // 1. ARRANGE
        int n = 3;
        // 2. ACT
        int result = faculteit.faculteit(n);
        // 3. ASSERT
        assertThat(result,is(6));
    }
    @Test
    public void faculteit_10_3628800(){
        // 1. ARRANGE
        int n = 10;
        // 2. ACT
        int result = faculteit.faculteit(n);
        // 3. ASSERT
        assertThat(result,is(3628800));
    }
}
