import controller.DealershipController;
import model.Dealership;
import service.DealershipService;
import view.DealershipView;

public class Main {
  public static void main(String[] args) {

    Dealership dealership = new Dealership("Dealership Duc");

    DealershipService dealershipService = new DealershipService(dealership);

    DealershipView view = new DealershipView();

    DealershipController controller = new DealershipController(dealershipService, view);

    controller.run();
  }
}
