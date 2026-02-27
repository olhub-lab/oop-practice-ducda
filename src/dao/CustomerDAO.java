package dao;

import database.JDBCUtil;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Customer;

public class CustomerDAO implements DAOInterface<Customer> {

  @Override
  public int insert(Customer customer) {
    int result = 0;
    try {
      Connection connection = JDBCUtil.getConnection();

      String query = "INSERT INTO customer (idCustomer, name, address, phoneNumber,balance) VALUES (?, ?, ?, ?, ?)";

      PreparedStatement preparedStatement = connection.prepareStatement(query);

      preparedStatement.setInt(1, customer.getIdCustomer());
      preparedStatement.setString(2, customer.getName());
      preparedStatement.setString(3, customer.getAddress());
      preparedStatement.setString(4, customer.getPhoneNumber());
      preparedStatement.setBigDecimal(5, customer.getBalance());

      result = preparedStatement.executeUpdate();

      JDBCUtil.closeConnection(connection);


    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  @Override
  public int update(Customer customer) {

    int result = 0;

    try {
      Connection connection = JDBCUtil.getConnection();

      String query = "UPDATE customer " +
          "SET " +
          "name=?," +
          "address=?," +
          "phoneNumber=?," +
          "balance=? " +
          "WHERE idCustomer=?";

      PreparedStatement preparedStatement = connection.prepareStatement(query);

      preparedStatement.setString(1, customer.getName());
      preparedStatement.setString(2, customer.getAddress());
      preparedStatement.setString(3, customer.getPhoneNumber());
      preparedStatement.setBigDecimal(4, customer.getBalance());
      preparedStatement.setInt(5, customer.getIdCustomer());

      result = preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  @Override
  public int delete(Customer customer) {
    return 0;
  }

  @Override
  public ArrayList<Customer> selectAll() {
    ArrayList<Customer> customers = new ArrayList<>();

    try {
      Connection connection = JDBCUtil.getConnection();

      String query = "SELECT * FROM customer";

      PreparedStatement preparedStatement = connection.prepareStatement(query);

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        int idCustomer = resultSet.getInt("idCustomer");
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        String phoneNumber = resultSet.getString("phoneNumber");
        BigDecimal balance = resultSet.getBigDecimal("balance");

        Customer customer = new Customer(idCustomer, name, address, phoneNumber, balance);

        customers.add(customer);
      }

      JDBCUtil.closeConnection(connection);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customers;
  }

  public Customer sellectByID(Connection connection, int idCustomer) {
    Customer customer = null;

    try {
      String query = "SELECT * FROM customer WHERE idCustomer = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, idCustomer);

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        customer = new Customer();
        customer.setIdCustomer(resultSet.getInt("idCustomer"));
        customer.setName(resultSet.getString("name"));
        customer.setAddress(resultSet.getString("address"));
        customer.setPhoneNumber(resultSet.getString("phoneNumber"));
        customer.setBalance(resultSet.getBigDecimal("balance"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customer;
  }
}
