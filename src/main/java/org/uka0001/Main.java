package org.uka0001;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException{
//        File file = new File("src/main/resources/sample.csv");
//        List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        InputStream stream = Main.class.getClassLoader().getResourceAsStream("sample.csv");
        List<String> lines = IOUtils.readLines(stream, StandardCharsets.UTF_8);
        List<Person> personList = CSVOrm.transform(lines, Person.class);
        List<Person2> personList2 = CSVOrm.transform(lines, Person2.class);
    }
}
