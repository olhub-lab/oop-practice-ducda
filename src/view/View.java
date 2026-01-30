package view;

import java.math.BigDecimal;
import java.util.Scanner;

public class View {
  private Scanner sc = new Scanner(System.in);

  public String inputString() {
    return sc.nextLine();
  }

  public int inputInt() {
    while (true) {
      try {
        return Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid number. Please enter again: ");
      }
    }
  }


  public BigDecimal inputBigDecimal() {
    while (true) {
      try {
        return new BigDecimal(sc.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid amount. Please enter again: ");
      }
    }
  }

  public void show(String message) {
    System.out.println(message);
  }

}
