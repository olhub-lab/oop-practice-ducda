package repository;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Customer;
import util.DatabaseUtil;

public class CustomerRepository implements RepositoryInterface<Customer> {


  @Override
  public int insert(Customer customer) {
    int result = 0;

    Connection connection = null;
    try {
      connection = DBConnection.getInstance().getConnection();

      connection.setAutoCommit(false);

      String query = "INSERT INTO customer (idCustomer, name, address, phoneNumber, balance) VALUES (?, ?, ?, ?, ?)";

      try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, customer.getIdCustomer());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getAddress());
        preparedStatement.setString(4, customer.getPhoneNumber());
        preparedStatement.setBigDecimal(5, customer.getBalance());

        result = preparedStatement.executeUpdate();
      }

      connection.commit();
    } catch (SQLException e) {
      DatabaseUtil.rollback(connection);

      e.printStackTrace();
    } finally {
      DatabaseUtil.closeConnection(connection);
    }
    return result;
  }

  @Override
  public int update(Customer object) {
    return 0;
  }

  @Override
  public int delete(Customer object) {
    return 0;
  }

  @Override
  public ArrayList<Customer> selectAll() {
    return null;
  }
}
