package inf2015_tp1;

import Assurance.Assurance;
import Assurance.ReclamationAccuracyException;
import XmlStream.XmlReader;
import XmlStream.XmlWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class INF2015_TP1 {
    public static void main(String[] args) throws ReclamationAccuracyException{
        //TODO : Put in args
        String pathIn = "C:/Users/Admin/Desktop/INF2015_TP1/src/inf2015_tp1/reclamations.xml";
        //TODO : Put in args
        String pathOut = "C:/Users/Admin/Desktop/INF2015_TP1/src/inf2015_tp1/remboursement.xml";
        
        
        try {
            Assurance assurance = ExtractReclamationsFromXMLFile(pathIn);
            assurance.VerifyAssuranceData();
            assurance.calculRemboursement();
            assurance.SaveAssuranceToXMLFile(pathOut);
            //System.out.println(assurance);
            //TODO: Il reste à faire à écrire le XML et à faire les tests
        } catch (ReclamationAccuracyException ex){
            //FAire un XML out erreur
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