package springboot01.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtil {

    public static final String URL = "jdbc:mysql://localhost:3306/imooc";
    public static final String USER = "CDY";
    public static final String PASSWORD = "123456";

    public static Connection getConnection() {
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            //2. 获得数据库连接
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } catch (Exception e) {
            return null;
        }
    }

    public static void release(PreparedStatement preparedStatement, Connection connection) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
