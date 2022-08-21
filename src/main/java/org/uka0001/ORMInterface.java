package org.uka0001;

import org.uka0001.read_write_sourse.DataReadWriteSource;

import java.util.List;

public interface ORMInterface {

    <T> List<T> readAll(DataReadWriteSource<?> source, Class<T> cls);

    default <T> void writeAll(DataReadWriteSource target, List<T> object) {

    }
}
