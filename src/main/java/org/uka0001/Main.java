package org.uka0001;

import lombok.SneakyThrows;
import org.uka0001.persons.Person;
import org.uka0001.read_write_sourse.ConnectionReadWriteSource;
import org.uka0001.read_write_sourse.DataReadWriteSource;
import org.uka0001.read_write_sourse.FileReadWriteSource;

import java.io.File;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

public class Main {

    private static final ORMInterface ORM = new ORM();

    public static void main(String[] args) throws Exception {
        URL url1 = Main.class.getClassLoader().getResource("sample.json");
        URL url2 = Main.class.getClassLoader().getResource("sample.xml");
        URL url3 = Main.class.getClassLoader().getResource("sample.csv");

        URL url4 = Main.class.getClassLoader().getResource("sample.xml");


        DataReadWriteSource<?> file = new FileReadWriteSource(new File(url4.toURI()));
        List<Person> list = ORM.readAll(file,Person.class);

        DataReadWriteSource<?> target1 = new FileReadWriteSource(new File(url3.toURI()));
        DataReadWriteSource<?> target2 = new FileReadWriteSource(new File(url2.toURI()));
        DataReadWriteSource<?> target3 = new FileReadWriteSource(new File(url1.toURI()));
        if (!list.isEmpty()){
            ORM.writeAll(target1, list);
            ORM.writeAll(target2, list);
            ORM.writeAll(target3, list);
        }

        DBService dbService = new DBService();
        process(dbService.connection());

//        withConnection(connection -> {
//            process(connection);
//            return null;
//        });
    }

    @SneakyThrows
    public static void process(Connection connection) {
        URL url5 = Main.class.getClassLoader().getResource("MOCK_DATA.json");

        List<Person> result;
        DataReadWriteSource<ResultSet> rw = new ConnectionReadWriteSource(connection, "person");
        result = ORM.readAll(rw, Person.class);

        DataReadWriteSource<?> file = new FileReadWriteSource(new File(url5.toURI()));
        List<Person> list = ORM.readAll(file,Person.class);

        ORM.writeAll(rw, list);

    }
//    @SneakyThrows
//    private static void process(Connection connection) {
//        URL url = Main.class.getClassLoader().getResource("sample.json");
//
//        List<Person> result;
//        // result = ORM.readAll(new FileReadWriteSource(new File(url.toURI())), Person.class);
//        // result.add(new Person("WRITE", BigInteger.ZERO, BigInteger.ZERO, "WRITE", LocalDate.now(), 0F));
//        // ORM.writeAll(new ORMInterface.FileReadWriteSource(new File(url.toURI())), result);
//
//        DataReadWriteSource<ResultSet> rw = new ConnectionReadWriteSource(connection, "person");
//        result = ORM.readAll(rw, Person.class);
//        result.add(new Person("WRITE", BigInteger.ZERO, BigInteger.ZERO, "WRITE", LocalDate.now(), 0F));
//        //ORM.writeAll(rw, result);
//    }

//    @SneakyThrows
//    private static void withConnection(Function<Connection, Void> function) {
//        try (Connection с = DriverManager.getConnection("jdbc:sqlite:sample.db")) {
//            try (Statement stmt = с.createStatement()) {
//                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS person " +
//                        "(id INTEGER not NULL, " +
//                        " name VARCHAR(255), " +
//                        " position VARCHAR(255), " +
//                        " age INTEGER, " +
//                        " PRIMARY KEY ( id ))");
//
//                stmt.executeUpdate("DELETE FROM person");
//                for (int index = 0; index < 10; index++) {
//                    stmt.executeUpdate("INSERT INTO person (name, position, age) VALUES ('1', '1', 1)");
//                }
//                System.out.println("Connection to bd has done successfully, bd is created.");
//            }
//            function.apply(с);
//        }
    }