package repository;

import constant.CustomerEntityConstant;
import constant.CustomerSqlConstant;
import database.DBConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import util.DatabaseUtil;

public class CustomerRepositoryImpl implements RepositoryInterface<Customer> {


  @Override
  public int insert(Customer customer) {
    int result = 0;

    Connection connection = null;
    try {
      connection = DBConnection.getInstance().getConnection();

      connection.setAutoCommit(false);

      try (PreparedStatement preparedStatement = connection.prepareStatement(
          CustomerSqlConstant.INSERT_CUSTOMER)) {
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
  public int update(Customer customer) {
    int result = 0;
    Connection connection = null;

    try {
      connection = DBConnection.getInstance().getConnection();

      connection.setAutoCommit(false);

      try (PreparedStatement preparedStatement = connection.prepareStatement(
          CustomerSqlConstant.UPDATE_CUSTOMER)) {

        int index = 1;

        preparedStatement.setString(index++, customer.getName());
        preparedStatement.setString(index++, customer.getAddress());
        preparedStatement.setString(index++, customer.getPhoneNumber());
        preparedStatement.setBigDecimal(index++, customer.getBalance());
        preparedStatement.setInt(index++, customer.getId());

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
  public int delete(Customer object) {
    return 0;
  }

  @Override
  public List<Customer> selectAll() {
    List<Customer> customers = new ArrayList<>();
    Connection connection = null;

    try {
      connection = DBConnection.getInstance().getConnection();
      connection.setAutoCommit(false);

      try (PreparedStatement preparedStatement = connection.prepareStatement(
          CustomerSqlConstant.SELECT_ALL_CUSTOMER);
          ResultSet resultSet = preparedStatement.executeQuery()) {

        while (resultSet.next()) {
          int id = resultSet.getInt(CustomerEntityConstant.COLUMN_ID);
          String name = resultSet.getString(CustomerEntityConstant.COLUMN_NAME);
          String address = resultSet.getString(CustomerEntityConstant.COLUMN_ADDRESS);
          String phoneNumber = resultSet.getString(CustomerEntityConstant.COLUMN_PHONE_NUMBER);
          BigDecimal balance = resultSet.getBigDecimal(CustomerEntityConstant.COLUMN_BALANCE);

          Customer customer = new Customer(id, name, address, phoneNumber, balance);

          customers.add(customer);
        }
        connection.commit();

      }
    } catch (SQLException e) {
      DatabaseUtil.rollback(connection);

      e.printStackTrace();
    } finally {
      DatabaseUtil.closeConnection(connection);
    }
    return customers;
  }
}
