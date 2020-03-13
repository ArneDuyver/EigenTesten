package tdd;

import java.util.Date;

public class Periode {
    private Date startDate;
    private Date eindDate;

    public Periode(Date startDate, Date eindDate) {
        this.startDate = startDate;
        this.eindDate = eindDate;
    }

    public boolean isInPeriode(Date date){
        //Als je nog niks in de functie hebt steken maar je wil wel al testen =>
        //throw new UnsupportedOperationException(); //het gaat tijdens de test dan wel crashen

        //DATAVALIDATIE
        if(date == null){
            throw new tdd.InvalidPeriodInputException();
        }


        if(date.after(startDate) && date.before(eindDate)){
            return true;
        }
        else {
            return false;
        }
    }
}
