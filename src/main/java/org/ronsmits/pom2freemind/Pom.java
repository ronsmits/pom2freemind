package org.ronsmits.pom2freemind;

import org.apache.maven.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ron on 1-9-14.
 */
public class Pom {
    private final List<Pom> children = new ArrayList<>();
    private Model model;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        model.getParent().getGroupId();
        if (model.getGroupId()==null) {
            builder.append(model.getParent().getGroupId()==null? "": String.format("%s ", model.getParent().getGroupId()));
        } else {
            builder.append(String.format("%s ", model.getGroupId()));
        }
        builder.append(model.getArtifactId()).append(" ");
        if (model.getVersion()==null){
            builder.append(model.getParent().getVersion()==null?"":String.format("%s", model.getParent().getVersion()));
        } else
                builder.append(model.getVersion());
        return builder.toString();
    }

    public List<Pom> getChildren() {
        return children;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
