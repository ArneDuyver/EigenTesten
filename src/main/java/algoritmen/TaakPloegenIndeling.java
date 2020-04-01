package algoritmen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class TaakPloegenIndeling {


    public static void main(String[] args) {
        //System.out.println("Hello\nWorld");
        toonVerdeling(4,4,2,6);

    }


    public static Optional<String[][]> spelverdeling(int ploegen, int spelletjes, int dubbels, int rondes) {
        //Input validation
        if (ploegen % 2 != 0 || ploegen < 2 || spelletjes < 1 || dubbels < 1 || rondes < 1 || ploegen > 26) {
            //TODO feedback in de terminal printen
            throw new algoritmen.InvalidInputException();
        }
        //Make teampairs, doubles, empty pairsUsed and empty doublesUsed ArrayLists
        ArrayList<String> pairs = getPairs(ploegen);
        ArrayList<String> doubles = new ArrayList<>();
        for (int i = 1; i <= dubbels - 1; i++) {
            for (int j = 0; j < pairs.size();j++){
                doubles.add(pairs.get(j));
            }
        }
        ArrayList<String> pairsUsed = new ArrayList<>();
        ArrayList<String> doublesUsed = new ArrayList<>();

        //Make the starting (empty) gameboard
        String[][] tempBoard = new String[rondes][spelletjes];
        return solveSpelverdeling(ploegen,pairs, pairsUsed, doubles, doublesUsed, spelletjes, 0, new ArrayList<>(), rondes, 0, new ArrayList<>(), tempBoard);
    }

    private static Optional<String[][]> solveSpelverdeling(int numberOfTeams,ArrayList<String> pairs, ArrayList<String> pairsUsed,
                                                           ArrayList<String> doubles, ArrayList<String> doublesUsed,
                                                           int games, int currentGame, ArrayList<Integer> prevGame, int rounds, int currentRound, ArrayList<Integer> prevRound,
                                                           String[][] tempBoard) {
        //TODO: delete these debugging prints
        System.out.println(" ");
        System.out.println("*****VOLGENDE ITERATIE*****");
        //System.out.println("#Teams: " + numberOfTeams + " & " + "#games: " + games +" & " +"#rounds: " + rounds);
        System.out.println("pairs: " + pairs + " & " + "pairsUsed: " + pairsUsed);
        System.out.println("doubles: " + doubles + " & " + "doublesUsed: " + doublesUsed);
        System.out.println("currentGame: " + currentGame + " & " + "prevGame: " + prevGame);
        System.out.println("currentRound: " + currentRound + " & " + "prevRound: " + prevRound);
        System.out.println("TEMPBOARD: ");
        printBoard(tempBoard);


        //DONE 1: If the pairsArrayList is empty and every team plays every game THEN return tempVerdeling as OptionalNullable
        if (pairs.isEmpty() && allTeamsInEveryGame(numberOfTeams,tempBoard)){
            return Optional.of(tempBoard);
        }
        //DONE 2: Else if pairsUsed is empty and u try to put the first pair of pairs further than the last round THEN return Optional.empty
        else if ((pairsUsed.isEmpty() && currentRound >= rounds) || (pairs.isEmpty() && doubles.isEmpty())){
            return Optional.empty();
        } else {
            //DONE 3: Choose the right pair to place (If the pairs ArrayList is empty use the doubles)
            String pairToPlace = "";
            if (!pairs.isEmpty()){
                pairToPlace = pairs.get(0);
            }
            else {
                //TODO: Hier gebeurd iets vreemd als hij A-B als double gezet heeft en A-C heeft nergens een plaats gevonden dan wordt A-B van het spelbord verwijderd en is het volgende paar dat getest wordt C-D
                //ga er voorlopig van uit dat we steeds chronologisch werken
                pairToPlace = doubles.get(0);
            }
            System.out.println("pairToPlace: "+pairToPlace); //TODO delete
            //optie 1: backtrack TODO Backtrack werkt nog niet
            if (currentGame>=games){
                //pairs en/of doubles lijsten een fase terugzetten
                String toReset;
                //TODO deze if statements kloppen nog niet
                if (!pairsUsed.isEmpty()){
                    toReset = pairsUsed.get(pairsUsed.size()-1);
                    pairs.add(0,toReset);
                    pairsUsed.remove(pairsUsed.size()-1);

                } else if (!doublesUsed.isEmpty()){ //gaat bijna nooit empty zijn want want hebben die geinitieerd//TODO: nog oplossen wat het wordt als je ook doubles kan overslaan bij het zoeken
                    //TODO deze if statements kloppen nog niet
                    toReset = doublesUsed.get(doublesUsed.size()-1);
                    doublesUsed.remove(toReset);
                    doubles.add(0,toReset);
                }
                //Het paar op positie laatste item van prevRound en prevGame wissen
                int previousRound = prevRound.get(prevRound.size()-1);
                int previousGame = prevGame.get(prevGame.size()-1);
                tempBoard[previousRound][previousGame] = null;
                //currentGame wordt laatste item previousGame en currentRound wordt laatste item previousRound + 1
                currentGame = prevGame.get(prevGame.size()-1);
                currentRound = prevRound.get(prevRound.size()-1) +1;
                //laatste items van previousGame en previousRound wissen
                prevGame.remove(prevGame.size()-1);
                prevRound.remove(prevRound.size()-1);

                return solveSpelverdeling(numberOfTeams,pairs,pairsUsed,doubles,doublesUsed,games,currentGame,prevGame,rounds,currentRound,prevRound,tempBoard);
            }
            //optie 2
            else if (currentRound >= rounds){
                currentGame = currentGame + 1;
                currentRound = 0;
                return solveSpelverdeling(numberOfTeams,pairs,pairsUsed,doubles,doublesUsed,games,currentGame,prevGame,rounds,currentRound,prevRound,tempBoard);
            }
            //optie 3
            else if (!canPairGoThisGame(tempBoard,pairToPlace,currentGame)){
                currentRound = currentRound+1;
                return solveSpelverdeling(numberOfTeams,pairs,pairsUsed,doubles,doublesUsed,games,currentGame,prevGame,rounds,currentRound,prevRound,tempBoard);
            }
            //optie 4
            else if (canPairGoThisGame(tempBoard,pairToPlace,currentGame) && canPairGoThisRound(tempBoard,pairToPlace,currentRound) && !alreadyPairHere(tempBoard,currentRound,currentGame)){
                tempBoard[currentRound][currentGame] = pairToPlace;
                if (!pairs.isEmpty()){
                    pairsUsed.add(pairToPlace);
                    pairs.remove(0);
                }
                else if (!doubles.isEmpty()){ //TODO: nog oplossen wat het wordt als je ook doubles kan overslaan bij het zoeken
                    //TODO nog eens beredeneren
                    doublesUsed.add(pairToPlace);
                    //TODO nog eens beredeneren
                    doubles.remove(pairToPlace);
                }

                prevRound.add(currentRound);
                prevGame.add(currentGame);
                currentRound = 0;
                currentGame = 0;
                return solveSpelverdeling(numberOfTeams,pairs,pairsUsed,doubles,doublesUsed,games,currentGame,prevGame,rounds,currentRound,prevRound,tempBoard);
            }
            else{
                currentRound = currentRound + 1;
                return solveSpelverdeling(numberOfTeams,pairs,pairsUsed,doubles,doublesUsed,games,currentGame,prevGame,rounds,currentRound,prevRound,tempBoard);
            }
        }
    }


    public static void toonVerdeling(int ploegen, int spelletjes, int dubbels, int rondes) {
        Optional<String[][]> oplossing = spelverdeling(ploegen, spelletjes, dubbels, rondes);
        //TODO: nog verwijderen
        System.out.println("------------------------TOON EINDRESULTAAT-------------------------");
        if (oplossing.isEmpty()) {
            System.out.println("Er werd geen oplossing gevonden");
        } else {
            String[][] deOplossing = oplossing.get();
            System.out.print("         | ");
            for (int games = 0; games < deOplossing[0].length; games++) {
                if (games + 1 < 10) {
                    System.out.print("Spel " + (games + 1) + " |  ");
                } else {
                    System.out.print("Spel " + (games + 1) + "|  ");
                }
            }
            System.out.println("");
            System.out.print("_________ ________ ");
            for (int games = 0; games < deOplossing[0].length - 1; games++) {
                System.out.print("_________ ");
            }
            System.out.println("");
            for (int i = 0; i < deOplossing.length; i++) {
                if (i + 1 < 10) {
                    System.out.print("Ronde " + (i + 1) + "  |  ");
                } else {
                    System.out.print("Ronde " + (i + 1) + " |  ");
                }
                for (int j = 0; j < deOplossing[i].length; j++) {
                    if (deOplossing[i][j] != null) {
                        System.out.print(deOplossing[i][j] + "   |   ");
                    } else {
                        System.out.print("   " + "   |   ");
                    }
                }
                System.out.println("");
            }
        }
    }

    public static String[] getTeams(int numberOfTeams) {
        String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        return Arrays.copyOfRange(letters, 0, numberOfTeams);
    }

    public static ArrayList<String> getPairs(int numberOfTeams) {
        if (numberOfTeams < 2 || numberOfTeams > 26) {
            throw new InvalidInputException();
        }
        ArrayList<String> teams = new ArrayList<>();
        Collections.addAll(teams, getTeams(numberOfTeams));

        ArrayList<String> pairs = new ArrayList<>();
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                String pair = teams.get(i) + "-" + teams.get(j);
                pairs.add(pair);
            }
        }
        return pairs;
    }

    public static ArrayList<String> getTeamsFromPair(String pair) {
        ArrayList<String> teams = new ArrayList<>();
        for (int i = 0; i < pair.length(); i++) {
            char c = pair.charAt(i);
            String s = "" + c;
            if (!s.equals("-")) {
                teams.add(s);
            }
        }
        return teams;
    }

    public static boolean canPairGoThisRound(String[][] gameBoard, String pair, int round) {
        ArrayList<String> teamsAlreadyHere = new ArrayList<>();
        //get all teams this round
        for (int i = 0; i < gameBoard[round].length; i++) {
            if (gameBoard[round][i] != null) {
                teamsAlreadyHere.addAll(getTeamsFromPair(gameBoard[round][i]));
            }
        }
        //get teams of pair
        ArrayList<String> teamsOfPair = getTeamsFromPair(pair);
        //if a team of a pair is in this round than return false else return true
        for (String team : teamsOfPair) {
            if (teamsAlreadyHere.contains(team)) {
                return false;
            }
        }
        return true;
    }

    public static boolean canPairGoThisGame(String[][] gameBoard, String pair, int game) {
        ArrayList<String> teamsAlreadyHere = new ArrayList<>();
        //get all teams this game
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i][game] != null) {
                teamsAlreadyHere.addAll(getTeamsFromPair(gameBoard[i][game]));
            }
        }
        //get teams of pair
        ArrayList<String> teamsOfPair = getTeamsFromPair(pair);
        //if a team of a pair is in this game than return false else return true
        for (String team : teamsOfPair) {
            if (teamsAlreadyHere.contains(team)) {
                return false;
            }
        }
        return true;
    }

    public static boolean alreadyPairHere(String[][] gameBoard, int round, int game){
        return (gameBoard[round][game] != null);
    }

    public static boolean allTeamsInEveryGame(int numberOfTeams, String[][] board){
        ArrayList<String> allTeams = new ArrayList<>();
        Collections.addAll(allTeams, getTeams(numberOfTeams));
        for (int i = 0; i < board[0].length; i++){
            ArrayList<String> teamsThatPlayedGame = new ArrayList<>();
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] != null) {
                    ArrayList<String> teamsRoundGame = getTeamsFromPair(board[j][i]);
                    for (String t : teamsRoundGame){
                        if (!teamsThatPlayedGame.contains(t)){
                            teamsThatPlayedGame.add(t);
                        }
                    }
                }
            }
            if (teamsThatPlayedGame.size() != allTeams.size()){
                return false;
            }
            for (String t : allTeams){
                if (!teamsThatPlayedGame.contains(t)){
                    return false;
                }
            }
        }
        return true;
    }

    //extra
    public static void printBoard(String[][] board){
        String[][] deOplossing = board;
        System.out.print("         | ");
        for (int games = 0; games < deOplossing[0].length; games++) {
            if (games + 1 < 10) {
                System.out.print("Game " + (games + 1) + " |  ");
            } else {
                System.out.print("Game " + (games + 1) + "|  ");
            }
        }
        System.out.println("");
        System.out.print("_________ ________ ");
        for (int games = 0; games < deOplossing[0].length - 1; games++) {
            System.out.print("_________ ");
        }
        System.out.println("");
        for (int i = 0; i < deOplossing.length; i++) {
            if (i + 1 < 10) {
                System.out.print("Round " + (i + 1) + "  |  ");
            } else {
                System.out.print("Round " + (i + 1) + " |  ");
            }
            for (int j = 0; j < deOplossing[i].length; j++) {
                if (deOplossing[i][j] != null) {
                    System.out.print(deOplossing[i][j] + "   |   ");
                } else {
                    System.out.print("   " + "   |   ");
                }
            }
            System.out.println("");
        }
    }
}
