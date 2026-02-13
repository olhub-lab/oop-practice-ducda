package model;

import java.math.BigDecimal;

public abstract class Vehicle {

  protected int idVehicle;
  protected String model;
  protected String manufacturer;
  protected int year;
  protected BigDecimal basePrice;
  protected String origin;
  protected static final BigDecimal vatRate = BigDecimal.valueOf(0.1);
  protected int quantity;
  protected String type;

  public Vehicle() {
  }

  public Vehicle( int idVehicle,
                  String model,
                  String manufacturer,
                  int year,
                  BigDecimal basePrice,
                  String origin,
                  int quantity,
                  String type) {
    this.idVehicle = idVehicle;
    this.model = model;
    this.manufacturer = manufacturer;
    this.year = year;
    this.basePrice = basePrice;
    this.origin = origin;
    this.quantity = quantity;
    this.type = type;
  }

  abstract BigDecimal importTax();

  abstract BigDecimal exciseTax();

  BigDecimal vat() {
    BigDecimal taxSum = basePrice.add(importTax()).add(exciseTax());

    return taxSum.multiply(vatRate);
  }


  public BigDecimal finalPrice() {
    return basePrice.add(importTax()).add(exciseTax()).add(vat());
  }

  public boolean checkInventory() {
    return quantity > 0;
  }

  public void minusQuantity(){
    quantity--;
  }

  public String getModel() {
    return model;
  }

  public int getYear() {
    return year;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public BigDecimal getBasePrice() {
    return basePrice;
  }

  public String getOrigin() {
    return origin;
  }

  public int getQuantity() {
    return quantity;
  }

  public String getType() {
    return type;
  }

  public int getIdVehicle() {
    return idVehicle;
  }

  public void setIdVehicle(int idVehicle) {
    this.idVehicle = idVehicle;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
