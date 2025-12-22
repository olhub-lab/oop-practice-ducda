import java.math.BigDecimal;

class Motobike extends Vehicle {

  private int engineCapacity;
  private String typeMotobike;
  private String power;
  private static final String ORIGIN_DOMESTIC = "domestic";
  private static final BigDecimal IMPORT_TAX_RATE = BigDecimal.valueOf(0.3);
  private static final BigDecimal EXCISE_TAX_RATE = BigDecimal.valueOf(0.2);
  private static final int ENGINE_CAPACITY_LIMIT = 150;

  public Motobike(String model, String manufacturer, int year, BigDecimal basePrice, String origin,
      int engineCapacity, String typeMotobike, String power) {
    super(model, manufacturer, year, basePrice, origin);
    this.engineCapacity = engineCapacity;
    this.typeMotobike = typeMotobike;
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
}
