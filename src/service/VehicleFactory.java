package service;

import java.math.BigDecimal;
import model.Bicycle;
import model.Car;
import model.Motorbike;

public class VehicleFactory {

  public static Car createCar(
      String model, String manufacturer, int year,
      BigDecimal price, String origin, int quantity,
      String type, int seat, String fuel,
      int engine, String bodyType) {

    return new Car(model, manufacturer, year, price,
        origin, quantity, type,
        seat, fuel, engine, bodyType);
  }

  public static Motorbike createMotorbike(
      String model, String manufacturer, int year,
      BigDecimal price, String origin, int quantity,
      String type, int engine,
      String motorbikeType, String power) {

    return new Motorbike(model, manufacturer, year, price,
        origin, quantity, type,
        engine, motorbikeType, power);
  }

  public static Bicycle createBicycle(
      String model, String manufacturer, int year,
      BigDecimal price, String origin, int quantity,
      String type, String frame, String bikeType) {

    return new Bicycle(model, manufacturer, year, price,
        origin, quantity, type,
        frame, bikeType);
  }
}
