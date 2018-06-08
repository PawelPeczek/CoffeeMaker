package edu.agh.iet.to;

import edu.agh.iet.to.FSM.requests.*;
import edu.agh.iet.to.FSM.states.CoffeeMade;
import edu.agh.iet.to.FSM.states.Idle;
import edu.agh.iet.to.FSM.states.NeedToRefill;
import edu.agh.iet.to.FSM.states.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class AppConfig {

    @Bean(name="coffeeLimit")
    public int getCoffeeLimit(){
        return 4;
    }

    @Bean(name="coinsLimit")
    public int getCoinsLimit(){
        return 2;
    }

    @Bean(name="apiToRequestTable")
    public HashMap<String, Request> getAPIToRequestTable(){
        HashMap<String, Request> result = new HashMap<>();
        result.put("takeCoffee", new TakeCoffee());
        result.put("returnCoins", new ReturnCoins());
        result.put("refillCoffee", new Refill());
        result.put("pressButton", new ButtonPressed());
        result.put("putCoin", new CoinInserted());
        return result;
    }

    @Bean(name="startState")
    public Idle getIdleState(){
        return new Idle();
    }

    @Bean
    public NeedToRefill getNeedToRefillState(){
        return new NeedToRefill();
    }

    @Bean
    public CoffeeMade getCoffeeMadeState(){
        return new CoffeeMade();
    }


    @Bean(name="stateLookupTable")
    @Autowired
    public HashMap<String, State> getStateLookupTable(Idle idleState, NeedToRefill needToRefill, CoffeeMade coffeeMade){
        HashMap<String, State> stateLookupTable = new HashMap<>();
        stateLookupTable.put(Idle.class.getName(), idleState);
        stateLookupTable.put(NeedToRefill.class.getName(), needToRefill);
        stateLookupTable.put(CoffeeMade.class.getName(), coffeeMade);
        return stateLookupTable;
    }

    @Bean(name="CoffeeMachine")
    public CoffeeMachine getCoffeeMachine(){
        return new CoffeeMachine();
    }

    @Bean(name="APIConfig")
    public APIConfig getAPIConfig(){
        return new APIConfig();
    }

    @Bean()
    public CoffeeMachineAPI getCoffeeMachineAPI(){
        return new CoffeeMachineAPI();
    }

    @Bean
    public UserInterface getUserInterface(){
        return new UserInterface();
    }

}
