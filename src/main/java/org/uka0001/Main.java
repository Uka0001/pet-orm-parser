package org.uka0001;

import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Main {

    @SneakyThrows
    public static void main(String[] args) throws IOException {
//        File file = new File("src/main/resources/sample.csv");
//        List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
//        InputStream stream = Main.class.getClassLoader().getResourceAsStream("sample.csv");
//        List<String> lines = IOUtils.readLines(stream, StandardCharsets.UTF_8);
//        List<Person> personList = CSVOrm.transform(lines, Person.class);
//        List<Person2> personList2 = CSVOrm.transform(lines, Person2.class);
        URL url = Main.class.getClassLoader().getResource("sample.csv");
        List<Person> result = new ORM().transform(new File(url.toURI()), Person.class);
    }
}
