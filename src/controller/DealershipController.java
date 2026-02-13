package controller;

import exceptions.BaseException;
import java.math.BigDecimal;
import java.util.List;
import model.Customer;
import model.Vehicle;
import service.DealershipService;
import view.DealershipView;

public class DealershipController {

  private DealershipService dealership;
  private DealershipView view;

  public DealershipController(DealershipService dealership, DealershipView view) {
    this.dealership = dealership;
    this.view = view;
  }

  public void run() {
    while (true) {
      int choice = view.showMenu();

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
//        case 5:
//          buyVehicle();
//          break;
        case 0:
          view.show("bye");
          return;
        default:
          view.show("Invalid choice");
      }
    }
  }

  private void showCustomers() {
    view.showCustomers(dealership.getAllCustomers());
  }

  private void showVehicles() {
    view.showVehicles(dealership.getAllVehicles());
  }


  private void inputVehicles() {
    view.show("Enter number of vehicles: ");
    int numberOfVehicle = view.inputInt();

    for (int i = 0; i < numberOfVehicle; i++) {
      view.show("\nVehicle " + (i + 1));

      view.show("ID Vehicle: ");
      int idVehicle = view.inputInt();

      view.show("Choose type (Car/Motorbike/Bike): ");
      String type = view.inputString();

      view.show("Model: ");
      String model = view.inputString();

      view.show("Manufacturer: ");
      String manufacturer = view.inputString();

      view.show("Year: ");
      int year = view.inputInt();

      view.show("Base price: ");
      BigDecimal basePrice = view.inputBigDecimal();

      view.show("Origin: ");
      String origin = view.inputString();

      view.show("Quantity: ");
      int quantity = view.inputInt();

      dealership.createVehicle(idVehicle, type, model, manufacturer, year, basePrice, origin,
          quantity, view);

    }
  }

  private void inputCustomers() {
    view.show("Enter number of customers: ");
    int numberOfCustomer = view.inputInt();

    for (int i = 0; i < numberOfCustomer; i++) {
      view.show("\nCustomer " + (i + 1));

      view.show("ID Customer: ");
      int idCustomer = view.inputInt();

      view.show("Name: ");
      String name = view.inputString();

      view.show("Address: ");
      String address = view.inputString();

      view.show("Phone: ");
      String phone = view.inputString();

      view.show("Balance: ");
      BigDecimal balance = view.inputBigDecimal();

      dealership.createCustomer(idCustomer, name, address, phone, balance);

      view.show("Customer added successfully.");
    }
  }

//  private void buyVehicle() {
//    try {
//      List<Customer> customers = dealership.getAllCustomers();
//      List<Vehicle> vehicles = dealership.getAllVehicles();
//
//      int chooseCustomer = view.chooseCustomer(customers);
//      int chooseVehicle = view.chooseVehicle(vehicles);
//
//      Customer customer = customers.get(chooseCustomer);
//      Vehicle vehicle = vehicles.get(chooseVehicle);
//
//      dealership.buyVehicle(customer.getIdCustomer(), vehicle.getIdVehicle());
//
//      view.show("Purchase successful!");
//
//    } catch (BaseException e) {
//      view.show("Purchase failed: " + e.getMessage());
//    }
//  }

//  private void suggestAlternatives(String type) {
//    List<Vehicle> alternatives = dealership.suggestAlternatives(type);
//    view.showSuggestedVehicles(alternatives);
//  }
}
