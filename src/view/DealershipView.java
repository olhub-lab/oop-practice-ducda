package view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import model.Customer;
import model.Vehicle;

public class DealershipView {
  private Scanner scanner = new Scanner(System.in);

  public int showMenu() {
    show("\n===== DEALERSHIP MENU =====");
    show("1. View customers");
    show("2. View vehicles in inventory");
    show("3. Add vehicle");
    show("4. Add customer");
    show("5. Buy vehicle");
    show("0. Exit");
    show("Choose: ");

    return inputInt();
  }


  public String inputString() {
    return scanner.nextLine();
  }

  public int inputInt() {
    while (true) {
      try {
        return Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid number. Please enter again: ");
      }
    }
  }


  public BigDecimal inputBigDecimal() {
    while (true) {
      try {
        return new BigDecimal(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid amount. Please enter again: ");
      }
    }
  }

  public void show(String message) {
    System.out.println(message);
  }

  public void showCustomers(List<Customer> customers) {
    if (customers.isEmpty()) {
      show("No customers available");
      return;
    }
    show("\n--- Customer List ---");
    for (int i = 0; i < customers.size(); i++) {
      Customer customer = customers.get(i);
      show((i + 1) + ". " + customer.getName()
          + " | Address: " + customer.getAddress()
          + " | Phone: " + customer.getPhoneNumber()
          + " | Balance: " + customer.getBalance());
    }
  }

  public void showVehicles(List<Vehicle> vehicles) {
    if (vehicles.isEmpty()) {
      show("No vehicles in inventory.");
      return;
    }
    show("\n--- Vehicle Inventory ---");
    for (int i = 0; i < vehicles.size(); i++) {
      Vehicle vehicle = vehicles.get(i);
      show((i + 1) + ". " + vehicle.getModel()
          + " | Type: " + vehicle.getType()
          + " | Price: " + vehicle.finalPrice()
          + " | Quantity: " + vehicle.getQuantity());
    }
  }

  public int chooseCustomer(List<Customer> customers) {
    showCustomers(customers);
    show("Choose customer:");
    return inputInt() - 1;
  }

  public int chooseVehicle(List<Vehicle> vehicles) {
    showVehicles(vehicles);
    show("Choose vehicle:");
    return inputInt() - 1;
  }

  public void showSuggestedVehicles(List<Vehicle> vehicles) {
    if (vehicles == null || vehicles.isEmpty()) {
      show("No alternative available.");
      return;
    }

    show("Suggested alternatives:");
    for (Vehicle vehicle : vehicles) {
      show(vehicle.getModel() + " | Price: " + vehicle.finalPrice());
    }
  }

}
