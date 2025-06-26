import ui.Controller;
import ui.View;
import data.EncounterFileRepository;
import domain.EncounterService;

public class App {
    public static void main(String[] args) {
        // Setup repository
        String filePath = "./data/encounters.csv";
        EncounterFileRepository repository = new EncounterFileRepository(filePath);

        // Setup service and view
        EncounterService service = new EncounterService(repository);
        View view = new View();

        // Setup controller with injected dependencies
        Controller controller = new Controller(view, service);

        // Run the app
        controller.run();
    }
}
