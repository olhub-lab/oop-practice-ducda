import java.math.BigDecimal;

abstract class Vehicle {

  protected String model;
  protected String manufacturer;
  protected int year;
  protected BigDecimal basePrice;
  protected String origin;
  protected static final BigDecimal vatRate = BigDecimal.valueOf(0.1);

  public Vehicle(String model, String manufacturer, int year, BigDecimal basePrice, String origin) {
    this.model = model;
    this.manufacturer = manufacturer;
    this.year = year;
    this.basePrice = basePrice;
    this.origin = origin;

  }

  abstract BigDecimal importTax();

  abstract BigDecimal exciseTax();

  BigDecimal vat() {
    BigDecimal taxSum = basePrice.add(importTax()).add(exciseTax());

    return taxSum.multiply(vatRate);
  }


  BigDecimal finalPrice() {
    return basePrice.add(importTax()).add(exciseTax()).add(vat());
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
