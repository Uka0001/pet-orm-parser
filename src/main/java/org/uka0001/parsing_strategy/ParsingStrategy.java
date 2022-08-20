package org.uka0001.parsing_strategy;

import org.uka0001.read_write_sourse.DataReadWriteSource;
import org.uka0001.ORM;

public interface ParsingStrategy<T extends DataReadWriteSource> {
    ORM.Table parseToTable(T content);
}
