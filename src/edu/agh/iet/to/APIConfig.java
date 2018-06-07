package edu.agh.iet.to;

import edu.agh.iet.to.FSM.requests.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Set;

public class APIConfig {
    @Autowired
    @Qualifier("apiToRequestTable")
    private final HashMap<String, Request> apiToRequestTable = new HashMap<>();

    public boolean isValidRequest(String requestName){
        return apiToRequestTable.containsKey(requestName);
    }

    public Request getRequestForGivenName(String name){
        return apiToRequestTable.get(name);
    }

    public Set<String> getAllRequestsNames(){
        return apiToRequestTable.keySet();
    }
}
