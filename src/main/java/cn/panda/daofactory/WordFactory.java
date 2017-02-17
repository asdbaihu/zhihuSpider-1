package cn.panda.daofactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lingj on 2017/2/16 0016.
 */
public class WordFactory {

    private static Connection connection;

    private String DRIVER = "com.mysql.jdbc.Driver";
    private String URL = "jdbc:mysql://localhost:3306/houses?useUnicode=true&amp;characterEncoding=UTF-8";
    private String USERNAME = "root";
    private String PASSWORD = "123456";

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        if (null == connection || connection.isClosed()) {
            Class.forName(DRIVER);
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        } else {
            return connection;
        }
    }

}
