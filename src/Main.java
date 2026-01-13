import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter dealership name: ");
    String dealerName = sc.nextLine();
    Dealership dealership = new Dealership(dealerName);

    System.out.print("Enter the number of vehicles: ");
    int numberOfVehicles = sc.nextInt();
    sc.nextLine();

    for (int i = 0; i < numberOfVehicles; i++) {
      System.out.println("\nVehicle " + (i + 1));
      System.out.print("Choose type (Car/Motorbike/Bike): ");
      String choice = sc.nextLine();

      System.out.print("Model: ");
      String model = sc.nextLine();

      System.out.print("Manufacturer: ");
      String manufacturer = sc.nextLine();

      System.out.print("Year: ");
      int year = sc.nextInt();

      System.out.print("Base price: ");
      BigDecimal basePrice = sc.nextBigDecimal();
      sc.nextLine();

      System.out.print("Origin: ");
      String origin = sc.nextLine();

      System.out.print("Quantity: ");
      int quantity = sc.nextInt();
      sc.nextLine();

      System.out.print("Vehicle type: ");
      String type = sc.nextLine();

      Vehicle vehicle = null;

      if (choice.equalsIgnoreCase("Car")) {
        System.out.print("Seat: ");
        int seat = sc.nextInt();
        sc.nextLine();

        System.out.print("Fuel: ");
        String fuel = sc.nextLine();

        System.out.print("Engine capacity: ");
        int engineCapacity = sc.nextInt();
        sc.nextLine();

        vehicle = new Car(model, manufacturer, year, basePrice,
            origin, quantity, type, seat, fuel, engineCapacity);

      } else if (choice.equalsIgnoreCase("Motorbike")) {
        System.out.print("Engine capacity: ");
        int engineCapacity = sc.nextInt();
        sc.nextLine();

        System.out.print("Motorbike type: ");
        String typeMotorbike = sc.nextLine();

        System.out.print("Power: ");
        String power = sc.nextLine();

        vehicle = new Motobike(model, manufacturer, year, basePrice,
            origin, quantity, type, engineCapacity, typeMotorbike, power);

      } else if (choice.equalsIgnoreCase("Bike")) {
        System.out.print("Bicycle type: ");
        String typeBicycle = sc.nextLine();

        System.out.print("Frame material: ");
        String frameMaterial = sc.nextLine();

        vehicle = new Bicycle(model, manufacturer, year, basePrice,
            origin, quantity, type, frameMaterial, typeBicycle);
      }

      dealership.addVehicle(vehicle);
    }


    System.out.print("\nEnter number of customers: ");
    int numberOfCustomer = sc.nextInt();
    sc.nextLine();

    for (int i = 0; i < numberOfCustomer; i++) {
      System.out.println("\nCustomer " + (i + 1));

      System.out.print("Name: ");
      String name = sc.nextLine();

      System.out.print("Address: ");
      String address = sc.nextLine();

      System.out.print("Phone: ");
      String phone = sc.nextLine();

      System.out.print("Balance: ");
      BigDecimal balance = sc.nextBigDecimal();
      sc.nextLine();

      dealership.addCustomer(new Customer(name, address, phone, balance));
    }

    while (true) {
      System.out.println("\n===== BUY VEHICLE MENU =====");
      System.out.println("1. Buy vehicle");
      System.out.println("0. Exit");
      System.out.print("Choose: ");

      int choice = sc.nextInt();
      sc.nextLine();

      if (choice == 0) {
        break;
      }

      System.out.println("\nCustomers:");
      for (int i = 0; i < dealership.getCustomers().size(); i++) {
        System.out.println((i + 1) + ". " +
            dealership.getCustomers().get(i).getName());
      }

      System.out.print("Choose customer: ");
      int chooseCustomer = sc.nextInt() - 1;
      sc.nextLine();
      Customer customer = dealership.getCustomers().get(chooseCustomer);

      System.out.println("\nVehicles:");

      for (int i = 0; i < dealership.getInventory().size(); i++) {
        Vehicle v = dealership.getInventory().get(i);
        System.out.println((i + 1) + ". " + v.getModel()
            + " | Type: " + v.getType()
            + " | Price: " + v.finalPrice()
            + " | Quatity: " + v.getQuantity());
      }

      System.out.print("Choose vehicle: ");
      int chooseVehicle = sc.nextInt() - 1;
      sc.nextLine();
      Vehicle vehicle = dealership.getInventory().get(chooseVehicle);

      //buy

      if (dealership.sellVehicle(vehicle, customer)) {
        System.out.println(" Purchase successful!");
        System.out.println("Customer: " + customer.getName());
        System.out.println("Balance: " + customer.getBalance());
        System.out.println("Loyalty: " + customer.loyaltyLevel());
      } else {
        System.out.println(" Purchase failed");

        System.out.println("Suggested alternatives:");

        ArrayList<Vehicle> alternatives =
            dealership.suggestAlternative(vehicle.getType());

        if (alternatives.isEmpty()) {
          System.out.println("No alternative available");
        } else {
          System.out.println("Suggested alternatives:");

          System.out.println( alternatives.get(0).getModel()
              + " | Price: " + alternatives.get(0).finalPrice());

          if (alternatives.size() > 1) {
            System.out.println( alternatives.get(1).getModel()
                + " | Price: " + alternatives.get(1).finalPrice());
          }
        }
      }


    }
    sc.close();


  }
}
