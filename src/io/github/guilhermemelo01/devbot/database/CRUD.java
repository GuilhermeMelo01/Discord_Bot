package io.github.guilhermemelo01.devbot.database;

import io.github.guilhermemelo01.devbot.main.BotDiscord;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CRUD {

    public static void createTable() throws SQLException {
        String sql = """
                create table tb_guild\s
                (
                    id integer not null primary key autoincrement unique,
                    id_guild text not null unique,
                    prefix text not null
                )""";

        PreparedStatement statement = Objects.requireNonNull(ConnectinoFactory.getConnection()).prepareStatement(sql);
        statement.execute();

        statement.close();
        ConnectinoFactory.getConnection().close();
    }

    public static void insert(String guild_id, char prefix) throws SQLException {
        String sql = """
                insert or ignore into tb_guild values (null, ?, ?)
                """;

        PreparedStatement statement = Objects.requireNonNull(ConnectinoFactory.getConnection()).prepareStatement(sql);
        statement.setString(1, guild_id);
        statement.setString(2, String.valueOf(prefix));
        statement.execute();

        statement.close();
        ConnectinoFactory.getConnection().close();
    }

    public static void select(String guild_id) throws SQLException {
        String sql = """
                Select * from tb_guild where id_guild = ?
                """;

        PreparedStatement statement = Objects.requireNonNull(ConnectinoFactory.getConnection()).prepareStatement(sql);
        statement.setString(1, guild_id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            BotDiscord.prefixMap.put(guild_id, resultSet.getString("prefix").charAt(0));
        }
        statement.close();
        resultSet.close();
        ConnectinoFactory.getConnection().close();
    }

    public static void update(String id_guild, char newPrefix) throws SQLException {

        String sql = """
                update tb_guild set prefix = ? where id_guild = ?
                """;

        PreparedStatement statement = Objects.requireNonNull(ConnectinoFactory.getConnection()).prepareStatement(sql);

        statement.setString(1, String.valueOf(newPrefix));
        statement.setString(2, String.valueOf(id_guild));
        statement.executeUpdate();

        statement.close();
        ConnectinoFactory.getConnection().close();
    }


}
