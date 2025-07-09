package InventoryManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ui.MainMenu;

@SpringBootApplication
@ComponentScan(basePackages = {"InventoryManager", "service", "ui", "data"})

public class InventoryApp {

    public static void main(String[] args) {
        // Bootstraps the Spring container
        ApplicationContext context = SpringApplication.run(InventoryApp.class, args);

        // Get MainMenu from Spring's ApplicationContext
        MainMenu menu = context.getBean(MainMenu.class);

        // Run the main menu
        menu.run();
    }
}
