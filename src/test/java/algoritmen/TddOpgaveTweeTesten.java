package algoritmen;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TddOpgaveTweeTesten {
    @Test
    public void makeShoeBox_36n37n35n36n38_15x146(){
        // 1. ARRANGE & ACT
        List<Integer> shoesList = Arrays.asList(36,37,35,36,38);
        ArrayList<Integer> shoes = new ArrayList<>(shoesList);
        String toCheckResult = TddOpgaveTwee.makeShoeBox(shoes);
        // 2. ASSERT
        String checkResult = "15x146";
        assertThat(toCheckResult,is(checkResult));
    }
    @Test
    public void makeShoeBox_38n38_10x76(){
        // 1. ARRANGE & ACT
        List<Integer> shoesList = Arrays.asList(38,38);
        ArrayList<Integer> shoes = new ArrayList<>(shoesList);
        String toCheckResult = TddOpgaveTwee.makeShoeBox(shoes);
        // 2. ASSERT
        String checkResult = "10x76";
        assertThat(toCheckResult,is(checkResult));
    }
    @Test
    public void makeShoeBox_36n36_15x36(){
        // 1. ARRANGE & ACT
        List<Integer> shoesList = Arrays.asList(36,36);
        ArrayList<Integer> shoes = new ArrayList<>(shoesList);
        String toCheckResult = TddOpgaveTwee.makeShoeBox(shoes);
        // 2. ASSERT
        String checkResult = "15x36";
        assertThat(toCheckResult,is(checkResult));
    }
}
