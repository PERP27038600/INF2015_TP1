/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XmlStream;

import Assurance.Assurance;
import Assurance.Demande;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlReader{
    
    private Document document;
    private XPath xPath;

    public XmlReader(String fileName) throws ParserConfigurationException, IOException, SAXException{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        File file = new File(fileName);
        document = documentBuilder.parse(file);
        XPathFactory xPathFactory = XPathFactory.newInstance();
        xPath = xPathFactory.newXPath();
    }
    
    public Assurance ParseHeader() throws XPathExpressionException{
        String client = xPath.evaluate(
                  "/reclamations/client", document);
        String contrat = xPath.evaluate(
                  "/reclamations/contrat", document);
        String mois = xPath.evaluate(
                  "/reclamations/mois", document);
        Assurance reclamationHeader = new Assurance(client, contrat, mois);
        return reclamationHeader;
    }
    
    public ArrayList<Demande> parseReclamations() throws XPathExpressionException{
        ArrayList<Demande> reclamations = new ArrayList<>();
        int itemCount = Integer.parseInt(xPath.evaluate(
                "count(/reclamations/reclamation)", document));
        for (int i = 1; i <= itemCount; i++){
            reclamations.add(BuildReclamation(i));
        }
        return reclamations;
        
    }
    
    private Demande BuildReclamation(int indexElement) throws XPathExpressionException{
        String soin = xPath.evaluate(
              "/reclamations/reclamation[" + indexElement + "]/soin", document);
        String date = xPath.evaluate(
              "/reclamations/reclamation[" + indexElement + "]/date", document);
        String montant = xPath.evaluate(
              "/reclamations/reclamation[" + indexElement + "]/montant", document);
        Demande reclamation = new Demande(soin, date, montant);
        return reclamation;
    }

}

