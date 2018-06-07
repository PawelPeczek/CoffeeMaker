package edu.agh.iet.to;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Program {
    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserInterface userInterface = context.getBean(UserInterface.class);
        userInterface.startCommunicationLoop();
    }
}
