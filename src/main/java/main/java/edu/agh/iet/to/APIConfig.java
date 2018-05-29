package main.java.edu.agh.iet.to;

import main.java.edu.agh.iet.to.FSM.requests.Request;

import java.util.HashMap;
import java.util.Set;

public class APIConfig {
    private final HashMap<String, Request> apiToRequestTable = new HashMap<>();

    public boolean isValidRequest(String requestName){
        return apiToRequestTable.containsKey(requestName);
    }

    public void addNameToRequestMapping(String name, Request request){
        apiToRequestTable.put(name, request);
    }

    public Request getRequestForGivenName(String name){
        return apiToRequestTable.get(name);
    }

    public Set<String> getAllRequestsNames(){
        return apiToRequestTable.keySet();
    }
}
