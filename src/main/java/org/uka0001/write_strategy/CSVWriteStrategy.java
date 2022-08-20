package org.uka0001.write_strategy;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class CSVWriteStrategy implements WriteStrategy {
    File file = null;

    @SneakyThrows
    @Override
    public void write(List<?> list) {
        String content = String.join(System.lineSeparator(), convertToListOfHeaderAndData(list));

        FileUtils.writeStringToFile(file, content, StandardCharsets.UTF_8);
    }

    private List<String> convertToListOfHeaderAndData(List<?> list) {
        Class cls = list.get(0).getClass();
        List<Field> fields = Arrays.asList(cls.getDeclaredFields());
        //TODO

        return null;
    }
}
