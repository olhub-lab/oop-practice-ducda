package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import model.Bicycle;
import model.Car;
import model.Customer;
import model.Dealership;
import model.Motorbike;
import model.Vehicle;
import view.View;

public class Controller {

  private Dealership dealership;
  private View view;

  public Controller(Dealership dealership, View view) {
    this.dealership = dealership;
    this.view = view;
  }

  public void run() {
    while (true) {
      view.show("\n===== DEALERSHIP MENU =====");
      view.show("1. View customers");
      view.show("2. View vehicles in inventory");
      view.show("3. Add vehicle");
      view.show("4. Add customer");
      view.show("5. Buy vehicle");
      view.show("0. Exit");
      view.show("Choose: ");

      int choice = view.inputInt();
      view.inputString();

      switch (choice) {
        case 1:
          showCustomers();
          break;
        case 2:
          showVehicles();
          break;
        case 3:
          inputVehicles();
          break;
        case 4:
          inputCustomers();
          break;
        case 5:
          buyVehicle();
          break;
        case 0:
          view.show("bye");
          return;
        default:
          view.show("Invalid choice");
      }
    }
  }

  private void showCustomers() {
    if (dealership.getCustomers().isEmpty()) {
      view.show("No customers available");
      return;
    }

    view.show("\n--- Customer List ---");
    for (int i = 0; i < dealership.getCustomers().size(); i++) {
      Customer c = dealership.getCustomers().get(i);
      view.show((i + 1) + ". " + c.getName()
          + " | Address: " + c.getAddress()
          + " | Phone: " + c.getPhoneNumber()
          + " | Balance: " + c.getBalance());
    }
  }

  private void showVehicles() {
    if (dealership.getInventory().isEmpty()) {
      view.show("No vehicles in inventory.");
      return;
    }

    view.show("\n--- Vehicle Inventory ---");
    for (int i = 0; i < dealership.getInventory().size(); i++) {
      Vehicle v = dealership.getInventory().get(i);
      view.show((i + 1) + ". " + v.getModel()
          + " | Type: " + v.getType()
          + " | Price: " + v.finalPrice()
          + " | Quantity: " + v.getQuantity());
    }
  }

  public void inputVehicles() {
    view.show("Enter number of vehicles: ");
    int numberOfVehicle = view.inputInt();
    view.inputString();

    for (int i = 0; i < numberOfVehicle; i++) {
      view.show("\nVehicle " + (i + 1));

      view.show("Choose type (Car/Motorbike/Bike): ");
      String choice = view.inputString();

      view.show("Model: ");
      String model = view.inputString();

      view.show("Manufacturer: ");
      String manufacturer = view.inputString();

      view.show("Year: ");
      int year = view.inputInt();
      view.inputString();

      view.show("Base price: ");
      BigDecimal price = view.inputBigDecimal();
      view.inputString();

      view.show("Origin: ");
      String origin = view.inputString();

      view.show("Quantity: ");
      int quantity = view.inputInt();
      view.inputString();

      view.show("Vehicle type: ");
      String type = view.inputString();

      Vehicle vehicle = createVehicle(choice, model, manufacturer,
          year, price, origin, quantity, type);

      if (vehicle != null) {
        dealership.addVehicle(vehicle);
        view.show("Vehicle added successfully.");
      } else {
        view.show("Invalid vehicle type!");
      }
    }
  }

  private Vehicle createVehicle(String choice, String model, String manufacturer,
      int year, BigDecimal price, String origin, int quantity, String type) {

    if (choice.equalsIgnoreCase("Car")) {
      view.show("Seat: ");
      int seat = view.inputInt();
      view.inputString();

      view.show("Fuel: ");
      String fuel = view.inputString();

      view.show("Engine capacity: ");
      int engine = view.inputInt();
      view.inputString();

      view.show("Body type: ");
      String bodyType = view.inputString();

      return new Car(model, manufacturer, year, price, origin,
          quantity, type, seat, fuel, engine,bodyType);
    }

    if (choice.equalsIgnoreCase("Motorbike")) {
      view.show("Engine capacity: ");
      int engine = view.inputInt();
      view.inputString();

      view.show("Motorbike type: ");
      String typeMotorbike = view.inputString();

      view.show("Power: ");
      String power = view.inputString();

      return new Motorbike(model, manufacturer, year, price, origin,
          quantity, type, engine, typeMotorbike, power);
    }

    if (choice.equalsIgnoreCase("Bike")) {
      view.show("Bicycle type: ");
      String typeBike = view.inputString();

      view.show("Frame material: ");
      String frame = view.inputString();

      return new Bicycle(model, manufacturer, year, price, origin,
          quantity, type, frame, typeBike);
    }

    return null;
  }

  public void inputCustomers() {
    view.show("Enter number of customers: ");
    int numberOfCustomer = view.inputInt();
    view.inputString();

    for (int i = 0; i < numberOfCustomer; i++) {
      view.show("\nCustomer " + (i + 1));

      view.show("Name: ");
      String name = view.inputString();

      view.show("Address: ");
      String address = view.inputString();

      view.show("Phone: ");
      String phone = view.inputString();

      view.show("Balance: ");
      BigDecimal balance = view.inputBigDecimal();
      view.inputString();

      Customer customer = new Customer(name, address, phone, balance);
      dealership.addCustomer(customer);

      view.show("Customer added successfully.");
    }
  }

  private void buyVehicle() {
    if (dealership.getCustomers().isEmpty()
        || dealership.getInventory().isEmpty()) {
      view.show("Need at least 1 customer and 1 vehicle.");
      return;
    }

    showCustomers();
    view.show("Choose customer: ");
    int chooseCustomer = view.inputInt() - 1;
    view.inputString();

    if (chooseCustomer < 0 || chooseCustomer >= dealership.getCustomers().size()) {
      view.show("Invalid customer!");
      return;
    }

    Customer customer = dealership.getCustomers().get(chooseCustomer);

    showVehicles();
    view.show("Choose vehicle: ");
    int chooseVehicle = view.inputInt() - 1;
    view.inputString();

    if (chooseVehicle < 0 || chooseVehicle >= dealership.getInventory().size()) {
      view.show("Invalid vehicle!");
      return;
    }

    Vehicle vehicle = dealership.getInventory().get(chooseVehicle);

    if (dealership.sellVehicle(vehicle, customer)) {
      view.show("Purchase successful!");
    } else {
      view.show("Purchase failed!");
      suggestAlternatives(vehicle.getType());
    }
  }

  private void suggestAlternatives(String type) {
    ArrayList<Vehicle> alternatives = dealership.suggestAlternative(type);

    if (alternatives.isEmpty()) {
      view.show("No alternative available.");
      return;
    }

    view.show("Suggested alternatives:");
    for (int i = 0; i <  alternatives.size(); i++) {
      Vehicle vehicle = alternatives.get(i);
      view.show(vehicle.getModel() + " | Price: " + vehicle.finalPrice());
    }
  }
}
