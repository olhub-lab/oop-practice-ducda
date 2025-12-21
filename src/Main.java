import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Write Vehicle: Car, Motorbike, Bike");
    String choice = sc.nextLine();
    System.out.print("Model: ");
    String model = sc.nextLine();
    System.out.print("Manufacturer: ");
    String manufacturer = sc.nextLine();
    System.out.print("Year of Manufacture: ");
    int year = sc.nextInt();
    System.out.print("Base Price: ");
    BigDecimal basePrice = sc.nextBigDecimal();
    sc.nextLine();
    System.out.print("Origin: ");
    String origin = sc.nextLine();
    Vehicle vehicle = null;
    if (choice.equalsIgnoreCase("Car")) {
      System.out.print("Seat: ");
      int seat = sc.nextInt();
      System.out.print("fuel: ");
      String fuel = sc.nextLine();
      sc.nextLine();
      System.out.print("Engine Capacity: ");
      int engineCapacity = sc.nextInt();
      System.out.print("Body Type: ");
      String bodyType = sc.nextLine();
      sc.nextLine();
      vehicle = new Car(model,manufacturer,year,basePrice,origin,seat,fuel,engineCapacity);
    } else if (choice.equalsIgnoreCase("Motorbike")) {
      System.out.print("Engine Capacity: ");
      int engineCapacity = sc.nextInt();
      System.out.print("Type Motorbike: ");
      String typeMotorbike = sc.nextLine();
      sc.nextLine();
      System.out.print("Power: ");
      String power = sc.nextLine();
      vehicle = new Motobike(model,manufacturer,year,basePrice,origin,engineCapacity,typeMotorbike,power);
    } else if (choice.equalsIgnoreCase("Bike")) {
      System.out.print("Type Bicycle: ");
      String typeBicycle = sc.nextLine();
      System.out.print("Frame Material: ");
      String frameMaterial = sc.nextLine();
      vehicle = new Bicycle(model,manufacturer,year,basePrice,origin,frameMaterial,typeBicycle);
    }
    vehicle.printInformation();

  }
}
