import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Customer {

  private String name;
  private String address;
  private String phoneNumber;
  private BigDecimal balance;
  private List<Vehicle> purchaseHistory;
  private static final int LEVEL_SILVER = 2;
  private static final int LEVEL_GOLD = 4;
  private static final int LEVEL_PLATINUM = 6;

  public Customer(String name, String address, String phoneNumber, BigDecimal balance) {
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.balance = balance;
    this.purchaseHistory = new ArrayList<Vehicle>();
  }

//  public boolean buyVehicle(Vehicle vehicle) {
//    if (vehicle == null) {
//      return false;
//    }
//
//    BigDecimal totalPrice = vehicle.finalPrice();
//    if (balance.compareTo(totalPrice) >= 0) {
//      balance = balance.subtract(totalPrice);
//      purchaseHistory.add(vehicle);
//      getOwnedVehiclesCount();
//      return true;
//    } else {
//      System.out.println("The balance is insufficient to buy.");
//      return false;
//    }
//  }

  public void depositMonney(BigDecimal amount) {
    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
      System.out.println("The deposit amount must be greater than 0.");
      return;
    }
    balance = balance.add(amount);
    System.out.println("Deposit successful. Current balance:" + balance + " VND");
  }

//  public void addVehicle(Vehicle vehicle){
//    purchaseHistory.add(vehicle);
//  }


  public String loyaltyLevel() {
    if (purchaseHistory.size() >= LEVEL_PLATINUM) {
      return "Platinum";
    } else if (purchaseHistory.size() >= LEVEL_GOLD) {
      return "Gold";
    } else if (purchaseHistory.size() >= LEVEL_SILVER) {
      return "Silver";
    } else {
      return "Regular";
    }
  }


  public boolean enoughMoney(BigDecimal amount) {
    if (amount == null) {
      return false;
    }
    return balance.compareTo(amount) >= 0;
  }

  public void addPurchaseHistory(Vehicle vehicle) {
    purchaseHistory.add(vehicle);
  }

  public void minusBalance(BigDecimal amount) {
    balance = balance.subtract(amount);
  }

  public int getOwnedVehiclesCount() {
    return purchaseHistory.size();
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public List<Vehicle> getPurchaseHistory() {
    return purchaseHistory;
  }
}
