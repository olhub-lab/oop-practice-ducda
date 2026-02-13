package database;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
  public static Connection getConnection() {
    Connection conn = null;

    try {

      DriverManager.registerDriver(new Driver());

      String url = "jdbc:mysql://localhost:3306/vehicle_management";
      String user = "root";
      String password = "dinhanhduc2006";

      conn = DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return conn;
  }

  public static void closeConnection(Connection conn) {
    try {
      if (conn != null) {
        conn.close();
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
  }
}
