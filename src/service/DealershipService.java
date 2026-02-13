
package service;

import dao.CustomerDAO;
import dao.VehicleDAO;
import database.JDBCUtil;
import exceptions.CustomerNotFoundException;
import exceptions.InsufficientBalanceException;
import exceptions.OutOfStockException;
import exceptions.VehicleNotFoundException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import model.Customer;
import model.Dealership;
import model.Vehicle;
import view.DealershipView;

public class DealershipService {

  private VehicleDAO vehicleDAO = new VehicleDAO();

  private CustomerDAO customerDAO = new CustomerDAO();

  public DealershipService(Dealership dealership) {
  }

  public void createVehicle(
      int idVehicle,
      String type,
      String model,
      String manufacturer,
      int year,
      BigDecimal basePrice,
      String origin,
      int quantity,
      DealershipView view
  ) {

    Vehicle vehicle = null;

    if (type.equalsIgnoreCase("car")) {

      view.show("Seat: ");
      int seat = view.inputInt();

      view.show("Fuel: ");
      String fuel = view.inputString();

      view.show("Engine capacity: ");
      int engineCapacity = view.inputInt();

      view.show("Body type: ");
      String bodyType = view.inputString();

      vehicle = VehicleFactory.createCar(idVehicle,
          model, manufacturer, year, basePrice,
          origin, quantity, type,
          seat, fuel, engineCapacity, bodyType
      );

    } else if (type.equalsIgnoreCase("motorbike")) {

      view.show("Engine capacity: ");
      int engineCapacity = view.inputInt();

      view.show("Motorbike type: ");
      String typeMotorbike = view.inputString();

      view.show("Power: ");
      String power = view.inputString();

      vehicle = VehicleFactory.createMotorbike(idVehicle,
          model, manufacturer, year, basePrice,
          origin, quantity, type,
          engineCapacity, typeMotorbike, power
      );

    } else if (type.equalsIgnoreCase("bike")) {

      view.show("Bicycle type: ");
      String typeBicycle = view.inputString();

      view.show("Frame material: ");
      String frameMaterial = view.inputString();

      vehicle = VehicleFactory.createBicycle(idVehicle,
          model, manufacturer, year, basePrice,
          origin, quantity, type,
          frameMaterial, typeBicycle
      );
    }

    if (vehicle == null) {
      throw new IllegalArgumentException("Invalid vehicle type");
    }

    vehicleDAO.insert(vehicle);
  }

  public void createCustomer(
      int idCustomer,
      String name,
      String address,
      String phoneNumber,
      BigDecimal balance
  ) {
    Customer customer = new Customer(idCustomer, name, address, phoneNumber, balance);
    customerDAO.insert(customer);
  }

  public ArrayList<Vehicle> getAllVehicles() {
    return vehicleDAO.selectAll();
  }

  public ArrayList<Customer> getAllCustomers() {
    return customerDAO.selectAll();
  }


//  public void buyVehicle(int idCustomer, int idVehicle) {
//
//    Connection connection = null;
//
//    try {
//      connection = JDBCUtil.getConnection();
//
//      connection.setAutoCommit(false);
//
//      Customer customer = customerDAO.sellectByID(connection, idCustomer);
//      if (customer == null) {
//        throw new CustomerNotFoundException("Customer not found");
//      }
//      Vehicle vehicle = vehicleDAO.sellectByID(connection, idVehicle);
//      if (vehicle == null) {
//        throw new VehicleNotFoundException("Vehicle not found");
//      }
//      if (vehicle.getQuantity() <= 0) {
//        throw new OutOfStockException("Vehicle out of stock");
//      }
//      if (customer.getBalance().compareTo(vehicle.getBasePrice()) < 0) {
//        throw new InsufficientBalanceException("Not enough balance");
//      }
//
//      customerDAO.decreaseBalance(connection, idCustomer, vehicle.getBasePrice());
//
//      vehicleDAO.decreaseQuantity(connection, idVehicle);
//
//      connection.commit();
//
//    } catch (SQLException e) {
//      if (connection != null) {
//        try {
//          connection.rollback();
//        } catch (SQLException ex) {
//          ex.printStackTrace();
//        }
//      }
//    }
//    finally {
//      JDBCUtil.closeConnection(connection);
//    }
//  }

//  public ArrayList<Vehicle> suggestAlternatives(String type) {
//    return dealership.suggestAlternative(type);
//  }
}
