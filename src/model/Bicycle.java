package model;

import java.math.BigDecimal;

public class Bicycle extends Vehicle {

  private String typeBicycle;
  private String frameMaterial;
  private static final BigDecimal IMPORT_TAX_RATE = BigDecimal.ZERO;
  private static final BigDecimal EXCISE_TAX_RATE = BigDecimal.ZERO;

  public Bicycle(int id, String model, String manufacturer, int year, BigDecimal basePrice,
      String origin,
      int quantity, String type,
      String typeBicycle, String frameMaterial) {
    super(id, model, manufacturer, year, basePrice, origin, quantity, type);
    this.typeBicycle = typeBicycle;
    this.frameMaterial = frameMaterial;
  }

  @Override
  BigDecimal importTax() {
    return basePrice.multiply(IMPORT_TAX_RATE);
  }

  @Override
  BigDecimal exciseTax() {
    return basePrice.multiply(EXCISE_TAX_RATE);
  }

  public String getTypeBicycle() {
    return typeBicycle;
  }

  public String getFrameMaterial() {
    return frameMaterial;
  }
}