package Assurance;

import XmlStream.XmlWriter;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Element;

public class Assurance {
    private String client;
    private String contrat;
    private String month;
    ArrayList<Demande> reclamations;
    
    public Assurance(String client, String contrat, String month) {
        this.client = client;
        this.contrat = contrat;
        this.month = month;
        this.reclamations = null;
    }
    
    public void SetDemandes(ArrayList<Demande> reclamations){
        this.reclamations = reclamations;
    }
    
    public void VerifyAssuranceData() throws ReclamationAccuracyException{
        AssuranceDataAccuracy assuranceDataAccuracy = new AssuranceDataAccuracy();
        if(!assuranceDataAccuracy.IsClientDataAccurate(client)
                ||!assuranceDataAccuracy.IsContratDataAccurate(contrat)
                ||!assuranceDataAccuracy.IsMonthDataAccurate(month))
            throw new ReclamationAccuracyException();
        for(Demande demande : reclamations){
            if(!demande.VerifyDataAccuracy(month))
                throw new ReclamationAccuracyException();
        }
    }
    
    public void calculRemboursement(){
        for(Demande demand : reclamations){
            int iSoin = Integer.parseInt(demand.getSoin());
            double dPrice = demand.getPriceToDouble();
            Contrat contract = new Contrat(contrat, iSoin);
            contract.ChooseContrat();
            dPrice = contract.CalculeDiscount(dPrice);
            String newPrice = demand.setPriceToFormattedString(dPrice);
            demand.setPrice(newPrice);
        }
    }
    
    public void SaveAssuranceToXMLFile(String filename) throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
        XmlWriter xmlOut = new XmlWriter();
        Element currentNode = xmlOut.CreateRoot("reclamations");
        xmlOut.CreateElement("client", client, currentNode);
        xmlOut.CreateElement("contrat", contrat, currentNode);
        xmlOut.CreateElement("mois", month, currentNode);
        for (Demande demand : reclamations){
            Element subNode = xmlOut.CreateRoot("reclamation", currentNode);
            xmlOut.CreateElement("soin", demand.getSoin(), subNode);
            xmlOut.CreateElement("date", demand.getDate(), subNode);
            xmlOut.CreateElement("montant", demand.getPrice(), subNode);
        }
        xmlOut.SaveDocumentToFile(filename);
    }
    
    @Override
    public String toString(){
        String reclamations = "";
        for(Demande reclamation : this.reclamations){
            reclamations += reclamation;
        }
        return "client:" + client + "\n" + 
               "contrat:" + contrat + "\n" + 
               "mois:" + month + "\n" + 
               reclamations;
    }
}
