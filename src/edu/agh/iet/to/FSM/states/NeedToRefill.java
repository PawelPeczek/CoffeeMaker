package edu.agh.iet.to.FSM.states;


import edu.agh.iet.to.CoffeeMachine;
import edu.agh.iet.to.FSM.requests.Refill;
import edu.agh.iet.to.FSM.requests.Request;
import org.springframework.stereotype.Component;

public class NeedToRefill extends State {


    public NeedToRefill(){
        setInitialNextState();
    }

    @Override
    public void handleRequest(Request request) {
        if(request instanceof Refill){
            handleRefillRequest();
        } else {
            handleWrongRequest("In this cas you have to refill coffee");
        }
    }

    protected void setInitialNextState(){
        this.nextStateName = NeedToRefill.class.getName();
        System.out.println("Coffee refilled");
    }

    private void handleRefillRequest(){
        coffeeMachine.resetCoffeeCounter();
        this.nextStateName = Idle.class.getName();
    }
}
