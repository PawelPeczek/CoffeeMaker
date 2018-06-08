package edu.agh.iet.to.FSM.states;


import edu.agh.iet.to.FSM.requests.Request;
import edu.agh.iet.to.FSM.requests.TakeCoffee;
import org.springframework.stereotype.Component;

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

    @Override
    protected void setInitialNextState() {
        nextStateName = CoffeeMade.class.getName();
    }

    private void handleTakeCoffeeRequest(){
        System.out.println("Coffee taken.");
        nextStateName = Idle.class.getName();
        System.out.println("CoffeeMade set nextStateName to: " + nextStateName);
    }


}
