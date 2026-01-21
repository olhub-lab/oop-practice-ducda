import java.math.BigDecimal;
import java.util.Scanner;

public class View {
  private Scanner sc = new Scanner(System.in);

  public String inputString() {
    return sc.nextLine();
  }

  public int inputInt() {
    return sc.nextInt();
  }

  public BigDecimal inputBigDecimal() {
    return sc.nextBigDecimal();
  }

  public void show(String message) {
    System.out.println(message);
  }

}
