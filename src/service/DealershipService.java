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

  public void addVehicle(Vehicle vehicle) {
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
