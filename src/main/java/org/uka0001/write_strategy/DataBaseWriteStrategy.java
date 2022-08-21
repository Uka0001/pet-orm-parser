package org.uka0001.write_strategy;

import lombok.AllArgsConstructor;
import org.uka0001.DBService;
import org.uka0001.persons.Person;

import java.util.List;

@AllArgsConstructor
public class DataBaseWriteStrategy implements WriteStrategy {
    @Override
    public void write(List<?> list) {
        DBService dbService = new DBService();
        if (list.get(0) instanceof Person) {
            for (Object each : list) {
                dbService.addPerson((Person) each);
            }
        }

    }
}
