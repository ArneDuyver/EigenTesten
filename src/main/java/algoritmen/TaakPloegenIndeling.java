package algoritmen;

import java.util.Optional;

public class TaakPloegenIndeling {
    private String[] mogelijkePloegen;
    private String[] ploegenArray;


    public static void main(String[] args){
        System.out.println("Hello\nWorld");
        //toonVerdeling(2,1,1,1);

        String[][] oplossing2 = new String[2][2];
        String leeg = Optional.empty();
        oplossing2[0][0] = leeg;
        Optional<String[][]> deOplossing2 = Optional.of(oplossing2);
        System.out.println(deOplossing2.get()[0][0]);
        String nieuweLetter = "b";

        if( deOplossing2.get()[0][0].isEmpty()){
            System.out.println("Do stuff");
        }
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
        //TODO: code schrijven
        //INPUT VALIDATIE
        if (ploegen%2!=0 || ploegen < 2 || spelletjes<1 || dubbels < 1 || rondes < 1 ){
            throw new algoritmen.InvalidSpelverdelingInputException();
        }
        String[][] oplossing = new String[rondes][spelletjes];
        for()


        //TEST
        //oplossing[0][0] = "A-B";

        return oplossing;
    }

    /**
     * Volgende methode print de ploegenindeling in de console
     * @param ploegen Het aantal ploegen, moet even zijn
     * @param spelletjes Het totaal aantal spelletjes
     * @param dubbels Het maximum aantal keer dat 2 ploegen tegen elkaar mogen spelen
     * @param rondes Het aantal rondes
     */
    public static void toonVerdeling(int ploegen, int spelletjes, int dubbels, int rondes){
        //TODO: code schrijven die de 2D array mooi afdrukt: enkel via  " System.out.println(""); "
        Optional<String[][]> oplossing = spelverdeling(ploegen,spelletjes,dubbels,rondes);

        if (oplossing.isEmpty()){
            System.out.println("Er werd geen oplossing gevonden");
            return;
        }
        String[][] deOplossing = oplossing.get();
        System.out.println(deOplossing[0][0]);
    }

    public static String[] getLetters(){
        String[] letterArray = new String[26];
        return letterArray;
    }
}
