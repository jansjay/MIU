package business;

import librarysystem.Context;

public abstract class BaseController {

    public boolean Authorize() throws LoginException{
        return Utility.checkPermission(Context.getContext().getAuth());
    }
}
