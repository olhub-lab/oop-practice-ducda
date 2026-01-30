package controller;

import exceptions.BaseException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Vehicle;
import service.DealershipService;
import service.VehicleFactory;
import view.View;

public class Controller {

  private DealershipService dealership;
  private View view;

  public Controller(DealershipService dealership, View view) {
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
    List<Customer> customers = dealership.getCustomers();

    if (customers.isEmpty()) {
      view.show("No customers available");
      return;
    }

    view.show("\n--- Customer List ---");
    for (int i = 0; i < customers.size(); i++) {
      Customer customer = dealership.getCustomers().get(i);
      view.show((i + 1) + ". " + customer.getName()
          + " | Address: " + customer.getAddress()
          + " | Phone: " + customer.getPhoneNumber()
          + " | Balance: " + customer.getBalance());
    }
  }

  private void showVehicles() {
    List<Vehicle> vehicles =dealership.getInventory();

    if (vehicles.isEmpty()) {
      view.show("No vehicles in inventory.");
      return;
    }

    view.show("\n--- Vehicle Inventory ---");
    for (int i = 0; i < vehicles.size(); i++) {
      Vehicle vehicle = vehicles.get(i);
      view.show((i + 1) + ". " + vehicle.getModel()
          + " | Type: " + vehicle.getType()
          + " | Price: " + vehicle.finalPrice()
          + " | Quantity: " + vehicle.getQuantity());
    }
  }

  private void inputVehicles() {
    view.show("Enter number of vehicles: ");
    int numberOfVehicle = view.inputInt();

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

      view.show("Base price: ");
      BigDecimal price = view.inputBigDecimal();

      view.show("Origin: ");
      String origin = view.inputString();

      view.show("Quantity: ");
      int quantity = view.inputInt();

      view.show("Vehicle type: ");
      String type = view.inputString();

      Vehicle vehicle = null;

      if (choice.equalsIgnoreCase("Car")) {
        view.show("Seat: ");
        int seat = view.inputInt();


        view.show("Fuel: ");
        String fuel = view.inputString();

        view.show("Engine capacity: ");
        int engine = view.inputInt();


        view.show("Body type: ");
        String bodyType = view.inputString();

        vehicle = VehicleFactory.createCar(model, manufacturer, year, price,
            origin, quantity, type, seat,
            fuel, engine, bodyType);
      } else if (choice.equalsIgnoreCase("Motorbike")) {
        view.show("Engine capacity: ");
        int engine = view.inputInt();

        view.show("Motorbike type: ");
        String typeMotorbike = view.inputString();

        view.show("Power: ");
        String power = view.inputString();

        vehicle = VehicleFactory.createMotorbike(
            model, manufacturer, year, price,
            origin, quantity, type,
            engine, typeMotorbike, power);
      } else if (choice.equalsIgnoreCase("Bike")) {
        view.show("Bicycle type: ");
        String typeBike = view.inputString();

        view.show("Frame material: ");
        String frame = view.inputString();

        vehicle = VehicleFactory.createBicycle(
            model, manufacturer, year, price,
            origin, quantity, type,
            frame, typeBike);
      }

      if (vehicle != null) {
        dealership.addVehicle(vehicle);
        view.show("Vehicle added.");
      } else {
        view.show("Invalid vehicle type.");
      }
    }
  }
  private void inputCustomers() {
    view.show("Enter number of customers: ");
    int numberOfCustomer = view.inputInt();

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

      dealership.addCustomer(name, address, phone, balance);

      view.show("Customer added successfully.");
    }
  }

  private void buyVehicle() {
    try {
      showCustomers();
      view.show("Choose customer: ");
      int chooseCustomer = view.inputInt() - 1;

      showVehicles();
      view.show("Choose vehicle: ");
      int chooseVehicle = view.inputInt() - 1;

      dealership.buyVehicle(chooseCustomer, chooseVehicle);

      view.show("Purchase successful!");

    } catch (BaseException e) {
      view.show("Purchase failed: " + e.getMessage());
    }
  }


  private void suggestAlternatives(String type) {
    ArrayList<Vehicle> alternatives = dealership.suggestAlternatives(type);

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
