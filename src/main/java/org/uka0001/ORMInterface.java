package org.uka0001;

import lombok.SneakyThrows;
import org.uka0001.persons.Person;
import org.uka0001.read_write_sourse.DataReadWriteSource;

import java.sql.ResultSet;
import java.util.List;

public interface ORMInterface {

    @SneakyThrows
    <T> List<T> readAll(DataReadWriteSource<?> source, Class<T> cls);


    default <T> void writeAll(DataReadWriteSource target, List<T> object) {

    }
}
