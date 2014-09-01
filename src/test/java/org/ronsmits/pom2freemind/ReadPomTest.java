package org.ronsmits.pom2freemind;

import org.apache.maven.model.Model;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ReadPomTest {

    @Test
    public void testGetModelNoModules() throws Exception {
        Model model = ReadPom.getModel("testproject/nomodules.xml");
        List<String> modules = model.getModules();
        assertEquals(0, modules.size());
    }

    @Test
    public void testGetModel2Modules() throws Exception {
        Model model = ReadPom.getModel("testproject/2modules.xml");
    }
}