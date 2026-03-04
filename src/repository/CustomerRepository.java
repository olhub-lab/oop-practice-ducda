package repository;

import constant.CustomerConstant;
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

      String query =
          "INSERT INTO " + CustomerConstant.TABLE_NAME + "(" + CustomerConstant.COLUMN_ID + ","
              + CustomerConstant.COLUMN_NAME + ","
              + CustomerConstant.COLUMN_ADDRESS + ","
              + CustomerConstant.COLUMN_PHONE_NUMBER + ","
              + CustomerConstant.COLUMN_BALANCE + ") VALUES(?,?,?,?,?)";
      try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        int index = 1;

        preparedStatement.setInt(index++, customer.getId());
        preparedStatement.setString(index++, customer.getName());
        preparedStatement.setString(index++, customer.getAddress());
        preparedStatement.setString(index++, customer.getPhoneNumber());
        preparedStatement.setBigDecimal(index++, customer.getBalance());

        result = preparedStatement.executeUpdate();
      }
      if (result > 0) {
        connection.commit();
      } else {
        DatabaseUtil.rollback(connection);
      }
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
