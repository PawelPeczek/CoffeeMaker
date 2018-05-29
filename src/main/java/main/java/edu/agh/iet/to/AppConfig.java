package main.java.edu.agh.iet.to;

import main.java.edu.agh.iet.to.FSM.requests.*;
import main.java.edu.agh.iet.to.FSM.states.CoffeeMade;
import main.java.edu.agh.iet.to.FSM.states.Idle;
import main.java.edu.agh.iet.to.FSM.states.NeedToRefill;
import main.java.edu.agh.iet.to.FSM.states.State;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class AppConfig {
    @Bean
    public CoffeeMachine getCoffeeMachine(){
        int coffeLimit = 10;
        int coinsLimit = 2;
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.setCoffeeLimit(coffeLimit);
        Idle idleState = new Idle(coffeeMachine, coinsLimit);
        NeedToRefill needToRefill = new NeedToRefill(coffeeMachine);
        CoffeeMade coffeeMade = new CoffeeMade(coffeeMachine);
        HashMap<String, State> stateLookupTable = new HashMap<>();
        stateLookupTable.put(Idle.class.getName(), idleState);
        stateLookupTable.put(NeedToRefill.class.getName(), needToRefill);
        stateLookupTable.put(CoffeeMade.class.getName(), coffeeMade);
        coffeeMachine.setCurrentState(idleState);
        coffeeMachine.setStateLookupTable(stateLookupTable);
        return coffeeMachine;
    }

    @Bean
    public APIConfig getAPIToRequestTable(){
        APIConfig apiConfig = new APIConfig();
        apiConfig.addNameToRequestMapping("takeCoffee", new TakeCoffee());
        apiConfig.addNameToRequestMapping("returnCoins", new ReturnCoins());
        apiConfig.addNameToRequestMapping("refillCoffee", new Refill());
        apiConfig.addNameToRequestMapping("pressButton", new ButtonPressed());
        apiConfig.addNameToRequestMapping("putCoin", new Coin());
        return apiConfig;
    }
}
