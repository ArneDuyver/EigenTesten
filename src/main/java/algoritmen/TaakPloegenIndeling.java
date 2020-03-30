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
        //INPUT VALIDATIE
        if (ploegen%2!=0 || ploegen < 2 || spelletjes<1 || dubbels < 1 || rondes < 1 || ploegen > 26){
            throw new algoritmen.InvalidInputException();
        }
        //<editor-fold desc="Maak de teamparen en de doubles">
        ArrayList<String> pairs = getPairs(ploegen);
        ArrayList<ArrayList<String>> doubles = new ArrayList<ArrayList<String>>();
        for (int i = 1; i < dubbels-1; i++){
            doubles.add(pairs);
        }
        //</editor-fold>
        //TODO: backtracking methode oproepen && return optional.empty als er geen oplossing is
        String[][] oplossing = new String[rondes][spelletjes];

        return Optional.ofNullable(oplossing);
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

    public static ArrayList<String> getPairs(int numberOfTeams){
        if (numberOfTeams<2 || numberOfTeams > 26){
            throw new InvalidInputException();
        }
        String[] letters = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        ArrayList<String> teams = new ArrayList<>();
        Collections.addAll(teams, Arrays.copyOfRange(letters, 0, numberOfTeams));

        ArrayList<String> pairs = new ArrayList<>();
        for (int i = 0; i<teams.size();i++){
            for (int j = i+1;j<teams.size();j++){
                String pair = teams.get(i)+"-"+teams.get(j);
                pairs.add(pair);
            }
        }
        return pairs;
    }
    public static ArrayList<String> getTeamsFromPair(String pair){
        ArrayList<String> teams = new ArrayList<>();
        for (int i = 0; i<pair.length();i++){
            char c = pair.charAt(i);
            String s =  ""+c;
            if (!s.equals("-")){
                teams.add(s);
            }
        }
        return teams;
    }
    public static boolean canPairGoThisRoundAndGame(String[][] gameBoard, String pair, int round, int game){
        ArrayList<String> teamsAlreadyHere = new ArrayList<>();
        //get all teams this round
        for (int i = 0; i < gameBoard[round].length; i++){
            if (gameBoard[round][i] != null){
                teamsAlreadyHere.addAll(getTeamsFromPair(gameBoard[round][i]));
            }
        }
        //get all teams this game
        for (int i = 0; i < gameBoard.length; i++){
            if (gameBoard[i][game] != null){
                teamsAlreadyHere.addAll(getTeamsFromPair(gameBoard[i][game]));
            }
        }
        //get teams of pair
        ArrayList<String> teamsOfPair = getTeamsFromPair(pair);
        //if a team of a pair is in this game or round than return false else true
        for (String team : teamsOfPair){
            if (teamsAlreadyHere.contains(team)){
                return false;
            }
        }
        return true;
    }

}
