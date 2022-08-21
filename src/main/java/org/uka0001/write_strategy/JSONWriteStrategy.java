package org.uka0001.write_strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@AllArgsConstructor
public class JSONWriteStrategy implements WriteStrategy {
    private File file;

    @SneakyThrows
    @Override
    public void write(List<?> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(list);
        Files.write(Path.of(file.getPath()), jsonString.getBytes());
    }
}
