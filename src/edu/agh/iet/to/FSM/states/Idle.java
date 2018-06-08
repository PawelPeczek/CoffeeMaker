package edu.agh.iet.to.FSM.states;


import edu.agh.iet.to.FSM.requests.ButtonPressed;
import edu.agh.iet.to.FSM.requests.CoinInserted;
import edu.agh.iet.to.FSM.requests.Request;
import edu.agh.iet.to.FSM.requests.ReturnCoins;

public class Idle extends State {

    public Idle() {
        setInitialNextState();
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

    @Override
    protected void setInitialNextState() {
        nextStateName = Idle.class.getName();
    }

    private void handleCoinInsertedRequest(){
        if(coffeeMachine.canMakeAnotherCoffee()){
            System.out.println("Can make another coffee");
            tryToInsertCoin();
            setInitialNextState();
        } else {
            System.out.println("There is need edu.agh.iet.to refill coffee");
            nextStateName = NeedToRefill.class.getName();
        }

    }

    private void tryToInsertCoin(){
        if(coffeeMachine.canTakeAnotherCoin()){
            coffeeMachine.incrementNoOfInsertedCoins();
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
            coffeeMachine.resetNoOfInsertedCoins();
        }
        setInitialNextState();
    }

    private void returnCoins(){
        while(coffeeMachine.isCoinInsideMachine()){
            System.out.println("Returning coin to user!");
            coffeeMachine.decrementNoOfInsertedCoins();
        }
    }

    private void handleButtonPressedRequest(){
        if(coffeeMachine.isCoinInsideMachine()){
            System.out.println("Button pressed. Coffee is being made");
            coffeeMachine.increaseCoffeeCounter();
            coffeeMachine.resetNoOfInsertedCoins();
            nextStateName = CoffeeMade.class.getName();
        } else {
            System.out.println("Cannot make coffee without having enough coin(s) inserted.");
        }
    }

}
