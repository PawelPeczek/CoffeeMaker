package edu.agh.iet.to.FSM.states;


import edu.agh.iet.to.CoffeeMachine;
import edu.agh.iet.to.FSM.requests.Request;
import edu.agh.iet.to.FSM.requests.TakeCoffee;


public class CoffeeMade extends State {

    public CoffeeMade(CoffeeMachine coffeeMachine) {
        super(coffeeMachine);
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
