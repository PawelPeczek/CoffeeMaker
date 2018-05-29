package main.java.edu.agh.iet.to;

import main.java.edu.agh.iet.to.FSM.requests.Request;

import java.util.ArrayList;
import java.util.HashMap;

public class CoffeeMachineAPI {

    private final CoffeeMachine coffeeMachine;
    private final APIConfig apiConfig;

    public CoffeeMachineAPI(CoffeeMachine coffeeMachine, APIConfig apiConfig){
        this.coffeeMachine = coffeeMachine;
        this.apiConfig = apiConfig;
    }

    public void pressButton() {
        fireEvent("pressButton");
    }

    public void putCoin() {
        fireEvent("putCoin");
    }

    public void refillCoffee() {
        fireEvent("refillCoffee");
    }

    public void returnCoins() {
        fireEvent("returnCoins");
    }

    public void takeCoffee() {
        fireEvent("takeCoffee");
    }

    public void fireEvent(String apiFunName){
        if(!apiConfig.isValidRequest(apiFunName)){
            throw new RuntimeException("No function in API");
        }
        coffeeMachine.handleRequest(apiConfig.getRequestForGivenName(apiFunName));
    }

    public ArrayList<String> getAPIFunctionsNames(){
        return new ArrayList<>(apiConfig.getAllRequestsNames());
    }

}
