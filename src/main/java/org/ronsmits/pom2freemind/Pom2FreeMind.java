package org.ronsmits.pom2freemind;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by ron on 1-9-14.
 */
class Pom2FreeMind {

    private Pom2FreeMind(String start, String outputfile) {
        Pom pom;
        try {
            pom = new PomRecurse().recurse(start);
            PomToXml toXml = new PomToXml();
            toXml.createXML(pom);
            toXml.writeXML(outputfile);

        } catch (IOException | XmlPullParserException | ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        if (args.length!=2){
            System.err.println("you need to enter pom.xml file and output file name");
            System.exit(-1);
        } else {
            new Pom2FreeMind(args[0], args[1]);
        }
    }

}
