package model;

import java.math.BigDecimal;
import java.util.List;

public class Customer {

  private int idCustomer;
  private String name;
  private String address;
  private String phoneNumber;
  private BigDecimal balance;
  private List<Vehicle> purchaseHistory;
  private static final int MIN_LEVEL_SILVER = 2;
  private static final int MAX_LEVEL_SILVER = 4;
  private static final int MIN_LEVEL_GOLD = 5;
  private static final int MAX_LEVEL_GOLD = 7;
  private static final int MIN_LEVEL_PLATINUM = 8;

  public Customer() {
  }

  public Customer(int idCustomer, String name, String address, String phoneNumber,
      BigDecimal balance) {
    this.idCustomer = idCustomer;
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.balance = balance;
  }


  public void depositMoney(BigDecimal amount) {
    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Amount must be greater than zero");
    }
    balance = balance.add(amount);
  }


  public LoyaltyLevel loyaltyLevel() {
    if (getOwnedVehiclesCount() >= MIN_LEVEL_SILVER
        && getOwnedVehiclesCount() <= MAX_LEVEL_SILVER) {
      return LoyaltyLevel.SILVER;
    } else if (getOwnedVehiclesCount() >= MIN_LEVEL_GOLD
        && getOwnedVehiclesCount() <= MAX_LEVEL_GOLD) {
      return LoyaltyLevel.GOLD;
    } else if (getOwnedVehiclesCount() >= MIN_LEVEL_PLATINUM) {
      return LoyaltyLevel.PLATINUM;
    }
    return LoyaltyLevel.REGULAR;
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

  public int getIdCustomer() {
    return idCustomer;
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

  public void setIdCustomer(int idCustomer) {
    this.idCustomer = idCustomer;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }
}
