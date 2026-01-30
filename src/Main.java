import controller.Controller;
import model.Dealership;
import service.DealershipService;
import view.View;

public class Main {
  public static void main(String[] args) {

    Dealership dealership = new Dealership("Dealership Duc");

    DealershipService dealershipService = new DealershipService(dealership);

    View view = new View();

    Controller controller = new Controller(dealershipService, view);

    controller.run();
  }
}
