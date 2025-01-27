package com.revature.planetarium.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import org.sqlite.SQLiteConfig;

public class DatabaseConnector {

    public static Connection getConnection() throws SQLException {
//        SQLiteConfig config = new SQLiteConfig();
//        config.enforceForeignKeys(true);
//        String url = AppConfig.DATABASE_URL;
//        return DriverManager.getConnection(url, config.toProperties());
        return DriverManager.getConnection("jdbc:postgresql://trng-2121-2.chueiwozbnfz.us-east-1.rds.amazonaws.com:5432/postgres",
                                            "postgres",
                                            "trng_rds_2121");
    }

}
