package edu.agh.iet.to.FSM.states;


import edu.agh.iet.to.CoffeeMachine;
import edu.agh.iet.to.FSM.requests.Refill;
import edu.agh.iet.to.FSM.requests.Request;
import org.springframework.stereotype.Component;

@Component
public class NeedToRefill extends State {

    public NeedToRefill() {
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
