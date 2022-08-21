package org.uka0001.parsing_strategy;


import org.uka0001.Table;
import org.uka0001.read_write_sourse.DataReadWriteSource;

public interface ParsingStrategy<T extends DataReadWriteSource> {
    Table parseToTable(T content);
}
