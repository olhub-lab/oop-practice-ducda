package entity;

import java.math.BigDecimal;

public class VehicleEntity {

  private int id;
  private String model;
  private String manufacturer;
  private int year;
  private BigDecimal basePrice;
  private String origin;
  private int quantity;
  private String type;

  private String typeBicycle;
  private String frameMaterial;

  private int seat;
  private String fuel;
  private int engineCapacityCar;
  private String bodyType;

  private int engineCapacityMotorbike;
  private String typeMotorbike;
  private String power;

  public VehicleEntity() {
  }

  public VehicleEntity(int id, String model, String manufacturer, int year, BigDecimal basePrice,
      String origin, int quantity, String type, String typeBicycle,
      String frameMaterial, int seat, String fuel, int engineCapacityCar,
      String bodyType, int engineCapacityMotorbike, String typeMotorbike,
      String power) {
    this.id = id;
    this.model = model;
    this.manufacturer = manufacturer;
    this.year = year;
    this.basePrice = basePrice;
    this.origin = origin;
    this.quantity = quantity;
    this.type = type;
    this.typeBicycle = typeBicycle;
    this.frameMaterial = frameMaterial;
    this.seat = seat;
    this.fuel = fuel;
    this.engineCapacityCar = engineCapacityCar;
    this.bodyType = bodyType;
    this.engineCapacityMotorbike = engineCapacityMotorbike;
    this.typeMotorbike = typeMotorbike;
    this.power = power;
  }

  public int getId() {
    return id;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public int getYear() {
    return year;
  }

  public String getModel() {
    return model;
  }

  public String getOrigin() {
    return origin;
  }

  public BigDecimal getBasePrice() {
    return basePrice;
  }

  public int getQuantity() {
    return quantity;
  }

  public String getType() {
    return type;
  }

  public String getTypeBicycle() {
    return typeBicycle;
  }

  public String getFrameMaterial() {
    return frameMaterial;
  }

  public int getSeat() {
    return seat;
  }

  public String getFuel() {
    return fuel;
  }

  public int getEngineCapacityCar() {
    return engineCapacityCar;
  }

  public String getBodyType() {
    return bodyType;
  }

  public int getEngineCapacityMotorbike() {
    return engineCapacityMotorbike;
  }

  public String getTypeMotorbike() {
    return typeMotorbike;
  }

  public String getPower() {
    return power;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setBasePrice(BigDecimal basePrice) {
    this.basePrice = basePrice;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setTypeBicycle(String typeBicycle) {
    this.typeBicycle = typeBicycle;
  }

  public void setFrameMaterial(String frameMaterial) {
    this.frameMaterial = frameMaterial;
  }

  public void setSeat(int seat) {
    this.seat = seat;
  }

  public void setFuel(String fuel) {
    this.fuel = fuel;
  }

  public void setEngineCapacityCar(int engineCapacityCar) {
    this.engineCapacityCar = engineCapacityCar;
  }

  public void setBodyType(String bodyType) {
    this.bodyType = bodyType;
  }

  public void setEngineCapacityMotorbike(int engineCapacityMotorbike) {
    this.engineCapacityMotorbike = engineCapacityMotorbike;
  }

  public void setTypeMotorbike(String typeMotorbike) {
    this.typeMotorbike = typeMotorbike;
  }

  public void setPower(String power) {
    this.power = power;
  }
}
