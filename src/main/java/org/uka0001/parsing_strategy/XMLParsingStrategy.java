package org.uka0001.parsing_strategy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;
import org.uka0001.ORM;
import org.uka0001.read_write_sourse.FileReadWriteSource;

public class XMLParsingStrategy implements ParsingStrategy<FileReadWriteSource> {
    @SneakyThrows
    @Override
    public ORM.Table parseToTable(FileReadWriteSource content) {
        XmlMapper mapper = new XmlMapper();
        JsonNode result = mapper.readTree(content.getContent());
        return null;
    }
}