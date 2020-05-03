package algoritmen;

import java.util.ArrayList;
import java.util.Arrays;

public class VerdeelHeers {
    public static void main(String[] args) {
        System.out.println("VOORBEELDEN");
        ArrayList<int[]> lists = new ArrayList<>();
        int[] list1 = {1,2};
        int[] list2 = {2,1};
        int[] list3 = {3,2,1};
        int[] list4 = {2,3,1};
        int[] list5 = {1,2,7,6,3,4};
        int[] list6 = {1,4,3,5,8,6,7};
        int[] list7 = {10,9,8,7,6,5,4,3,2,1};
        int[] list8 = {-1,0};
        int[] list9 = {0,-1};
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        lists.add(list4);
        lists.add(list5);
        lists.add(list6);
        lists.add(list7);
        lists.add(list8);
        lists.add(list9);
        for (int[] l : lists){
            for (int i : l){
                if  (i == l[l.length-1]){
                    System.out.print(i+" ");
                } else {
                    System.out.print(i + ", ");
                }
            }
            System.out.println(": Aantal fouten is "+getVolgordeFouten(l));
        }
        System.out.println("THE END");
    }

    /**
     * Calculate the number of order faults in a list of numbers
     * @param array The list from which the number of faults must be calculated
     * @return The number of faults
     */
    public static int getVolgordeFouten(int[] array) {
        int faults = 0;
        //If the list only has 1 element: it has 0 faults
        if (array.length == 1) return 0;
        //If the list only has 2 elements: it has 0 faults or 1 fault
        else if (array.length == 2){
            if (array[0]<=array[1]) return 0;
            else return 1;
        }
        //Split the list in half
        int midden = array.length/2;

        //Get the faults of the 2 halves
        int[] left = Arrays.copyOfRange(array, 0, midden);
        int[] right = Arrays.copyOfRange(array, midden, (array.length));
        faults = faults + getVolgordeFouten(left) + getVolgordeFouten(right);

        //Sort the 2 halves
        int[] leftSorted = Arrays.copyOfRange(left, 0, left.length);
        Arrays.sort(leftSorted);
        int[] rightSorted = Arrays.copyOfRange(right, 0, right.length);
        Arrays.sort(rightSorted);
        //Calculate the extra faults from the left halve to the right halve
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

        //Calculate the total faults
        faults = faults + extra;

        return faults;
    }
}
