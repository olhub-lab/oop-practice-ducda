package database;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

  public static Connection getConnection() {
    Connection connection = null;
    try {

      DriverManager.registerDriver(new Driver());

      String url = "jdbc:mysql://localhost:3306/vehicle_management";
      String user = "root";
      String password = "dinhanhduc2006";

      return DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  public static void closeConnection(Connection connection) {
    try {
      if (connection != null) {
        connection.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
