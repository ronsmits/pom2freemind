package org.ronsmits.pom2freemind;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import static org.junit.Assert.*;

public class PomRecurseTest {

    @Test
    public void testRecurse() throws Exception {

        PomRecurse recurse= new PomRecurse();
        Pom pom = recurse.recurse("testproject/pom.xml");
        assertEquals(1, pom.getChildren().size()); // there are two modules, but not on the same level
        Pom childpom  = pom.getChildren().get(0);
        assertEquals(1, childpom.getChildren().size());

    }

    @Test
    public void toXML() throws IOException, XmlPullParserException, ParserConfigurationException, TransformerException {
        PomRecurse recurse = new PomRecurse();
        Pom pom = recurse.recurse("testproject/pom.xml");
        PomToXml toXml = new PomToXml();
        toXml.createXML(pom);
        System.out.println(toXml.toXml());
    }

}