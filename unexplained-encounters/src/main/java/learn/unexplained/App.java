package learn.unexplained;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import learn.unexplained.ui.Controller;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("di.xml");
        Controller controller = context.getBean(Controller.class);
        controller.run();
    }
}





