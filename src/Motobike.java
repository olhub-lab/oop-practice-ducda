class Motobike extends Vehicle {

  int engineCapacity;
  String typeMotobike;
  String power;

  public Motobike(String model, String manufacturer, int year, double basePrice, String origin,
      int engineCapacity, String typeMotobike, String power) {
    super(model, manufacturer, year, basePrice, origin);
    this.engineCapacity = engineCapacity;
    this.typeMotobike = typeMotobike;
    this.power = power;

  }

  @Override
  double importTax() {
    if (origin.equals("domestic")) {
      return 0;
    } else {
      return basePrice * 0.3;
    }
  }

  @Override
  double exciseTax() {
    if (engineCapacity < 150) {
      return 0;
    } else {
      return (basePrice + importTax()) * 0.2;
    }

  }
}
