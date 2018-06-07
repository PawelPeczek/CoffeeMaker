package main.java.edu.agh.iet.to;


import main.java.edu.agh.iet.to.FSM.requests.Request;
import main.java.edu.agh.iet.to.FSM.states.State;

import java.util.HashMap;

public class CoffeeMachine {

    private int coinsLimit;
    private int coinsInserted = 0;
    private int coffeeCounter = 0;
    private int coffeeLimit;
    private HashMap<String, State> stateLookupTable;
    private State currentState;

    public CoffeeMachine(int coffeeLimit, int coinsLimit){
        this.coffeeLimit = coffeeLimit;
        this.coinsLimit = coinsLimit;
    }

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

    private void updateCurrentState(){
        if(currentState == null){
            throw new NullPointerException("CoffeeMachine state incomplete");
        }
        if(!stateLookupTable.containsKey(currentState.getNextStateName())){
            throw new NullPointerException("CoffeeMachine configuration incomplete");
        }
        currentState = stateLookupTable.get(currentState.getNextStateName());
    }

    public void setStateLookupTable(HashMap<String, State> stateLookupTable) {
        this.stateLookupTable = stateLookupTable;
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

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

}
