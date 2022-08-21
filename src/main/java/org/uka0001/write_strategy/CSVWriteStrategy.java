package org.uka0001.write_strategy;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CSVWriteStrategy implements WriteStrategy {
    private File file;

    @SneakyThrows
    @Override
    public void write(List<?> list) {
        String content = String.join(System.lineSeparator(), convertToListOfHeaderAndData(list));

        FileUtils.writeStringToFile(file, content, StandardCharsets.UTF_8);
    }

    private List<String> convertToListOfHeaderAndData(List<?> list) {
        Class cls = list.get(0).getClass();
        List<Field> fields = Arrays.asList(cls.getDeclaredFields());
        List<String> result = new ArrayList<>();
        result.add(convertToHeader(fields));
        result.addAll(list.stream().map(item -> transform(item, fields)).collect(Collectors.toList()));
        return result;
    }

    @SneakyThrows
    private String transform(Object o, List<Field> fields) {
        List<String> person = new ArrayList<>();
        for (Field each : fields) {
            each.setAccessible(true);
            String s = String.valueOf(each.get(o));
        }
        return String.join(",", person);
    }

    private String convertToHeader(List<Field> fields) {
        List<String> headers = new ArrayList<>();
        for (Field each : fields) {
            headers.add(each.getName());
        }
        return String.join(",", headers);
    }
}
