class Car extends Vehicle {

  int seat;
  String fuel;
  int engineCapacity;
  String bodyType;

  public Car(String model, String manufacturer, int year, double basePrice, String origin, int seat,
      String fuel, int engineCapacity) {
    super(model, manufacturer, year, basePrice, origin);
    this.seat = seat;
    this.fuel = fuel;
    this.engineCapacity = engineCapacity;
    this.bodyType = bodyType;
  }

  @Override
  double importTax() {
    if (origin.equals("domestic")) {
      return 0;
    } else {
      return basePrice * 0.5;
    }

  }

  @Override
  double exciseTax() {
    if (engineCapacity < 3000) {
      return (basePrice + importTax()) * 0.5;
    } else {
      return (basePrice + importTax());
    }
  }
}
