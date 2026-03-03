package database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {

  private static DBConnection instance;

  private HikariDataSource dataSource;

  private DBConnection() {
    HikariConfig config = new HikariConfig();

    config.setJdbcUrl("jdbc:mysql://localhost:3306/vehiclemanagement");
    config.setUsername("root");
    config.setPassword("dinhanhduc2006");

    config.setMaximumPoolSize(10);
    config.setMinimumIdle(5);
    config.setIdleTimeout(60000);
    config.setConnectionTimeout(40000);
    config.setMaxLifetime(1800000);

    dataSource = new HikariDataSource(config);

    System.out.println("Connected to database");
  }

  public static DBConnection getInstance() {
    if (instance == null) {
      instance = new DBConnection();
    }
    return instance;
  }

  public Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }
}
