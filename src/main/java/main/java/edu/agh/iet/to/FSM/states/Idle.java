package main.java.edu.agh.iet.to.FSM.states;


import main.java.edu.agh.iet.to.CoffeeMachine;
import main.java.edu.agh.iet.to.FSM.requests.ButtonPressed;
import main.java.edu.agh.iet.to.FSM.requests.Coin;
import main.java.edu.agh.iet.to.FSM.requests.Request;
import main.java.edu.agh.iet.to.FSM.requests.ReturnCoins;

public class Idle extends State {

    private final int coinsLimit;
    private int coinsInserted;

    public Idle(CoffeeMachine coffeeMachine, int coinsLimit) {
        super(coffeeMachine);
        coinsInserted = 0;
        this.coinsLimit = coinsLimit;
        nextStateName = Idle.class.getName();
    }

    @Override
    public void handleRequest(Request request) {
        if(request instanceof Coin){
            handleCoinRequest();
        } else if(request instanceof ReturnCoins) {
            handleReturnCoinsRequest();
        } else if(request instanceof ButtonPressed) {
            handleButtonPressedRequest();
        } else {
            handleWrongRequest();
        }
    }

    private void handleCoinRequest(){
        if(coffeeMachine.canMakeAnotherCoffee()){
            tryToInsertCoin();
        } else {
            System.out.println("There is need to refill coffee");
            nextStateName = NeedToRefill.class.getName();
        }

    }

    private void tryToInsertCoin(){
        if(canTakeAnotherCoin()){
            coinsInserted ++;
            System.out.println("Got coin!");
        } else {
            System.out.println("Cannot insert more coins.");
        }
    }

    private void handleReturnCoinsRequest(){
        if(coinsInserted == 0){
            System.out.println("No coins inside machine!");
        } else {
            returnCoins();
            coinsInserted = 0;
        }
    }

    private void returnCoins(){
        for(int i = 0; i < coinsInserted; i++){
            System.out.println("Returning coin to user!");
        }
    }

    private boolean canTakeAnotherCoin(){
        return coinsInserted < coinsLimit;
    }


    private void handleButtonPressedRequest(){
        if(isSufficientAmmountOfCoins()){
            System.out.println("Button pressed. Coffee is being made");
            coffeeMachine.increaseCoffeeCounter();
            coinsInserted = 0;
            nextStateName = CoffeeMade.class.getName();
        } else {
            System.out.println("Cannot make coffee without having enough coin(s) inserted.");
        }
    }

    private boolean isSufficientAmmountOfCoins(){
        return coinsInserted > 0;
    }
}
