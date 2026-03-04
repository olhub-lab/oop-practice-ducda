package model;

import java.math.BigDecimal;

public class Motorbike extends Vehicle {

  private int engineCapacity;
  private String typeMotorbike;
  private String power;
  private static final String ORIGIN_DOMESTIC = "domestic";
  private static final BigDecimal IMPORT_TAX_RATE = BigDecimal.valueOf(0.3);
  private static final BigDecimal EXCISE_TAX_RATE = BigDecimal.valueOf(0.2);
  private static final int ENGINE_CAPACITY_LIMIT = 150;

  public Motorbike() {
  }

  public Motorbike(int idVehicle, String model, String manufacturer, int year, BigDecimal basePrice,
      String origin,
      int quantity, String type,
      int engineCapacity, String typeMotorbike, String power) {
    super(idVehicle, model, manufacturer, year, basePrice, origin, quantity, type);
    this.engineCapacity = engineCapacity;
    this.typeMotorbike = typeMotorbike;
    this.power = power;

  }

  @Override
  BigDecimal importTax() {
    if (origin.equalsIgnoreCase(ORIGIN_DOMESTIC)) {
      return BigDecimal.ZERO;
    } else {
      return basePrice.multiply(IMPORT_TAX_RATE);
    }
  }

  @Override
  BigDecimal exciseTax() {
    if (engineCapacity < ENGINE_CAPACITY_LIMIT) {
      return BigDecimal.ZERO;
    } else {
      BigDecimal taxBase = basePrice.add(importTax());
      return taxBase.multiply(EXCISE_TAX_RATE);
    }
  }

  public int getEngineCapacity() {
    return engineCapacity;
  }

  public String getTypeMotorbike() {
    return typeMotorbike;
  }

  public String getPower() {
    return power;
  }
}
