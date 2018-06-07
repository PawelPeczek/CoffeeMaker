package edu.agh.iet.to.FSM.states;


import edu.agh.iet.to.FSM.requests.Request;
import edu.agh.iet.to.FSM.requests.TakeCoffee;
import org.springframework.stereotype.Component;

@Component
public class CoffeeMade extends State {

    public CoffeeMade() {
        nextStateName = CoffeeMade.class.getName();
    }

    @Override
    public void handleRequest(Request request) {
        if(request instanceof TakeCoffee){
            handleTakeCoffeeRequest();
        } else {
            handleWrongRequest();
        }
    }

    private void handleTakeCoffeeRequest(){
        System.out.println("Coffee taken.");
        nextStateName = Idle.class.getName();
    }
}
