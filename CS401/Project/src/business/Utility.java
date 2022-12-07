package business;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.*;
import librarysystem.Context;

public class Utility {
    private  static DataAccess da = new DataAccessFacade();
    public static boolean isValidMember(String memberId){
        if(memberId == null) return false;
        return da.readMemberMap().containsKey(memberId);
    }
    public static boolean isValidIsbn(String isbn){
        if(isbn == null) return false;
        return da.readBooksMap().containsKey(isbn);
    }
    public static boolean checkPermission(Auth auth)
            throws LoginException {
        if(auth == null) return false;
        return Context.getContext().getAuth() == auth;
    }
}
