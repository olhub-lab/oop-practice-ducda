package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Customer {

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

  public Customer(String name, String address, String phoneNumber, BigDecimal balance) {
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.balance = balance;
    this.purchaseHistory = new ArrayList<Vehicle>();
  }


  public void depositMoney(BigDecimal amount) {
    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Amount must be greater than zero");
    }
    balance = balance.add(amount);
  }



  LoyaltyLevel loyaltyLevel() {
    if (getOwnedVehiclesCount() >= MIN_LEVEL_SILVER && getOwnedVehiclesCount() <= MAX_LEVEL_SILVER) {
      return LoyaltyLevel.SILVER;
    }else if (getOwnedVehiclesCount()>=MIN_LEVEL_GOLD && getOwnedVehiclesCount() <= MAX_LEVEL_GOLD) {
      return LoyaltyLevel.GOLD;
    } else if (getOwnedVehiclesCount() >= MIN_LEVEL_PLATINUM)  {
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
