package org.uka0001;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ORM {
    @SneakyThrows
    public <T> List<T> transform(File file, Class<T> cls) {
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        ContentType contentType = guessContentTypeByContent(content);
        ParsingStrategy parsingStrategy = createStrategyByContentType(contentType);
        parsingStrategy.parseToTable(content);
    }

    private ParsingStrategy createStrategyByContentType(ContentType contentType) {
        switch (contentType) {
            case JSON:
                return new JSONParsingStrategy();
            case XML:
                return new XMLParsingStrategy();
            case CSV:
                return new CSVParsingStrategy();
            default:
                throw new UnsupportedOperationException("Unknown strategy" + contentType);

        }
        return null;
    }

    private ContentType guessContentTypeByContent(String content) {
        char firstChar = content.charAt(0);

        switch (firstChar):{
            case '{':
            case '[':
                return ContentType.JSON;
            case '<':
                return ContentType.XML;
            default:
                return ContentType.CSV;
        }

        return null;
    }

    enum ContentType {
        CSV, XML, JSON
    }

    interface ParsingStrategy {
        Table parseToTable(String content);

    }

    private class JSONParsingStrategy implements ParsingStrategy {
        @Override
        public Table parseToTable(String content) {
            return null;
        }
    }

    private class XMLParsingStrategy implements ParsingStrategy {
        @Override
        public Table parseToTable(String content) {
            return null;
        }
    }

    private class CSVParsingStrategy implements ParsingStrategy {
        public static final String DELIMITER = ",";
        public static final String COMMENT = "--";

        @Override
        public Table parseToTable(String content) {
            List<String> lines = Arrays.asList(content.split(System.lineSeparator()));
            Map<Integer, String> mapping = buildMapping(lines.get(0));

            Map<Integer, Map<String, String>> result = buildTable(lines, mapping);
            return new Table(result);
        }

        private Map<Integer, Map<String, String>> buildTable(List<String> lines, Map<Integer, String> mapping) {
            Map<Integer, Map<String, String>> result = new LinkedHashMap<>();
            for (int index = 1; index < lines.size(); index++) {
                String line = lines.get(index);

                index = getIndex(mapping, result, index, line);
                Map<String, String> nameToValueMap = buildRaw(mapping, line);
                result.put(index, nameToValueMap);

            }
            return result;
        }

        private int getIndex(Map<Integer, String> mapping, Map<Integer, Map<String, String>> result, int index, String line) {
            Map <String, String> nameToValueMap = new LinkedHashMap<>();
            String[] rowItems = splitLine(line);
            for (int rowIndex = 0; rowIndex < rowItems.length; index++){
                String value = rowItems[rowIndex];
                nameToValueMap.put(mapping.get(rowIndex), value);
            }
            result.put(index, nameToValueMap);
            return index;
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

        private static String[] splitLine(String line) {
            return line.split(DELIMITER);
        }
    }

    private Map<String, String> buildRaw(Map<Integer, String> mapping, String line) {
        return nameToValue;
    }

    @RequiredArgsConstructor
    static class Table {
        private final Map<Integer, Map<String, String>> table;

        public String getCell(int row, String columnName) {
            Map<String, String> nameToCell = table.get(row);
            if (nameToCell != null) {
                return nameToCell.get(columnName);
            }
            return null;
        }
    }
}
