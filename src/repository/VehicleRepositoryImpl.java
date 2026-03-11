package repository;

import constant.VehicleSqlConstant;
import constant.VehicleTypeConstant;
import database.DBConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.Bicycle;
import model.Car;
import model.Motorbike;
import model.Vehicle;
import service.VehicleFactory;
import util.DatabaseUtil;

public class VehicleRepositoryImpl implements RepositoryInterface<Vehicle> {

  @Override
  public int insert(Vehicle vehicle) {
    int result = 0;
    Connection connection = null;
    try {
      connection = DBConnection.getInstance().getConnection();
      connection.setAutoCommit(false);

      try (PreparedStatement preparedStatement = connection.prepareStatement(
          VehicleSqlConstant.INSERT_VEHICLE)) {

        preparedStatement.setInt(1, vehicle.getId());
        preparedStatement.setString(2, vehicle.getModel());
        preparedStatement.setString(3, vehicle.getManufacturer());
        preparedStatement.setInt(4, vehicle.getYear());
        preparedStatement.setBigDecimal(5, vehicle.getBasePrice());
        preparedStatement.setString(6, vehicle.getOrigin());
        preparedStatement.setInt(7, vehicle.getQuantity());
        preparedStatement.setString(8, vehicle.getType());

        if (VehicleTypeConstant.BICYCLE.equals(vehicle.getType())) {
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
        } else if (VehicleTypeConstant.CAR.equals(vehicle.getType())) {
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

        } else if (VehicleTypeConstant.MOTORBIKE.equals(vehicle.getType())) {
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

        if (result > 0) {
          connection.commit();
        } else {
          DatabaseUtil.rollback(connection);
        }
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
  public int update(Vehicle vehicle) {
    int result = 0;
    Connection connection = null;
    try {
      connection = DBConnection.getInstance().getConnection();

      connection.setAutoCommit(false);

      try (PreparedStatement preparedStatement = connection.prepareStatement(
          VehicleSqlConstant.UPDATE_VEHICLE)) {
        preparedStatement.setString(1, vehicle.getModel());
        preparedStatement.setString(2, vehicle.getManufacturer());
        preparedStatement.setInt(3, vehicle.getYear());
        preparedStatement.setBigDecimal(4, vehicle.getBasePrice());
        preparedStatement.setString(5, vehicle.getOrigin());
        preparedStatement.setInt(6, vehicle.getQuantity());
        preparedStatement.setString(7, vehicle.getType());
        preparedStatement.setInt(8, vehicle.getId());

        result = preparedStatement.executeUpdate();
        if (result > 0) {
          connection.commit();
        } else {
          DatabaseUtil.rollback(connection);
        }

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
  public int delete(Vehicle object) {
    return 0;
  }

  @Override
  public List<Vehicle> selectAll() {
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    Connection connection = null;

    try {
      connection = DBConnection.getInstance().getConnection();
      connection.setAutoCommit(false);

      try (PreparedStatement preparedStatement = connection.prepareStatement(
          VehicleSqlConstant.SELECT_ALL_VEHICLE)) {
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
          int id = resultSet.getInt("id");
          String model = resultSet.getString("model");
          String manufacturer = resultSet.getString("manufacturer");
          int year = resultSet.getInt("year");
          BigDecimal basePrice = resultSet.getBigDecimal("base_price");
          String origin = resultSet.getString("origin");
          int quantity = resultSet.getInt("quantity");
          String type = resultSet.getString("type");

          String typeBicycle = resultSet.getString("type_bicycle");
          String frameMaterial = resultSet.getString("frame_material");

          int seat = resultSet.getInt("seat");
          String fuel = resultSet.getString("fuel");
          int engineCapacityCar = resultSet.getInt("engine_capacity_car");
          String bodyType = resultSet.getString("body_type");

          int engineCapacityMotorbike = resultSet.getInt("engine_capacity_motorbike");
          String motorbikeType = resultSet.getString("type_motorbike");
          String power = resultSet.getString("power");

          Vehicle vehicle = null;

          switch (type.toLowerCase()) {
            case VehicleTypeConstant.CAR:
              vehicle = VehicleFactory.createCar(id, model, manufacturer, year, basePrice,
                  origin, quantity, type,
                  seat, fuel, engineCapacityCar, bodyType);
              break;
            case VehicleTypeConstant.BICYCLE:
              vehicle = VehicleFactory.createBicycle(id, model, manufacturer, year, basePrice,
                  origin, quantity, type,
                  typeBicycle, frameMaterial);
              break;
            case VehicleTypeConstant.MOTORBIKE:
              vehicle = VehicleFactory.createMotorbike(id, model, manufacturer, year,
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
      }
    } catch (SQLException e) {
      DatabaseUtil.rollback(connection);
      e.printStackTrace();
    } finally {
      DatabaseUtil.closeConnection(connection);
    }
    return vehicles;
  }
}
