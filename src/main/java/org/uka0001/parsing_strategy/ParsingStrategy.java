package org.uka0001.parsing_strategy;

import org.uka0001.ORM;
import org.uka0001.read_write_sourse.DataReadWriteSource;

public interface ParsingStrategy<T extends DataReadWriteSource> {
    ORM.Table parseToTable(T content);
}
