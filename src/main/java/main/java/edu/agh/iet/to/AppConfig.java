package main.java.edu.agh.iet.to;

import main.java.edu.agh.iet.to.FSM.requests.*;
import main.java.edu.agh.iet.to.FSM.states.CoffeeMade;
import main.java.edu.agh.iet.to.FSM.states.Idle;
import main.java.edu.agh.iet.to.FSM.states.NeedToRefill;
import main.java.edu.agh.iet.to.FSM.states.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class AppConfig {

    @Autowired
    private ApplicationContext appContext;

    @Bean
    public CoffeeMachine getCoffeeMachine(){
        int coffeeLimit = 10;
        int coinsLimit = 2;
        CoffeeMachine coffeeMachine = new CoffeeMachine(coffeeLimit, coinsLimit);
        Idle idleState = new Idle(coffeeMachine);
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

    @Bean
    public CoffeeMachineAPI getCoffeeMachineAPI(){
        CoffeeMachine coffeeMachine = appContext.getBean(CoffeeMachine.class);
        APIConfig apiToRequestTable = appContext.getBean(APIConfig.class);
        return new CoffeeMachineAPI(coffeeMachine, apiToRequestTable);
    }
}
