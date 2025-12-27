import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    List<Vehicle> inventory = new ArrayList<>();
    List<Integer> stock = new ArrayList<>();

    System.out.print("Enter the number of vehicles: ");
    int numberOfVehicles = sc.nextInt();
    sc.nextLine();

    for (int i = 0; i < numberOfVehicles; i++) {
      System.out.println("There are vehicle: Car, Motorbike, Bike");
      System.out.println("Enter Vehicle number: " + (i + 1));
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

        vehicle = new Car(model, manufacturer, year, basePrice, origin, seat, fuel, engineCapacity);
      } else if (choice.equalsIgnoreCase("Motorbike")) {
        System.out.print("Engine Capacity: ");
        int engineCapacity = sc.nextInt();

        System.out.print("Type Motorbike: ");
        String typeMotorbike = sc.nextLine();
        sc.nextLine();

        System.out.print("Power: ");
        String power = sc.nextLine();

        vehicle = new Motobike(model, manufacturer, year, basePrice, origin, engineCapacity,
            typeMotorbike, power);

      } else if (choice.equalsIgnoreCase("Bike")) {

        System.out.print("Type Bicycle: ");
        String typeBicycle = sc.nextLine();

        System.out.print("Frame Material: ");
        String frameMaterial = sc.nextLine();

        vehicle = new Bicycle(model, manufacturer, year, basePrice, origin, frameMaterial,
            typeBicycle);
      }

      System.out.print("Quantity in stock: ");
      int quantity = sc.nextInt();
      sc.nextLine();

      inventory.add(vehicle);
      stock.add(quantity);
    }
    //cusotmer
    List<Customer> customers = new ArrayList<>();

    System.out.print("Enter the number of customers: ");
    int customerCount = sc.nextInt();
    sc.nextLine();

    for (int i = 0; i < customerCount; i++) {
      System.out.println("Customer: " + (i + 1));

      System.out.print("Name: ");
      String name = sc.nextLine();

      System.out.print("Number Phone: ");
      String phone = sc.nextLine();

      System.out.print("Address: ");
      String address = sc.nextLine();

      System.out.print("Initial balance: ");
      BigDecimal balance = sc.nextBigDecimal();
      sc.nextLine();
      if (balance.compareTo(BigDecimal.ZERO) < 0) {
        balance = BigDecimal.ZERO;
      }

      customers.add(new Customer(name, address, phone, balance));
    }
    System.out.println("-----------------------------");
    //buy
    for (Customer customer : customers) {
      System.out.println("Customer:" + customer.getName());

      System.out.println("I have the following vehicles in my inventory:");
      for (int i = 0; i < inventory.size(); i++) {
        System.out.println((i + 1) + ". " + inventory.get(i).toString());
        inventory.get(i).printInformation();
        System.out.println("Stock:" + stock.get(i));
      }

      System.out.println("Which vehicle do you want to buy?");
      System.out.print("Choose vehicle number: ");
      int vehicleIndex = sc.nextInt() - 1;
      sc.nextLine();

      Vehicle chosenVehicle = inventory.get(vehicleIndex);

      if (customer.buyVehicle(chosenVehicle)) {
        stock.set(vehicleIndex, stock.get(vehicleIndex) - 1);
        System.out.println("Purchase successful!");
      } else {
        System.out.print("Enter deposit amount: ");
        BigDecimal amount = sc.nextBigDecimal();
        sc.nextLine();

        customer.depositMonney(amount);
        if (customer.buyVehicle(chosenVehicle)) {
          stock.set(vehicleIndex, stock.get(vehicleIndex) - 1);
          System.out.println("Purchase successful!");
        } else {
          System.out.print("Still not enough money ");
        }
      }
    }

    System.out.println("\n===== DEALERSHIP INVENTORY =====");
    for (int i = 0; i < inventory.size(); i++) {
      System.out.println((i + 1) + ". " + inventory.get(i).toString());
      inventory.get(i).printInformation();
      System.out.println("Stock:" + stock.get(i));
      System.out.println("-------------------------------");
    }

    System.out.println("\n===== CUSTOMER LIST =====");
    for (Customer customer : customers) {
      System.out.println("Name: " + customer.getName());
      System.out.println("Phone: " + customer.getPhoneNumber());
      System.out.println("Address: " + customer.getAddress());
      System.out.println("Balance: " + customer.getBalance());
      System.out.println("Loyalty level: " + customer.loyaltyLevel());
      System.out.println("Owned vehicles: " + customer.getOwnedVehiclesCount());
      if (customer.getOwnedVehiclesCount() > 0) {
        System.out.println("Purchase history:");
        for (Vehicle vehicle : customer.getPurchaseHistory()) {
          System.out.println(vehicle.model + " | Final price: " + vehicle.finalPrice());
        }
      } else {
        System.out.println("No purchase history.");
      }

      System.out.println("-------------------------------");
    }
    sc.close();
  }
}
