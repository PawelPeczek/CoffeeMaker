package main.java.edu.agh.iet.to.FSM.states;

import main.java.edu.agh.iet.to.CoffeeMachine;
import main.java.edu.agh.iet.to.FSM.requests.Request;

public abstract class State {

    protected final CoffeeMachine coffeeMachine;
    protected String nextStateName;

    public State(CoffeeMachine coffeeMachine) {
        if(coffeeMachine == null){
            throw new NullPointerException("Cannot initialize state of coffee machine without machine");
        }
        this.coffeeMachine = coffeeMachine;
    }

    public abstract void handleRequest(Request request);

    public String getNextStateName() {
        return nextStateName;
    }

    protected void handleWrongRequest(){
        System.out.println("Unable to handle this request at the moment!");
    }
}
