package edu.agh.iet.to;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    @Autowired
    private CoffeeMachineAPI coffeeMachineAPI;

    public void startCommunicationLoop(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> functions = coffeeMachineAPI.getAPIFunctionsNames();
        do {
            System.out.println("\n\n\n\nChoose option.");
            System.out.println("0. Quit");
            int optNo = 1;
            for (String apiFun : functions) {
                System.out.println(optNo + ". " + apiFun);
                optNo ++;
            }
            int chosenOpt = scanner.nextInt();
            if(isValidOptNumber(chosenOpt, optNo)) {
                coffeeMachineAPI.fireEvent(functions.get(chosenOpt - 1));
            } else if(isQuitOption(chosenOpt)) {
              break;
            } else {
                System.out.println("Invalid option");
            }
        } while(true);
    }

    private boolean isValidOptNumber(int choosenOptNo, int maxNo){
        return choosenOptNo > 0 && choosenOptNo < maxNo;
    }

    private boolean isQuitOption(int choosenOpt){
        return choosenOpt == 0;
    }
}
