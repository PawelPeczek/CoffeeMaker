package main.java.edu.agh.iet.to.FSM.states;

import main.java.edu.agh.iet.to.CoffeeMachine;
import main.java.edu.agh.iet.to.FSM.requests.Refill;
import main.java.edu.agh.iet.to.FSM.requests.Request;

public class NeedToRefill extends State {

    public NeedToRefill(CoffeeMachine coffeeMachine) {
        super(coffeeMachine);
        this.nextStateName = NeedToRefill.class.getName();
    }

    @Override
    public void handleRequest(Request request) {
        if(request instanceof Refill){
            handleRefillRequest();
        } else {
            handleWrongRequest();
        }
    }

    private void handleRefillRequest(){
        System.out.println("Got coffee refill request!");
        if(!coffeeMachine.canMakeAnotherCoffee()){
            coffeeMachine.resetCoffeeCounter();
        } else {
            this.nextStateName = Idle.class.getName();
        }
    }
}
