package edu.agh.iet.to.FSM.states;

import edu.agh.iet.to.CoffeeMachine;
import edu.agh.iet.to.FSM.requests.Request;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class State {

    @Autowired
    protected CoffeeMachine coffeeMachine;

    protected String nextStateName;


    public abstract void handleRequest(Request request);

    protected abstract void setInitialNextState();

    public String getNextStateName() {
        return nextStateName;
    }

    protected void handleWrongRequest(){
        System.out.println("Unable to handle this request at the moment!");
        setInitialNextState();
    }

    protected void handleWrongRequest(String message){
        System.out.println(message);
        setInitialNextState();
    }
}
