/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Assurance;

import XmlStream.XmlWriter;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;

/**
 *
 * @author Admin
 */
public class Demande {
    private String soin;
    private String date;
    private String price;
    
    public Demande(String soin, String date, String price){
        this.soin = soin;
        this.date = date;
        this.price = price;
    }
    
    public boolean VerifyDataAccuracy(String baseMonth){
        DemandDataAccuracy demandDataAccuracy = new DemandDataAccuracy();
        if(!demandDataAccuracy.IsMontantAccurate(price)
                ||!demandDataAccuracy.IsMonthAccurate(date, baseMonth)
                ||!demandDataAccuracy.IsSoinAccurate(soin))
            return false;
        return true;
    }
        
    public double getPriceToDouble() {
        //The price must have the following format : x.xx$
        int period = price.indexOf(".");
        double unit = Double.parseDouble(price.substring(0, period));
        double fraction = Double.parseDouble(price.substring(period, price.length() - 1));
        fraction /= 100;
        return unit + fraction;
    }
      
    public String setPriceToFormattedString(double price) {
        //The price must have the following format : x.xx$
        String formattedString = String.valueOf(price);
        int dot = formattedString.indexOf(".");
        if(dot == -1){
            formattedString += ".00$";
        } else if(dot == 0){
            formattedString = "0." + formattedString;
        }
        if(formattedString.substring(dot + 1).length() == 1)
            formattedString += "0";
        formattedString += "$";
        return formattedString;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    
        
    public String getSoin() {
        return soin;
    }
    
    public String getDate() {
        return date;
    }

    public String getPrice() {
        return price;
    }
    
    @Override
    public String toString(){
        return "soin:" + soin + "\n" + 
               "date:" + date + "\n" + 
               "montant:" + price + "\n"; 
    }






}
