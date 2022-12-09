package business;

import java.util.List;

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
    
    public static boolean validateBookTitle(String title){
        if( title == null || title.isEmpty()) return false;
       return true;
    }
    
    public static boolean validateBookAuthors(List<Author> authors){
        if( authors == null || authors.size() == 0) return false;
       return true;
    }
    
    public static boolean validateBookCopies(int copies){
        if( copies <= 0 ) return false;
       return true;
    }
    
    public static boolean validateBookCheckoutLength(int len){
        if( len == 7 || len == 21) return true;
       return false;
    }
    
    public static boolean isEmpty(String text) {
    	return text.isEmpty();
    }
    
    public static boolean isValidNumber(String text) {
    	try {
    		Double.parseDouble(text);
    	}
    	catch(NumberFormatException e) {
    		return false;
    	}
    	return true;
    }
    
    
}
