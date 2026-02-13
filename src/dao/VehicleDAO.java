package dao;

import database.JDBCUtil;
import java.awt.desktop.PreferencesEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import model.Bicycle;
import model.Car;
import model.Customer;
import model.Motorbike;
import model.Vehicle;
import service.VehicleFactory;

public class VehicleDAO implements DAOInterface<Vehicle> {

  @Override
  public int insert(Vehicle vehicle) {
    int result = 0;
    try {
      Connection connection = JDBCUtil.getConnection();

//      Statement statement = connection.createStatement();
//
//      String query = "INSERT INTO vehicle (idVehicle, model,manufacturer,year,basePrice,origin,quantity,type)  " +
//                      "VALUES ('"+vehicle.getIdVehicle()+" ','"+vehicle.getModel()+" ' ,"
//                      + ""+vehicle.getManufacturer()+","+vehicle.getYear()+","
//                      + ""+vehicle.getBasePrice()+","+vehicle.getOrigin()+","
//                      + ""+vehicle.getQuantity()+",'"+vehicle.getType()+"')";

      String query =
          "INSERT INTO vehicle (idVehicle, model,manufacturer,year,basePrice,"
              + "origin,quantity,type,typeBicycle,frameMaterial,"
              + "seat,fuel,engineCapacityCar,bodyType,engineCapacityMotorbike,typeMotorbike,power )"
              +
              "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

      PreparedStatement preparedStatement = connection.prepareStatement(query);

      preparedStatement.setInt(1, vehicle.getIdVehicle());
      preparedStatement.setString(2, vehicle.getModel());
      preparedStatement.setString(3, vehicle.getManufacturer());
      preparedStatement.setInt(4, vehicle.getYear());
      preparedStatement.setBigDecimal(5, vehicle.getBasePrice());
      preparedStatement.setString(6, vehicle.getOrigin());
      preparedStatement.setInt(7, vehicle.getQuantity());
      preparedStatement.setString(8, vehicle.getType());

      if (vehicle.getType().equals("Bicycle")) {
        Bicycle bicycle = (Bicycle) vehicle;

        preparedStatement.setString(9, bicycle.getTypeBicycle());
        preparedStatement.setString(10, bicycle.getFrameMaterial());

        preparedStatement.setNull(11, Types.INTEGER);
        preparedStatement.setNull(12, Types.VARCHAR);
        preparedStatement.setNull(13, Types.INTEGER);
        preparedStatement.setNull(14, Types.VARCHAR);

        preparedStatement.setNull(15, Types.INTEGER);
        preparedStatement.setNull(16, Types.VARCHAR);
        preparedStatement.setNull(17, Types.INTEGER);
      } else if (vehicle.getType().equals("Car")) {
        Car car = (Car) vehicle;

        preparedStatement.setNull(9, Types.VARCHAR);
        preparedStatement.setNull(10, Types.VARCHAR);

        preparedStatement.setInt(11, car.getSeat());
        preparedStatement.setString(12, car.getFuel());
        preparedStatement.setInt(13, car.getEngineCapacity());
        preparedStatement.setString(14, car.getBodyType());

        preparedStatement.setNull(15, Types.INTEGER);
        preparedStatement.setNull(16, Types.VARCHAR);
        preparedStatement.setNull(17, Types.INTEGER);

      } else if (vehicle.getType().equals("Motorbike")) {
        Motorbike motorbike = (Motorbike) vehicle;

        preparedStatement.setNull(9, Types.VARCHAR);
        preparedStatement.setNull(10, Types.VARCHAR);

        preparedStatement.setNull(11, Types.INTEGER);
        preparedStatement.setNull(12, Types.VARCHAR);
        preparedStatement.setNull(13, Types.INTEGER);
        preparedStatement.setNull(14, Types.VARCHAR);

        preparedStatement.setInt(15, motorbike.getEngineCapacity());
        preparedStatement.setString(16, motorbike.getTypeMotorbike());
        preparedStatement.setString(17, motorbike.getPower());
      }

      result = preparedStatement.executeUpdate();

      JDBCUtil.closeConnection(connection);

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  @Override
  public int update(Vehicle vehicle) {
    int result = 0;
    try {
      Connection connection = JDBCUtil.getConnection();

      String query = "UPDATE vehicle " +
          "SET " +
//                    "idVehicle=?"+
          "model=?" +
          ",manufacturer=?" +
          ",year=?" +
          ",basePrice=?" +
          ",origin=?" +
          ",quantity=?" +
          ",type=?" +
          " WHERE idVehicle=?";

      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, vehicle.getModel());
      preparedStatement.setString(2, vehicle.getManufacturer());
      preparedStatement.setInt(3, vehicle.getYear());
      preparedStatement.setBigDecimal(4, vehicle.getBasePrice());
      preparedStatement.setString(5, vehicle.getOrigin());
      preparedStatement.setInt(6, vehicle.getQuantity());
      preparedStatement.setString(7, vehicle.getType());
      preparedStatement.setInt(8, vehicle.getIdVehicle());

      result = preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  @Override
  public int delete(Vehicle vehicle) {
    int result = 0;

    try {
      Connection connection = JDBCUtil.getConnection();
      String query = "DELETE FROM vehicle WHERE idVehicle=?";

      PreparedStatement preparedStatement = connection.prepareStatement(query);

      preparedStatement.setInt(1, vehicle.getIdVehicle());

      result = preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  @Override
  public ArrayList<Vehicle> selectAll() {
    ArrayList<Vehicle> vehicles = new ArrayList<>();

    try {
      Connection connection = JDBCUtil.getConnection();

      String query = "SELECT * FROM vehicle";

      PreparedStatement preparedStatement = connection.prepareStatement(query);

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        int idVehicle = resultSet.getInt("idVehicle");
        String model = resultSet.getString("model");
        String manufacturer = resultSet.getString("manufacturer");
        int year = resultSet.getInt("year");
        BigDecimal basePrice = resultSet.getBigDecimal("basePrice");
        String origin = resultSet.getString("origin");
        int quantity = resultSet.getInt("quantity");
        String type = resultSet.getString("type");

        String typeBicycle = resultSet.getString("typeBicycle");
        String frameMaterial = resultSet.getString("frameMaterial");

        int seat = resultSet.getInt("seat");
        String fuel = resultSet.getString("fuel");
        int engineCapacityCar = resultSet.getInt("engineCapacityCar");
        String bodyType = resultSet.getString("bodyType");

        int engineCapacityMotorbike = resultSet.getInt("engineCapacityMotorbike");
        String motorbikeType = resultSet.getString("typeMotorbike");
        String power = resultSet.getString("power");

        Vehicle vehicle = null;

        switch (type.toLowerCase()) {
          case "car":
            vehicle = VehicleFactory.createCar(idVehicle, model, manufacturer, year, basePrice,
                origin, quantity, type,
                seat, fuel, engineCapacityCar, bodyType);
            break;
          case "bicycle":
            vehicle = VehicleFactory.createBicycle(idVehicle, model, manufacturer, year, basePrice,
                origin, quantity, type,
                frameMaterial, typeBicycle);
            break;
          case "motorbike":
            vehicle = VehicleFactory.createMotorbike(idVehicle, model, manufacturer, year,
                basePrice, origin, quantity, type,
                engineCapacityMotorbike, motorbikeType, power);
            break;
          default:
            vehicle = null;
        }

        if (vehicle != null) {
          vehicles.add(vehicle);
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return vehicles;
  }

  public Vehicle sellectByID(Connection connection, int idVehicle) {
    Vehicle vehicle = null;

    try {
      String query = "SELECT * FROM vehicle WHERE idVehicle=?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, idVehicle);

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        String type = resultSet.getString("type");
        switch (type.toLowerCase()) {
          case "car":
            vehicle = new Car();
            break;
          case "bicycle":
            vehicle = new Bicycle();
            break;
          case "motorbike":
            vehicle = new Motorbike();
            break;
          default:
            vehicle = null;
        }
        vehicle.setIdVehicle(resultSet.getInt("idVehicle"));
        vehicle.setQuantity(resultSet.getInt("quantity"));

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return vehicle;
  }

  public void decreaseQuantity(Connection connection, int idVehicle) {
    try {
      String sql = "UPDATE vehicle SET quantity = quantity - 1 WHERE idVehicle=?";

      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, idVehicle);

      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
