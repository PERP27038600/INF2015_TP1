package Assurance;

import java.util.ArrayList;

//TODO : Changer pour un return seulement
public class AssuranceDataAccuracy {
    public boolean IsClientDataAccurate(String client){
        final int CLIENT_NBR_DIGITS = 6;
        if(client.length() != CLIENT_NBR_DIGITS)
            return false;
        return IsNumeric(client);   
    }
    
    public boolean IsContratDataAccurate(String contrat){
        if(contrat.length() != 1)
            return false;
        char ch = contrat.charAt(0);
        if(!(ch == 'A') && !(ch == 'B') && !(ch == 'C') && !(ch == 'D'))
            return false;
        return true;
    }
   
    public boolean IsMonthDataAccurate(String month){
        //La date doit impérativement avoir ce format : AAAA-MM
        final int MONTH_FORMAT_LENGTH = 7;
        final String YEAR_MONTH_DELIMITER = "-";
        final int YEAR_MONTH_DELIMITER_POS = 4;      
        if(month.length() != MONTH_FORMAT_LENGTH) 
            return false;
        String[] Times = month.split(YEAR_MONTH_DELIMITER);
        if(Times.length != 2)
            return false;
        for(String date : Times){
            if(!IsNumeric(date))
                return false;
        }  
        return true;
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
