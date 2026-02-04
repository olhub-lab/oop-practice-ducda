package service;

import exceptions.CustomerNotFoundException;
import exceptions.InsufficientBalanceException;
import exceptions.OutOfStockException;
import exceptions.VehicleNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Dealership;
import model.Vehicle;
import view.DealershipView;

public class DealershipService {

  private Dealership dealership;

  public DealershipService(Dealership dealership) {
    this.dealership = dealership;
  }

  public void addCustomer(String name, String address,
      String phone, BigDecimal balance) {
    Customer customer = new Customer(name, address, phone, balance);
    dealership.addCustomer(customer);
  }

  public List<Customer> getCustomers() {
    return dealership.getCustomers();
  }

  public void creatVehicle(
      String type,      String model,
      String manufacturer,
      int year,
      BigDecimal basePrice,
      String origin,
      int quantity,
      DealershipView view
  ) {
    Vehicle vehicle = null;

    if (type.equalsIgnoreCase("Car")) {
      view.show("Seat: ");
      int seat = view.inputInt();

      view.show("Fuel: ");
      String fuel = view.inputString();

      view.show("Engine capacity: ");
      int engineCapacity = view.inputInt();

      view.show("Body type: ");
      String bodyType = view.inputString();

      vehicle = VehicleFactory.createCar(model, manufacturer, year, basePrice,
          origin, quantity, type, seat,
          fuel, engineCapacity, bodyType);

    } else if (type.equalsIgnoreCase("Motorbike")) {
      view.show("Engine capacity: ");
      int engineCapacity = view.inputInt();

      view.show("Motorbike type: ");
      String typeMotorbike = view.inputString();

      view.show("Power: ");
      String power = view.inputString();

      vehicle = VehicleFactory.createMotorbike(
          model, manufacturer, year, basePrice,
          origin, quantity, type,
          engineCapacity, typeMotorbike, power);

    } else if (type.equalsIgnoreCase("Bike")) {
      view.show("Bicycle type: ");
      String typeBicycle = view.inputString();

      view.show("Frame material: ");
      String frameMaterial = view.inputString();

      vehicle = VehicleFactory.createBicycle(
          model, manufacturer, year, basePrice,
          origin, quantity, type,
          frameMaterial, typeBicycle);

    }
    if (vehicle == null) {
      throw new IllegalArgumentException("Invalid vehicle type");
    }

    addVehicle(vehicle);
  }

  private void addVehicle(Vehicle vehicle) {
    dealership.addVehicle(vehicle);
  }

  public List<Vehicle> getInventory() {
    return dealership.getInventory();
  }

  public void buyVehicle(int chooseCustomer, int chooseVehicle) {

    List<Customer> customers = dealership.getCustomers();
    List<Vehicle> inventory = dealership.getInventory();

    if (chooseCustomer < 0 || chooseCustomer >= customers.size()) {
      throw new CustomerNotFoundException("Customer not found");
    }

    if (chooseVehicle < 0 || chooseVehicle >= inventory.size()) {
      throw new VehicleNotFoundException("Vehicle not found");
    }

    Customer customer = customers.get(chooseCustomer);
    Vehicle vehicle = inventory.get(chooseVehicle);

    if (vehicle.getQuantity() <= 0) {
      throw new OutOfStockException("Vehicle out of stock");
    }

    if (!customer.enoughMoney(vehicle.finalPrice())) {
      throw new InsufficientBalanceException("Not enough balance");
    }

    customer.minusBalance(vehicle.finalPrice());
    vehicle.minusQuantity();
    customer.addPurchaseHistory(vehicle);
  }

  public ArrayList<Vehicle> suggestAlternatives(String type) {
    return dealership.suggestAlternative(type);
  }
}
