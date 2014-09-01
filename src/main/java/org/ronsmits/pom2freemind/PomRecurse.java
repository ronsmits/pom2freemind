package org.ronsmits.pom2freemind;

import org.apache.maven.model.Model;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.File;
import java.io.IOException;

/**
 * Created by ron on 1-9-14.
 */
class PomRecurse {

    public Pom recurse(String start) throws IOException, XmlPullParserException {
        File file = new File(start);
        System.out.println(file.getParent());
        Model model = ReadPom.getModel(start);

        Pom pom = new Pom();
        pom.setModel(model);
        recurseModel(file.getParent(), pom);
        return pom;
    }

    private void recurseModel(String parent, Pom pom) throws IOException, XmlPullParserException {
        for (String module : pom.getModel().getModules()) {
            Pom child = recurse(parent.concat("/").concat(module).concat("/pom.xml"));
            pom.getChildren().add(child);
        }
    }
}
