package io.github.guilhermemelo01.devbot.database;

import java.sql.SQLException;
import java.sql.Statement;

public class CRUD {

    public static void createTable() throws SQLException {
        String sql = """
                create table tb_guild\s
                (
                    id integer not null primary key autoincrement unique,
                    id_guild text not null unique,
                    prefix text not null
                )""";

        Statement statement = ConnectinoFactory.getConnection().createStatement();
        statement.execute(sql);

        statement.close();
        ConnectinoFactory.getConnection().close();
    }
}
