package org.uka0001.orm_releases.release1v2;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        URL url = Main.class.getClassLoader().getResource("sample.xml");
        String content = FileUtils.readFileToString(new File(url.toURI()), StandardCharsets.UTF_8);

        ORMInterface _interface = new ORM();
        List<Person> result = _interface.transform(new ORMInterface.StringInputSource(content), Person.class);
    }
}