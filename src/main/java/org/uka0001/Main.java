package org.uka0001;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static final String DELIMITER = ",";
    public static final String COMMENT = "--";

    public static void main(String[] args) throws IOException, NullPointerException {
        File file = new File("src/main/resources/sample.csv");
        List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
//        InputStream stream = Main.class.getClassLoader().getResourceAsStream("sample.csv");
//        List<String> lines = IOUtils.readLines(stream, StandardCharsets.UTF_8);
//        LineIterator itr = FileUtils.lineIterator();
        Map<Integer, String> mapping = buildMapping(lines.get(0));
        List<Person> personList = transform(mapping, lines.subList(1, lines.size()));
    }

    private static Map<Integer, String> buildMapping(String firstLine) {
        Map<Integer, String> map = new LinkedHashMap<>();
        String[] array = splitLine(firstLine);
        for (int index = 0; index < array.length; index++) {
            String value = array[index];
            if (value.contains(COMMENT)) {
                value = value.split(COMMENT)[0];
            }
            map.put(index, value.trim());
        }
        return map;
    }

    private static List<Person> transform(Map<Integer, String> mapping, List<String> lines) {
        return lines.stream().map(line ->
                        toPerson(line, mapping))
                .collect(Collectors.toList());
    }

    private static Person toPerson(String line, Map<Integer, String> mapping) {
        Person person = new Person();

        String[] array = splitLine(line);
        for (int index = 0; index < array.length; index++) {
            String value = array[index];
            String fieldNAme = mapping.get(index);
            setValueIntoFieldOrThrow(value, fieldNAme, person);
        }
        return person;
    }

    private static void setValueIntoFieldOrThrow(String value, String fieldNAme, Person person) {
        try {
            Field field = person.getClass().getField(fieldNAme);
            field.setAccessible(true);
            field.set(fieldNAme, value);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private static String[] splitLine(String line) {
        return line.split(DELIMITER);
    }
}
