package algoritmen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class TaakPloegenIndeling {
    private String[] mogelijkePloegen;
    private String[] ploegenArray;


    public static void main(String[] args){
        System.out.println("Hello\nWorld");
        toonVerdeling(6,10,1,10);
    }

    /**
     * Volgende methode maakt een ploegenindeling aan de hand van de gegeven parameters
     * @param ploegen Het aantal ploegen, moet even zijn
     * @param spelletjes Het totaal aantal spelletjes
     * @param dubbels Het maximum aantal keer dat 2 ploegen tegen elkaar mogen spelen
     * @param rondes Het aantal rondes
     * @return Een 2D arraylist van strings waarbij de eerste array de rondes aangeeft en de tweede array het spel. De elementen zijn de teams die tegen elkaar spelen
     */
    public static Optional<String[][]> spelverdeling(int ploegen, int spelletjes, int dubbels, int rondes){
        //TODO: code schrijven: return een Optional.empty() als er geen oplossing gevonden kan worden
        //INPUT VALIDATIE
        if (ploegen%2!=0 || ploegen < 2 || spelletjes<1 || dubbels < 1 || rondes < 1 || ploegen > 26){
            throw new algoritmen.InvalidSpelverdelingInputException();
        }
        //Maak de teams, de matchups en de doubles
        ArrayList<String> teams = getTeams(ploegen);
        ArrayList<String> pairs = getPairs(teams);
        ArrayList<ArrayList<String>> doubles = new ArrayList<ArrayList<String>>();
        for (int i = 1; i < dubbels; i++){
            doubles.add(pairs);
        }

        //
        String[][] oplossing = new String[rondes][spelletjes];


        //TEST
//        for (int i = 0; i<oplossing.length;i++){
//            for (int j = 0; j<oplossing[i].length;j++){
//                oplossing[i][j] = i+"-"+j;
//            }
//        }
        oplossing[0][0] = "A-B";
        Optional<String[][]> deOplossing = Optional.ofNullable(oplossing);
        return deOplossing;
    }

    /**
     * Volgende methode print de ploegenindeling in de console
     * @param ploegen Het aantal ploegen, moet even zijn
     * @param spelletjes Het totaal aantal spelletjes
     * @param dubbels Het maximum aantal keer dat 2 ploegen tegen elkaar mogen spelen
     * @param rondes Het aantal rondes
     */
    public static void toonVerdeling(int ploegen, int spelletjes, int dubbels, int rondes){
        Optional<String[][]> oplossing = spelverdeling(ploegen,spelletjes,dubbels,rondes);
        if (oplossing.isEmpty()){
            System.out.println("Er werd geen oplossing gevonden");
        } else {
            //TODO: code schrijven die de 2D array mooi afdrukt: enkel via  " System.out.println(""); "
            String[][] deOplossing = oplossing.get();
            System.out.print("         | ");
            for (int games = 0; games < deOplossing[0].length;games++) {
                if (games+1<10){
                    System.out.print("Game " + (games + 1) + " |  ");
                } else{
                    System.out.print("Game " + (games + 1) + "|  ");
                }
            }
            System.out.println("");
            System.out.print("_________ ________ ");
            for (int games = 0; games < deOplossing[0].length-1;games++) {
                System.out.print("_________ ");
            }
            System.out.println("");
            for (int i = 0; i < deOplossing.length; i++){
                if (i+1<10){
                    System.out.print("Ronde "+(i+1)+"  |  ");
                }else{
                    System.out.print("Ronde "+(i+1)+" |  ");
                }
                for (int j = 0; j < deOplossing[i].length; j++){
                    if (deOplossing[i][j] != null){
                        System.out.print(deOplossing[i][j]+"   |   ");
                    }else{
                        System.out.print("   "+"   |   ");
                    }
                }
                System.out.println("");
            }
        }
    }

    /**
     * Return an array of all letters
     * @return A string with all 26 letters of the alphabet
     */
    public static String[] getLetters(){
        return new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    }
    public static ArrayList<String> getTeams(int numberOfTeams){
        String[] letters = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        ArrayList<String> teams = new ArrayList<>();
        Collections.addAll(teams, Arrays.copyOfRange(letters, 0, numberOfTeams));
        return teams;
    }
    public static ArrayList<String> getPairs(ArrayList<String> teams){
        ArrayList<String> pairs = new ArrayList<>();
        for (int i = 0; i<teams.size();i++){
            for (int j = i+1;j<teams.size();j++){
                String pair = teams.get(i)+"-"+teams.get(j);
                pairs.add(pair);
            }
        }
        return pairs;
    }
}
