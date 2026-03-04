package exceptions;

import java.util.List;
import model.Vehicle;

public class InsufficientBalanceException extends BaseException {

  private List<Vehicle> suggestedVehicles;

  public InsufficientBalanceException(String message, List<Vehicle> suggestedVehicles) {
    super(message);
    this.suggestedVehicles = suggestedVehicles;
  }

  public List<Vehicle> getSuggestedVehicles() {
    return suggestedVehicles;
  }
}
