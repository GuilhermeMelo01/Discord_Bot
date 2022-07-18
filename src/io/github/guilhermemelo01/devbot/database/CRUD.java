package io.github.guilhermemelo01.devbot.database;

import io.github.guilhermemelo01.devbot.main.BotDiscord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD {


    public static void select(String guild_id) throws SQLException {
        String sql = """
                Select * from tb_guild where id_guild = %s
                """.formatted(guild_id);

        Statement statement = ConnectinoFactory.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            BotDiscord.prefixMap.put(guild_id, resultSet.getString("prefix").charAt(0));
        }
        statement.close();
        resultSet.close();
        ConnectinoFactory.getConnection().close();
    }

    public static void insert(String guild_id, char prefix) throws SQLException {
        String sql = """
                insert or ignore into tb_guild values (null, '%s', '%s')
                """.formatted(guild_id, prefix);

        Statement statement = ConnectinoFactory.getConnection().createStatement();
        statement.execute(sql);
        statement.close();
        ConnectinoFactory.getConnection().close();
    }

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
