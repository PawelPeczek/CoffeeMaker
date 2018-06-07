package edu.agh.iet.to;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

public class CoffeeMachineAPI {

    @Autowired
    private CoffeeMachine coffeeMachine;
    @Autowired
    private APIConfig apiConfig;


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
