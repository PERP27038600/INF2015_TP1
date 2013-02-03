package Assurance;

public class Contrat {
    private String contract;
    private int soin;
    private int pourcent;
    private double maximum;
    
    public Contrat(String contract, int soin){
        this.contract = contract;
        this.soin = soin;
    }
    
    public void ChooseContrat() {
        switch (contract){
            case "A":
                ContratA();
                break;
            case "B":
                ContratB();
                break;
            case "C":
                ContratC();
                break;
            case "D":
                ContratD();
                break;
        }
    }
    
    private void ContratA(){
        maximum = Double.MAX_VALUE;
        if(soin == 0 || soin == 100 || soin == 200 || soin == 500)
            pourcent = 25;
        else if((soin >= 300 && soin < 400) || soin == 400 || soin == 700)
            pourcent = 0;
        else if(soin == 600)
            pourcent = 40;
    }
    
    private void ContratB(){
        if(soin == 0 || soin == 300 ||(soin >= 300 && soin < 400) || soin == 500)
            pourcent = 50;
        else if(soin == 200 || soin == 600)
            pourcent = 100;
        else if(soin == 400)
            pourcent = 0;
        else if(soin == 700)
            pourcent = 70;
    }
            
    private void ContratC(){
        pourcent = 90;
        maximum = Double.MAX_VALUE;
    }
    
    private void ContratD(){
        pourcent = 100;
        if(soin == 0)
            maximum = 85.0;
        else if(soin == 100 || soin == 500)
            maximum = 100.0;
        else if(soin == 200 || soin == 600)
            maximum = 100.0;
        else if(soin >= 300 && soin < 400)
            maximum = Double.MAX_VALUE;
        else if(soin == 400)
            maximum = 65.0;
        else if(soin == 700)
            maximum = 90.0;
    }
    
    public double CalculeDiscount(double montant){
        double discoutPourcent = montant * pourcent / 100;
        if(discoutPourcent > maximum)
            return montant - maximum;
        else 
            return montant - discoutPourcent;
    }
    
}
