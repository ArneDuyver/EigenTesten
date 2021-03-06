package algoritmen;

import java.util.*;

public class TddOpgaveTwee {
    //in root folder: javac -d build src/main/java/algoritmen/TddOpgaveTwee.java
    //in root folder: cat src/main/java/algoritmen/voorbeeld.invoer | java -cp build/ algoritmen.TddOpgaveTwee
    public static void main(String[] args) {
        //<editor-fold desc="Code">
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbs = new ArrayList<>();
        //
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            String newS = s.replaceAll("\\D+",""); //replaces everything thats not a digit
            int numb = Integer.parseInt(newS);
            numbs.add(numb);
        }
        String[] closets = new String[numbs.get(0)];
        int closetIndex = 0;
        int index = 1;
        while (index < numbs.size()){
            int numberOfShoes = numbs.get(index);
            int newIndex = index+numberOfShoes+1;
            ArrayList<Integer> shoesList = new ArrayList<>();
            int index2 = index + 1;
            while(index2<newIndex){
                shoesList.add(numbs.get(index2));
                index2 ++;
            }
            closets[closetIndex] = makeShoeBox(shoesList);
            closetIndex++;
            index = newIndex;
        }

        System.out.println("output");
        for (int i = 0; i < closets.length;i++){
            System.out.println((i+1) + " " + closets[i]);
        }
        //</editor-fold>
    }

    public static String makeShoeBox(ArrayList<Integer> shoes){
        /*
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Foo");
        map.put(2, "Bar");
        System.out.println(map.get(1));
        */
        int hoogte = 0;
        int breedte = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (int size : shoes) {
            if (map.containsKey(size)) {
                int oldNumber = map.get(size);
                map.replace(size, oldNumber + 1);
            } else {
                map.put(size, 1);
            }
        }
        //Bereken hoogte & hoogte
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            int shoeSize = (int) pair.getKey();
            int total = (int) pair.getValue();
            //Bereken lengte
            if (shoeSize < 38){
                breedte = breedte+shoeSize;
            } else {
                breedte = breedte+(shoeSize*total);
            }
            //Bereken hoogte
            if (shoeSize < 38 && total > 1){
                hoogte = 15;
            } else if (hoogte != 15){
                hoogte = 10;
            }
            it.remove(); // avoids a ConcurrentModificationException
        }

        return (hoogte+"x"+breedte);
    }
}
