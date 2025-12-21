import java.math.BigDecimal;
class Bicycle extends Vehicle {

  private String typeBicycle;
  private String frameMaterial;
  private static final BigDecimal IMPORT_TAX_RATE = BigDecimal.ZERO;
  private static final BigDecimal EXCISE_TAX_RATE = BigDecimal.ZERO;

  public Bicycle(String model, String manufacturer, int year, BigDecimal basePrice, String origin,
      String typeBicycle, String frameMaterial) {
    super(model, manufacturer, year, basePrice, origin);
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
}