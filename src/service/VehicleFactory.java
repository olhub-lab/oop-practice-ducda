package service;

import java.math.BigDecimal;
import model.Bicycle;
import model.Car;
import model.Motorbike;

public class VehicleFactory {

  public static Car createCar(int idVehicle,
      String model, String manufacturer, int year,
      BigDecimal basePrice, String origin, int quantity,
      String type, int seat, String fuel,
      int engineCapacity, String bodyType) {

    return new Car(idVehicle,model, manufacturer, year, basePrice,
        origin, quantity, type,
        seat, fuel, engineCapacity, bodyType);
  }

  public static Motorbike createMotorbike(int idVehicle,
      String model, String manufacturer, int year,
      BigDecimal basePrice, String origin, int quantity,
      String type, int engineCapacity,
      String motorbikeType, String power) {

    return new Motorbike(idVehicle,model, manufacturer, year, basePrice,
        origin, quantity, type,
        engineCapacity, motorbikeType, power);
  }

  public static Bicycle createBicycle(int idVehicle,
      String model, String manufacturer, int year,
      BigDecimal basePrice, String origin, int quantity,
      String type, String frameMaterial, String typeBicycle) {

    return new Bicycle(idVehicle,model, manufacturer, year, basePrice,
        origin, quantity, type,
        frameMaterial, typeBicycle);
  }
}
