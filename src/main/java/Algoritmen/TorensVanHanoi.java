import java.util.ArrayList;

public class TorensVanHanoi {
    int aantalSchijven;
    ArrayList<Integer> paalX;
    ArrayList<Integer> paalY;
    ArrayList<Integer> paalZ;

    public TorensVanHanoi(int aantalSchijven) {
        this.aantalSchijven = aantalSchijven;
        this.paalX = new ArrayList<>();
        this.paalY = new ArrayList<>();
        this.paalZ = new ArrayList<>();

        for (int i = aantalSchijven;i>0;i--){
            paalX.add(i);
        }
    }
    public static void main(String[] args){
        TorensVanHanoi spel = new TorensVanHanoi(3);
        spel.sorteerNSchijvenVanXNaarYmbvZ(spel.getAantalSchijven(),spel.getPaalX(),spel.getPaalY(),spel.getPaalZ());
    }
    public int getAantalSchijven() {
        return aantalSchijven;
    }
    public ArrayList<Integer> getPaalX() {
        return paalX;
    }
    public ArrayList<Integer> getPaalY() {
        return paalY;
    }
    public ArrayList<Integer> getPaalZ() {
        return paalZ;
    }
    public void printPaalToString(ArrayList<Integer> paal){
        for (int i = 0; i < paal.size();i++){
            System.out.print(paal.get(i)+" ");
        }
    }
    public void printSpelToString(){
        System.out.print("Paal X : ");
        printPaalToString(paalX);
        System.out.println("");
        System.out.print("Paal Y : ");
        printPaalToString(paalY);
        System.out.println("");
        System.out.print("Paal Z : ");
        printPaalToString(paalZ);
        System.out.println("");
    }

    public void moveSchijf(ArrayList<Integer> paalVan,ArrayList<Integer> paalNaar){
        int n = paalVan.get(paalVan.size()-1);
        paalVan.remove( paalVan.size()-1);
        paalNaar.add(n);
    }

    public void sorteerNSchijvenVanXNaarYmbvZ(int n,ArrayList<Integer> paalA, ArrayList<Integer> paalB,ArrayList<Integer> paalC){
        printSpelToString();
        System.out.println("------------------------------------------");
        if (n==1){
            moveSchijf(paalA,paalB);
            printSpelToString();
            System.out.println("------------------------------------------");
        }
        else {
            sorteerNSchijvenVanXNaarYmbvZ(n - 1, paalA, paalC, paalB);
            moveSchijf(paalA, paalB);
            sorteerNSchijvenVanXNaarYmbvZ(n - 1, paalC, paalB, paalA);
        }
    }

}
