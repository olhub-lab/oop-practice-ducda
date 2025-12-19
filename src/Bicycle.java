class Bicycle extends Vehicle {

  String typeBicycle;
  String frameMaterial;

  public Bicycle(String model, String manufacturer, int year, double basePrice, String origin,
      String typeBicycle, String frameMaterial) {
    super(model, manufacturer, year, basePrice, origin);
    this.typeBicycle = typeBicycle;
    this.frameMaterial = frameMaterial;
  }

  @Override
  double importTax() {
    if (typeBicycle.equals("domestic")) {
      return 0;
    } else {
      return basePrice * 0.1;
    }
  }

  @Override
  double exciseTax() {
    return 0;
  }
}
