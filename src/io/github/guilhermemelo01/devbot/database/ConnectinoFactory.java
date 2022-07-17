package io.github.guilhermemelo01.devbot.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectinoFactory {

    public static Connection getConnection(){
        String url = "jdbc:sqlite:C:/Users/User/bot_project/sqllite/devbot/devbot.db";

        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
