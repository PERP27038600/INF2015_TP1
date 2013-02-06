package inf2015_tp1;

import Assurance.Assurance;
import Assurance.ReclamationAccuracyException;
import XmlStream.XmlReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

public class INF2015_TP1 {
    public static void main(String[] args) throws ReclamationAccuracyException{
        if(args.length < 2 || args.length > 2){
            javax.swing.JOptionPane.showMessageDialog(null, 
                    "Veuillez entré un chemin de fichier en entrée et en sortie."
                    + "Le programme va quitter\n");
            
            return;
        }
        String pathIn = args[0];
        String pathOut = args[1];
        Assurance assurance = null;
        try {
            assurance = ExtractReclamationsFromXMLFile(pathIn);
            assurance.VerifyAssuranceData();
            assurance.calculRemboursement();
            assurance.SaveAssuranceToXMLFile(pathOut);
            //System.out.println(assurance);
            //TODO: Il reste à faire à écrire le XML et à faire les tests
        } catch (ReclamationAccuracyException ex){
            assurance.ErrorToXMLFile(pathOut, ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(INF2015_TP1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static Assurance ExtractReclamationsFromXMLFile(String fileName) throws ParserConfigurationException, IOException, IOException, SAXException, XPathExpressionException{
        XmlReader xmlReader = new XmlReader(fileName);
        Assurance assurance = xmlReader.ParseHeader();
        assurance.SetDemandes(xmlReader.parseReclamations());
        return assurance;
    }

}