package algoritmen;

import java.util.Arrays;

public class VerdeelHeers {
    public static void main(String[] args) {

    }
    public static int getVolgordeFouten(int[] array) {
        int faults = 0;
        if (array.length == 1) return 0;
        else if (array.length == 2){
            if (array[0]<=array[1]) return 0;
            else return 1;
        }
        int midden = array.length/2;

        int[] left = Arrays.copyOfRange(array, 0, midden);
        int[] right = Arrays.copyOfRange(array, midden, (array.length));

        faults = faults + getVolgordeFouten(left) + getVolgordeFouten(right);

        int[] leftSorted = Arrays.copyOfRange(left, 0, left.length);
        Arrays.sort(leftSorted);
        int[] rightSorted = Arrays.copyOfRange(right, 0, right.length);
        Arrays.sort(rightSorted);

        int extra = 0;
        int l = 0;
        int r = 0;
        while (l < leftSorted.length && r < rightSorted.length){
            if (leftSorted[l] <= rightSorted[r]){
                l++;
            }
            else if(leftSorted[l] > rightSorted[r]){
                extra = extra + leftSorted.length - l;
                r++;
            }
        }

        faults = faults + extra;

        return faults;
    }

    //TODO: delete
    public static String arrayToString(int[] array){
        String s = "";
        for (int i : array){
            s = s+i+",";
        }
        return s;
    }
}
