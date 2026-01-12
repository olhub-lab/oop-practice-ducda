import java.math.BigDecimal;

class Car extends Vehicle {

  private int seat;
  private String fuel;
  private int engineCapacity;
  private String bodyType;
  private static final String ORIGIN_DOMESTIC = "domestic";
  private static final BigDecimal IMPORT_TAX_RATE = BigDecimal.valueOf(0.5);
  private static final BigDecimal EXCISE_TAX_RATE_LOW = BigDecimal.valueOf(0.5);
  private static final BigDecimal EXCISE_TAX_RATE_HIGH = BigDecimal.ONE;
  private static final int ENGINE_CAPACITY_LIMIT = 3000;

  public Car(String model, String manufacturer, int year, BigDecimal basePrice, String origin,
      int quantity, String type,
      int seat,
      String fuel, int engineCapacity) {
    super(model, manufacturer, year, basePrice, origin, quantity, type);
    this.seat = seat;
    this.fuel = fuel;
    this.engineCapacity = engineCapacity;
    this.bodyType = bodyType;
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
    BigDecimal taxBase = basePrice.add(importTax());

    if (engineCapacity < ENGINE_CAPACITY_LIMIT) {
      return taxBase.multiply(EXCISE_TAX_RATE_LOW);
    } else {
      return taxBase.multiply(EXCISE_TAX_RATE_HIGH);
    }
  }
}
