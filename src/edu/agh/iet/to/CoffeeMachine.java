package edu.agh.iet.to;


import edu.agh.iet.to.FSM.requests.Request;
import edu.agh.iet.to.FSM.states.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;

public class CoffeeMachine {

    @Autowired
    @Qualifier("coinsLimit")
    private int coinsLimit;

    @Autowired
    @Qualifier("coffeeLimit")
    private int coffeeLimit;

    private int coinsInserted = 0;
    private int coffeeCounter = 0;

    @Autowired
    @Qualifier("stateLookupTable")
    private HashMap<String, State> stateLookupTable;

    @Autowired
    @Qualifier("startState")
    private State currentState;


    public void handleRequest(Request request){
        if(currentState == null){
            throw new NullPointerException("CoffeeMachine state incomplete");
        }
        currentState.handleRequest(request);
        updateCurrentState();
    }

    public void increaseCoffeeCounter() {
        if(!canMakeAnotherCoffee()){
            throw new RuntimeException("Coffee counter increase not allowed!");
        }
        this.coffeeCounter ++;
    }

    public void resetCoffeeCounter() {
        this.coffeeCounter = 0;
    }

    public boolean canMakeAnotherCoffee(){
        return (coffeeCounter + 1 <= coffeeLimit);
    }

    public boolean canTakeAnotherCoin(){
        return coinsInserted < coinsLimit;
    }

    public boolean isCoinInsideMachine(){
        return coinsInserted > 0;
    }

    public void incrementAmountOfCoins(){
        coinsInserted++;
    }

    public void decrementAmountOfCoins(){
        coinsInserted--;
    }

    public void resetAmountOfCoins(){
        coinsInserted = 0;
    }

    private void updateCurrentState(){
        if(currentState == null){
            throw new NullPointerException("CoffeeMachine state incomplete");
        }
        if(!stateLookupTable.containsKey(currentState.getNextStateName())){
            throw new NullPointerException("CoffeeMachine configuration incomplete");
        }
        System.out.println("State changed at: " + currentState.getNextStateName());
        currentState = stateLookupTable.get(currentState.getNextStateName());
    }

}
