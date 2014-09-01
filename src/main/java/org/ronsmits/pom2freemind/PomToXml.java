package org.ronsmits.pom2freemind;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Date;

/**
 * Created by ron on 1-9-14.
 */
class PomToXml {
    private Document doc;

    public PomToXml() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        doc = docBuilder.newDocument();
    }

    public void createXML(Pom pom) {
        Element root = doc.createElement("map");
        root.setAttribute("version", "1.0.1");
        doc.appendChild(root);
        recursePom(pom, root);


    }

    private void recursePom(Pom pom, Element root) {

        Element element = createNode(pom);
        root.appendChild(element);
        for (Pom child : pom.getChildren()) {
            recursePom(child, element);
        }
    }

    private Element createNode(Pom pom) {
        Element element = doc.createElement("node");
        element.setAttribute("TEXT", pom.toString());
        element.setAttribute("POSITION", "RIGHT");
        element.setAttribute("ID", String.format("ID_%d", new Date().getTime()));
        element.setAttribute("CREATED", String.valueOf(new Date().getTime()));
        return element;
    }

    public String toXml() throws TransformerException {
        //set up a transformer
        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = transfac.newTransformer();
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        trans.setOutputProperty(OutputKeys.INDENT, "yes");

        //create string from xml tree
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        DOMSource source = new DOMSource(doc);
        trans.transform(source, result);
        return sw.toString();
    }

    public void writeXML(String outputfile) throws IOException, TransformerException {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputfile), "utf-8"));
        writer.write(toXml());
        writer.close();
    }
}