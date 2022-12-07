package business;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class Validator {
    private  static DataAccess da = new DataAccessFacade();
    public static boolean isValidMember(String memberId){
        if(memberId == null) return false;
        return da.readMemberMap().containsKey(memberId);
    }
    public static boolean isValidIsbn(String isbn){
        if(isbn == null) return false;
        return da.readBooksMap().containsKey(isbn);
    }
}
