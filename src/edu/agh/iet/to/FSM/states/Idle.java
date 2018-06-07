package edu.agh.iet.to.FSM.states;


import edu.agh.iet.to.CoffeeMachine;
import edu.agh.iet.to.FSM.requests.ButtonPressed;
import edu.agh.iet.to.FSM.requests.CoinInserted;
import edu.agh.iet.to.FSM.requests.Request;
import edu.agh.iet.to.FSM.requests.ReturnCoins;

public class Idle extends State {

    public Idle(CoffeeMachine coffeeMachine) {
        super(coffeeMachine);
        nextStateName = Idle.class.getName();
    }

    @Override
    public void handleRequest(Request request) {
        if(request instanceof CoinInserted){
            handleCoinInsertedRequest();
        } else if(request instanceof ReturnCoins) {
            handleReturnCoinsRequest();
        } else if(request instanceof ButtonPressed) {
            handleButtonPressedRequest();
        } else {
            handleWrongRequest();
        }
    }

    private void handleCoinInsertedRequest(){
        if(coffeeMachine.canMakeAnotherCoffee()){
            tryToInsertCoin();
        } else {
            System.out.println("There is need edu.agh.iet.to refill coffee");
            nextStateName = NeedToRefill.class.getName();
        }

    }

    private void tryToInsertCoin(){
        if(coffeeMachine.canTakeAnotherCoin()){
            coffeeMachine.incrementAmountOfCoins();
            System.out.println("Got coin!");
        } else {
            System.out.println("Cannot insert more coins.");
        }
    }

    private void handleReturnCoinsRequest(){
        if(!coffeeMachine.isCoinInsideMachine()){
            System.out.println("No coins inside machine!");
        } else {
            returnCoins();
            coffeeMachine.resetAmountOfCoins();
        }
    }

    private void returnCoins(){
        while(coffeeMachine.isCoinInsideMachine()){
            System.out.println("Returning coin to user!");
            coffeeMachine.decrementAmountOfCoins();
        }
    }

    private void handleButtonPressedRequest(){
        if(coffeeMachine.isCoinInsideMachine()){
            System.out.println("Button pressed. Coffee is being made");
            coffeeMachine.increaseCoffeeCounter();
            coffeeMachine.resetAmountOfCoins();
            nextStateName = CoffeeMade.class.getName();
        } else {
            System.out.println("Cannot make coffee without having enough coin(s) inserted.");
        }
    }

}
