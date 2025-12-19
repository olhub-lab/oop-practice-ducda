abstract class Vehicle {

  String model;
  String manufacturer;
  int year;
  double basePrice;
  String origin;

  public Vehicle(String model, String manufacturer, int year, double basePrice, String origin) {
    this.model = model;
    this.manufacturer = manufacturer;
    this.year = year;
    this.basePrice = basePrice;
    this.origin = origin;

  }

  abstract double importTax();

  abstract double exciseTax();

  double vat() {
    return 0.1 * (basePrice + importTax() + exciseTax());
  }

  double finalPrice() {
    return basePrice + importTax() + exciseTax() + vat();
  }
  void printInformation() {
    System.out.println("Model: " + model);
    System.out.println("Manufacturer: " + manufacturer);
    System.out.println("Year: " + year);
    System.out.println("Base Price: " + basePrice);
    System.out.println("Origin: " + origin);
    System.out.println("Import Tax: " + importTax());
    System.out.println("Excise Tax: " + exciseTax());
    System.out.println("Final Price: " + finalPrice());
  }
}
