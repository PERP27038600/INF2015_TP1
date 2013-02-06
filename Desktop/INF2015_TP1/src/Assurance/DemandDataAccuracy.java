package Assurance;

public class DemandDataAccuracy {
    
    //TODO : Changer pour mettre qu'un return
    public boolean IsMontantAccurate(String montant){
        //The price must have the following format : x.xx$
        if(montant.length() < 5)
            return false;
        if (!montant.endsWith("$"))
            return false;
        return true;
    }
    
    public boolean IsMonthAccurate(String month, String baseMonth){
        //La date doit impérativement avoir ce format : AAAA-MM-JJ
        final int MONTH_FORMAT_LENGTH = 10;
        final String YEAR_MONTH_DELIMITER = "-";
        final int YEAR_MONTH_DELIMITER_POS = 8;   
        if(month.length() != MONTH_FORMAT_LENGTH) 
            return false;
        String[] Times = month.split(YEAR_MONTH_DELIMITER);
        if(Times.length != 3)
            return false;
        for(String date : Times){
            if(!IsNumeric(date))
                return false;
        }
        if(!month.startsWith(baseMonth))
            return false;
        return true;
    }
    
    public boolean IsSoinAccurate(String soin){
        int iSoin = 0;
        try{
            iSoin = Integer.parseInt(soin);
        } catch(Exception e) {
            return false;
        }
        if(!VerifySoin(iSoin))
            return false;
        return true;
    }
           
    private boolean VerifySoin(int soin){
        if(soin == 0 || soin == 100 || soin == 200 || soin == 300 
                || soin == 400 || soin == 500 || soin == 600 || soin == 700)
            return true;
        if(soin > 300 && soin < 400)
            return true;
        return false;
    }
    
    private boolean IsNumeric(String number){
        try{
            Integer.parseInt(number);
            return true;   
        } catch(Exception e) {
            return false;
        }
    }
    
}
