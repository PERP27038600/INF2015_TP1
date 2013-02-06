package XmlStream;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlWriter {
    private Document d;
    public XmlWriter() throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        d = db.newDocument();
        d.setXmlStandalone(true);
    }
    
    public Element CreateRoot (String root){
        Element rootElement = d.createElement(root);
        d.appendChild(rootElement);
        return rootElement;
    }
    
    public Element CreateRoot (String subNode, Element parentNode){
        Element childElement = d.createElement(subNode);
        parentNode.appendChild(childElement);
        return childElement;
    }

    
    public Element CreateElement (String category, String value, Element parentNode){
        Element element = d.createElement(category);
        element.appendChild(d.createTextNode(value));
        parentNode.appendChild(element);
        return element;
    }
       
    
    public void SaveDocumentToFile(String filename) throws TransformerConfigurationException, TransformerException{
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.STANDALONE, "yes");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
        DOMSource source = new DOMSource(d);
        StreamResult result = new StreamResult(new File(filename));
        t.transform(source, result);
    }
}
