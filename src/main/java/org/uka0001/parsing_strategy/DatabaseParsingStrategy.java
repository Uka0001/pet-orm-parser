package org.uka0001.parsing_strategy;

import lombok.SneakyThrows;
import org.uka0001.ConnectionReadWriteSource;
import org.uka0001.ORM;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.LinkedHashMap;
import java.util.Map;

//Parsing data from content (database) from ConnectionReadWriteSource
public class DatabaseParsingStrategy implements ParsingStrategy<ConnectionReadWriteSource> {

    //Parsing data from content (database) from ConnectionReadWriteSource
    @Override
    public ORM.Table parseToTable(ConnectionReadWriteSource content) {
        ResultSet rs = content.getContent();
        Map<Integer, Map<String, String>> result = buildTable(rs);
        return new ORM.Table(result);
    }

    @SneakyThrows
    private Map<Integer, Map<String, String>> buildTable(ResultSet rs) {
        ResultSetMetaData metadata = rs.getMetaData();

        Map<Integer, Map<String, String>> result = new LinkedHashMap<>();
        int rowId = 0;
        while (rs.next()) {
            Map<String, String> row = new LinkedHashMap<>();
            for (int index = 1; index < metadata.getColumnCount(); index++) {
                row.put(metadata.getColumnName(index), rs.getString(index));
            }
            result.put(rowId, row);
            rowId++;
        }

        return result;
    }
}
