package InventoryManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.InventoryService;
import ui.MainMenu;

public class InventoryApp {
    public static void main(String[] args) {

        // Load Spring context from XML configuration
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        // Get the InventoryService bean that Spring autowired
        InventoryService service = context.getBean(InventoryService.class);

        // Launch the main menu with the wired service
        MainMenu menu = new MainMenu(service);
        menu.run();
    }
}
