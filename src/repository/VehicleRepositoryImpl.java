package repository;

import constant.CommonConstant;
import constant.VehicleEntityConstant;
import constant.VehicleSqlConstant;
import constant.VehicleTypeConstant;
import database.DBConnection;
import entity.VehicleEntity;
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

      VehicleEntity vehicleEntity = toEntity(vehicle);

      try (PreparedStatement preparedStatement = connection.prepareStatement(
          VehicleSqlConstant.INSERT_VEHICLE)) {
        int index = CommonConstant.INDEX;

        preparedStatement.setInt(index++, vehicleEntity.getId());
        preparedStatement.setString(index++, vehicleEntity.getModel());
        preparedStatement.setString(index++, vehicleEntity.getManufacturer());
        preparedStatement.setInt(index++, vehicleEntity.getYear());
        preparedStatement.setBigDecimal(index++, vehicleEntity.getBasePrice());
        preparedStatement.setString(index++, vehicleEntity.getOrigin());
        preparedStatement.setInt(index++, vehicleEntity.getQuantity());
        preparedStatement.setString(index++, vehicleEntity.getType());

        preparedStatement.setString(index++, vehicleEntity.getTypeBicycle());
        preparedStatement.setString(index++, vehicleEntity.getFrameMaterial());

        preparedStatement.setInt(index++, vehicleEntity.getSeat());
        preparedStatement.setString(index++, vehicleEntity.getFuel());
        preparedStatement.setInt(index++, vehicleEntity.getEngineCapacityCar());
        preparedStatement.setString(index++, vehicleEntity.getBodyType());

        preparedStatement.setInt(index++, vehicleEntity.getEngineCapacityMotorbike());
        preparedStatement.setString(index++, vehicleEntity.getTypeMotorbike());
        preparedStatement.setString(index++, vehicleEntity.getPower());
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

      VehicleEntity vehicleEntity = toEntity(vehicle);

      try (PreparedStatement preparedStatement = connection.prepareStatement(
          VehicleSqlConstant.UPDATE_VEHICLE)) {

        int index = CommonConstant.INDEX;
        preparedStatement.setString(index++, vehicleEntity.getModel());
        preparedStatement.setString(index++, vehicleEntity.getManufacturer());
        preparedStatement.setInt(index++, vehicleEntity.getYear());
        preparedStatement.setBigDecimal(index++, vehicleEntity.getBasePrice());
        preparedStatement.setString(index++, vehicleEntity.getOrigin());
        preparedStatement.setInt(index++, vehicleEntity.getQuantity());
        preparedStatement.setString(index++, vehicleEntity.getType());
        preparedStatement.setInt(index++, vehicleEntity.getId());

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
    VehicleEntity vehicleEntity = toEntity(vehicle);

    try {
      connection = DBConnection.getInstance().getConnection();
      connection.setAutoCommit(false);

      try (PreparedStatement preparedStatement =
          connection.prepareStatement(VehicleSqlConstant.DELETE_VEHICLE)) {

        int index = CommonConstant.INDEX;

        preparedStatement.setInt(index++, vehicleEntity.getId());

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
          VehicleEntity entity = mapToEntity(resultSet);

          Vehicle vehicle = toVehicle(entity);

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

  private VehicleEntity mapToEntity(ResultSet resultSet) throws SQLException {

    VehicleEntity vehicleEntity = new VehicleEntity();

    vehicleEntity.setId(resultSet.getInt(VehicleEntityConstant.COLUMN_ID));
    vehicleEntity.setModel(resultSet.getString(VehicleEntityConstant.COLUMN_MODEL));
    vehicleEntity.setManufacturer(resultSet.getString(VehicleEntityConstant.COLUMN_MANUFACTURER));
    vehicleEntity.setYear(resultSet.getInt(VehicleEntityConstant.COLUMN_YEAR));
    vehicleEntity.setBasePrice(resultSet.getBigDecimal(VehicleEntityConstant.COLUMN_BASE_PRICE));
    vehicleEntity.setOrigin(resultSet.getString(VehicleEntityConstant.COLUMN_ORIGIN));
    vehicleEntity.setQuantity(resultSet.getInt(VehicleEntityConstant.COLUMN_QUANTITY));
    vehicleEntity.setType(resultSet.getString(VehicleEntityConstant.COLUMN_TYPE));

    vehicleEntity.setTypeBicycle(resultSet.getString(VehicleEntityConstant.COLUMN_TYPE_BICYCLE));
    vehicleEntity.setFrameMaterial(
        resultSet.getString(VehicleEntityConstant.COLUMN_FRAME_MATERIAL));

    vehicleEntity.setSeat(resultSet.getInt(VehicleEntityConstant.COLUMN_SEAT));
    vehicleEntity.setFuel(resultSet.getString(VehicleEntityConstant.COLUMN_FUEL));
    vehicleEntity.setEngineCapacityCar(
        resultSet.getInt(VehicleEntityConstant.COLUMN_ENGINE_CAPACITY_CAR));
    vehicleEntity.setBodyType(resultSet.getString(VehicleEntityConstant.COLUMN_BODY_TYPE));

    vehicleEntity.setEngineCapacityMotorbike(
        resultSet.getInt(VehicleEntityConstant.COLUMN_ENGINE_CAPACITY_MOTORBIKE));
    vehicleEntity.setTypeMotorbike(
        resultSet.getString(VehicleEntityConstant.COLUMN_TYPE_MOTORBIKE));
    vehicleEntity.setPower(resultSet.getString(VehicleEntityConstant.COLUMN_POWER));

    return vehicleEntity;
  }

  private Vehicle toVehicle(VehicleEntity entity) {

    switch (entity.getType().toLowerCase()) {

      case VehicleTypeConstant.CAR:
        return VehicleFactory.createCar(
            entity.getId(),
            entity.getModel(),
            entity.getManufacturer(),
            entity.getYear(),
            entity.getBasePrice(),
            entity.getOrigin(),
            entity.getQuantity(),
            entity.getType(),
            entity.getSeat(),
            entity.getFuel(),
            entity.getEngineCapacityCar(),
            entity.getBodyType()
        );

      case VehicleTypeConstant.BICYCLE:
        return VehicleFactory.createBicycle(
            entity.getId(),
            entity.getModel(),
            entity.getManufacturer(),
            entity.getYear(),
            entity.getBasePrice(),
            entity.getOrigin(),
            entity.getQuantity(),
            entity.getType(),
            entity.getTypeBicycle(),
            entity.getFrameMaterial()
        );

      case VehicleTypeConstant.MOTORBIKE:
        return VehicleFactory.createMotorbike(
            entity.getId(),
            entity.getModel(),
            entity.getManufacturer(),
            entity.getYear(),
            entity.getBasePrice(),
            entity.getOrigin(),
            entity.getQuantity(),
            entity.getType(),
            entity.getEngineCapacityMotorbike(),
            entity.getTypeMotorbike(),
            entity.getPower()
        );

      default:
        return null;
    }
  }

  private VehicleEntity toEntity(Vehicle vehicle) {

    VehicleEntity entity = new VehicleEntity();

    entity.setId(vehicle.getId());
    entity.setModel(vehicle.getModel());
    entity.setManufacturer(vehicle.getManufacturer());
    entity.setYear(vehicle.getYear());
    entity.setBasePrice(vehicle.getBasePrice());
    entity.setOrigin(vehicle.getOrigin());
    entity.setQuantity(vehicle.getQuantity());
    entity.setType(vehicle.getType());

    switch (vehicle.getType().toLowerCase()) {

      case VehicleTypeConstant.CAR:
        Car car = (Car) vehicle;
        entity.setSeat(car.getSeat());
        entity.setFuel(car.getFuel());
        entity.setEngineCapacityCar(car.getEngineCapacity());
        entity.setBodyType(car.getBodyType());
        break;

      case VehicleTypeConstant.BICYCLE:
        Bicycle bicycle = (Bicycle) vehicle;
        entity.setTypeBicycle(bicycle.getTypeBicycle());
        entity.setFrameMaterial(bicycle.getFrameMaterial());
        break;

      case VehicleTypeConstant.MOTORBIKE:
        Motorbike motorbike = (Motorbike) vehicle;
        entity.setEngineCapacityMotorbike(motorbike.getEngineCapacity());
        entity.setTypeMotorbike(motorbike.getTypeMotorbike());
        entity.setPower(motorbike.getPower());
        break;
    }

    return entity;
  }
}
