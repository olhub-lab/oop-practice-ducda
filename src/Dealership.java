import java.util.ArrayList;

public class Dealership {

  private String name;
  private ArrayList<Vehicle> inventory = new ArrayList<>();
  private ArrayList<Customer> customers = new ArrayList<>();

  public Dealership(String name) {
    this.name = name;
    this.inventory = new ArrayList<>();
    this.customers = new ArrayList<>();
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

  public boolean sellVehicle(Vehicle vehicle, Customer customer) {
    if (!vehicle.checkInventory()) {
      System.out.println("Vehicle Not Enough");
      return false;
    }
    if(!customer.enoughMoney(vehicle.finalPrice())) {
      System.out.println("Customer Not Enough");
      return false;
    }
    vehicle.minusQuantity();

    customer.minusBalance(vehicle.finalPrice());
    customer.addPurchaseHistory(vehicle);

    System.out.println(customer.loyaltyLevel());

    return true;
  }

  public ArrayList<Vehicle> suggestAlternative(String vehicleType) {
    ArrayList<Vehicle> alternatives = new ArrayList<>();

    for (Vehicle vehicle : inventory) {

      if (vehicle.getType().equals(vehicleType) && vehicle.checkInventory()) {
        alternatives.add(vehicle);
      }
    }

    return alternatives;
  }

}
