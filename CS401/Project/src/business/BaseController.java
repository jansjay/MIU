package business;

import dataaccess.Operation;
import dataaccess.Permission;
import librarysystem.Context;

public abstract class BaseController {
    public boolean Authorize(Operation operation) throws LoginException{
        if(operation == null) return false;
        if(!Permission.getOperationMap().containsKey(operation)) return false;
        return Permission.getOperationMap().get(operation) == Context.getContext().getAuth();
    }
}
