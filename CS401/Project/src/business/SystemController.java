package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.*;

public class SystemController extends BaseController implements ControllerInterface  {
	public static Auth currentAuth = null;
	DataAccess da;
	public SystemController(){
		da = new DataAccessFacade();
	}

	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}
	@Override
	public List<String> allMemberIds() {
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	@Override
	public void checkOutBookCopy(String memberId, String isbn) throws LoginException {
		if(!super.Authorize(Operation.CheckoutBook)) throw new LoginException("UnAuthorized Access");
		if(!Validator.isValidMember(memberId)) throw new IllegalArgumentException("Invalid member ID");
		if(!Validator.isValidIsbn(isbn)) throw new IllegalArgumentException("Invalid ISBN");
		BorrowBook borrowBook = BorrowBook.borrowABook(memberId,isbn,da);
		da.saveBorrowBook(borrowBook);
	}


}
