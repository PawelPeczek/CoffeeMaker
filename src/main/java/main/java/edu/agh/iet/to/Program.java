package main.java.edu.agh.iet.to;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Program {
    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CoffeeMachine coffeeMachine = context.getBean(CoffeeMachine.class);
        APIConfig apiToRequestTable = context.getBean(APIConfig.class);
        UserInterface userInterface = new UserInterface(coffeeMachine, apiToRequestTable);
        userInterface.startCommunicationLoop();
    }
}
