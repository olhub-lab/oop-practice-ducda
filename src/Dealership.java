import java.util.ArrayList;

public class Dealership {

  private String name;
  private ArrayList<Vehicle> inventory = new ArrayList<>();
  private ArrayList<Customer> customers = new ArrayList<>();

  public Dealership(String name) {
    this.name = name;
  }

  public void addVehicle(Vehicle vehicle) {
    inventory.add(vehicle);
  }

  public void addCustomer(Customer customer) {
    customers.add(customer);
  }

  public Vehicle findVehicleByModel(String model) {
    for (Vehicle vehicle : inventory) {
      if (vehicle.getModel().equals(model)) {
        return vehicle;
      }
    }
    return null;
  }

  public String getName() {
    return name;
  }

  public ArrayList<Customer> getCustomers() {
    return customers;
  }

  public ArrayList<Vehicle> getInventory() {
    return inventory;
  }
}
