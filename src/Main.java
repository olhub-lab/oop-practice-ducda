
public class Main {
  public static void main(String[] args) {

    Dealership dealership = new Dealership("Dealership Duc");

    View view = new View();

    Controller controller = new Controller(dealership, view);

    controller.run();
  }
}
