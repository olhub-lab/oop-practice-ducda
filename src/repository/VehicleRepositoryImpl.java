package repository;

import constant.CommonConstant;
import constant.VehicleEntityConstant;
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
        int index = CommonConstant.INDEX;

        preparedStatement.setInt(index++, vehicle.getId());
        preparedStatement.setString(index++, vehicle.getModel());
        preparedStatement.setString(index++, vehicle.getManufacturer());
        preparedStatement.setInt(index++, vehicle.getYear());
        preparedStatement.setBigDecimal(index++, vehicle.getBasePrice());
        preparedStatement.setString(index++, vehicle.getOrigin());
        preparedStatement.setInt(index++, vehicle.getQuantity());
        preparedStatement.setString(index++, vehicle.getType());

        if (VehicleTypeConstant.BICYCLE.equals(vehicle.getType())) {
          Bicycle bicycle = (Bicycle) vehicle;

          preparedStatement.setString(index++, bicycle.getTypeBicycle());
          preparedStatement.setString(index++, bicycle.getFrameMaterial());

          preparedStatement.setNull(index++, Types.INTEGER);
          preparedStatement.setNull(index++, Types.VARCHAR);
          preparedStatement.setNull(index++, Types.INTEGER);
          preparedStatement.setNull(index++, Types.VARCHAR);

          preparedStatement.setNull(index++, Types.INTEGER);
          preparedStatement.setNull(index++, Types.VARCHAR);
          preparedStatement.setNull(index++, Types.INTEGER);
        } else if (VehicleTypeConstant.CAR.equals(vehicle.getType())) {
          Car car = (Car) vehicle;

          preparedStatement.setNull(index++, Types.VARCHAR);
          preparedStatement.setNull(index++, Types.VARCHAR);

          preparedStatement.setInt(index++, car.getSeat());
          preparedStatement.setString(index++, car.getFuel());
          preparedStatement.setInt(index++, car.getEngineCapacity());
          preparedStatement.setString(index++, car.getBodyType());

          preparedStatement.setNull(index++, Types.INTEGER);
          preparedStatement.setNull(index++, Types.VARCHAR);
          preparedStatement.setNull(index++, Types.INTEGER);

        } else if (VehicleTypeConstant.MOTORBIKE.equals(vehicle.getType())) {
          Motorbike motorbike = (Motorbike) vehicle;

          preparedStatement.setNull(index++, Types.VARCHAR);
          preparedStatement.setNull(index++, Types.VARCHAR);

          preparedStatement.setNull(index++, Types.INTEGER);
          preparedStatement.setNull(index++, Types.VARCHAR);
          preparedStatement.setNull(index++, Types.INTEGER);
          preparedStatement.setNull(index++, Types.VARCHAR);

          preparedStatement.setInt(index++, motorbike.getEngineCapacity());
          preparedStatement.setString(index++, motorbike.getTypeMotorbike());
          preparedStatement.setString(index++, motorbike.getPower());
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

        int index = CommonConstant.INDEX;
        preparedStatement.setString(index++, vehicle.getModel());
        preparedStatement.setString(index++, vehicle.getManufacturer());
        preparedStatement.setInt(index++, vehicle.getYear());
        preparedStatement.setBigDecimal(index++, vehicle.getBasePrice());
        preparedStatement.setString(index++, vehicle.getOrigin());
        preparedStatement.setInt(index++, vehicle.getQuantity());
        preparedStatement.setString(index++, vehicle.getType());
        preparedStatement.setInt(index++, vehicle.getId());

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
  public int delete(Vehicle vehicle) {

    int result = 0;
    Connection connection = null;

    try {
      connection = DBConnection.getInstance().getConnection();
      connection.setAutoCommit(false);

      try (PreparedStatement preparedStatement =
          connection.prepareStatement(VehicleSqlConstant.DELETE_VEHICLE)) {

        int index = CommonConstant.INDEX;

        preparedStatement.setInt(index++, vehicle.getId());

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
          int id = resultSet.getInt(VehicleEntityConstant.COLUMN_ID);
          String model = resultSet.getString(VehicleEntityConstant.COLUMN_MODEL);
          String manufacturer = resultSet.getString(VehicleEntityConstant.COLUMN_MANUFACTURER);
          int year = resultSet.getInt(VehicleEntityConstant.COLUMN_YEAR);
          BigDecimal basePrice = resultSet.getBigDecimal(VehicleEntityConstant.COLUMN_BASE_PRICE);
          String origin = resultSet.getString(VehicleEntityConstant.COLUMN_ORIGIN);
          int quantity = resultSet.getInt(VehicleEntityConstant.COLUMN_QUANTITY);
          String type = resultSet.getString(VehicleEntityConstant.COLUMN_TYPE);

          String typeBicycle = resultSet.getString(VehicleEntityConstant.COLUMN_TYPE_BICYCLE);
          String frameMaterial = resultSet.getString(VehicleEntityConstant.COLUMN_FRAME_MATERIAL);

          int seat = resultSet.getInt(VehicleEntityConstant.COLUMN_SEAT);
          String fuel = resultSet.getString(VehicleEntityConstant.COLUMN_FUEL);
          int engineCapacityCar = resultSet.getInt(
              VehicleEntityConstant.COLUMN_ENGINE_CAPACITY_CAR);
          String bodyType = resultSet.getString(VehicleEntityConstant.COLUMN_BODY_TYPE);

          int engineCapacityMotorbike = resultSet.getInt(
              VehicleEntityConstant.COLUMN_ENGINE_CAPACITY_MOTORBIKE);
          String motorbikeType = resultSet.getString(VehicleEntityConstant.COLUMN_TYPE_MOTORBIKE);
          String power = resultSet.getString(VehicleEntityConstant.COLUMN_POWER);

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
