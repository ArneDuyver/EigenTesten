package Algoritmen;

import java.util.ArrayList;

public class KoninginnenProbleem {

    public static void main(String[] args){
       int n = 6;
       System.out.println(nQueens(n));
    }
    //static anders niet oproepen zonder object te maken
    public static ArrayList<Integer> nQueens(int n){
        return queens(n,1,1,new ArrayList<Integer>());
    }

    private static ArrayList<Integer> queens(int aantalKoninginnen, int huidigeKolom, int huidigeRij,ArrayList<Integer> vorigeQ){
        if (aantalKoninginnen < huidigeKolom){ //als de huidige queen die je wil plaatsen groter is dan aantal dat je moet zetten weet je dat de vorige list de oplossing was
            return vorigeQ;
        }
        else if (isVeilig(huidigeRij, vorigeQ) && (huidigeRij<=aantalKoninginnen)){
            //bewaar de huidige positie voor de huidige koningin
            vorigeQ.add(huidigeRij);
            //probeer de volgende koninging te plaatsten
            return queens(aantalKoninginnen,huidigeKolom+1,1,vorigeQ);
        }
        //nagaan of de huidigeKolom/koningin nog een volgende mogelijkheid/rij heeft
        //alleen zinvol indien er nog plaats is
        else if (huidigeRij<aantalKoninginnen){
            return queens(aantalKoninginnen, huidigeKolom,huidigeRij+1, vorigeQ);
        }
        //backtracking
        else {
            //je kan niet verder backtracken => geen oplossing
            if (huidigeKolom == 1) return new ArrayList<>();
            else {
                int vorigeKolom = huidigeKolom - 1;
                int vorigeRij = vorigeQ.get(vorigeQ.size() - 1);
                vorigeQ.remove(vorigeQ.size() - 1);
                return queens(aantalKoninginnen, vorigeKolom, vorigeRij + 1, vorigeQ);
            }
        }
    }

    public static boolean isVeilig(int rij, ArrayList<Integer> vorige) {
        for(int rij2 : vorige){
            int verschil = vorige.size()-vorige.indexOf(rij2);
            if (rij == rij2){
                return false;
            }
            else if (rij2 == rij-verschil || rij2==rij+verschil) {
                return false;
            }
        }
        return true;
    }
}
