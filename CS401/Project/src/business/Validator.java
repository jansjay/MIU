package business;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class Validator {
    private  static DataAccess da = new DataAccessFacade();
    public static boolean validateMemberId(String memberId){
        if(memberId == null) return false;
        return da.readMemberMap().containsKey(memberId);
    }
    public static boolean validateIsbn(String isbn){
        if(isbn == null) return false;
        return da.readBooksMap().containsKey(isbn);
    }
}
