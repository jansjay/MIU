package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.Operation;
import dataaccess.User;


public class SystemController extends BaseController implements ControllerInterface  {
	public static Auth currentAuth = null;
	private static SystemController instance = null;
	DataAccess da;
	public SystemController(){
		da = new DataAccessFacade();
	}

	public static ControllerInterface getInstance() {
		if(instance == null)
			instance = new SystemController();
		return instance;
	}
	
	//UseCase1: methods 
	public void login(String id, String password) throws LoginException {
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		Context.createContext(id, currentAuth);
		
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
		if(!Validator.validateMemberId(memberId)) throw new IllegalArgumentException("Invalid member ID");
		if(!Validator.validateIsbn(isbn)) throw new IllegalArgumentException("Invalid ISBN");
		BorrowBook borrowBook = BorrowBook.borrowABook(memberId,isbn,da);
		da.saveBorrowBook(borrowBook);
	}


	//UseCase2: methods
	@Override
	public void saveMember(LibraryMember member) {
		da.saveNewMember(member);
	}
	
	//UseCase3: methods
	@Override
	public void saveBook(Book book) {
		da.saveNewBook(book);
	}

	//UseCase5
	@Override
	public void saveBookCopy(Book book) {
		book.addCopy();
		da.saveBookCopy(book);
	}
	
	//UseCase4 - methods
	public void checkoutBook(String memberId, String isbn) throws LibrarySystemException {
		
		LibraryMember member = da.searchMemberById(memberId);
		
		if(member == null) {
			throw new LibrarySystemException("No member is found with Id: " + memberId);
		}
		
		Book book = da.searchBookByIsbn(isbn);
		if(book == null) {
			throw new LibrarySystemException("No Book is found with ISBN: " + isbn);
		}
		
		if(!book.isAvailable()) {
			throw new LibrarySystemException("Book copy is NOT available ");
		}
		
		//prepare checkout record end entry
		BookCopy bookCopy = book.getNextAvailableCopy();
		bookCopy.changeAvailability();
		
		CheckoutRecord record = da.retrieveCheckoutRecordByMemberId(memberId);
		
		if(record == null) {
			record = new CheckoutRecord(member, LocalDate.now());
		}
		//calculate due date based on checkout days 
		LocalDate checkoutDate = LocalDate.now().plusDays(book.getMaxCheckoutLength());
		CheckoutEntry entry = new CheckoutEntry(bookCopy, LocalDate.now(), checkoutDate);
		record.addCheckoutEntry(entry);
		// save new / updated record and entries 
		da.saveCheckoutRecord(record);

	}
	
	//UseCase 6: (Optional 1)
	public LibraryMember searchMember(String memberId) {
		LibraryMember member = da.searchMemberById(memberId);
		//
		return member;
	}
	
	public void printCheckoutRecordsByMember(String memberId) {
		LibraryMember member = da.searchMemberById(memberId);
		CheckoutRecord record = da.retrieveCheckoutRecordByMemberId(memberId);
		if(member != null && record != null) {
			System.out.println("All checkout entry for the library member: ");
			System.out.println(member);
			System.out.println("Record Entries are : ");
			for(CheckoutEntry ent: record.getCheckoutEntries()) {
				System.out.println("Book isbn: " + ent.getBookCopy().getBook().getIsbn() 
									+ ", copy number: "+ ent.getBookCopy().getCopyNum());
			}
		}
	}
	
	//UseCase 7: (Optional 2)
	public Book searchOverDueBookByIsbn(String isbn) {
		Book book = da.searchBookByIsbn(isbn);
		//if copy/s unavailable then search into records
		List<BookCopy> unavaileCopies = book.getUnavailableCopies();
		//get over due book records 
		List<CheckoutRecord> recordList = da.retrieveCheckoutRecordByBookIsbn(isbn);
		for(CheckoutRecord record: recordList) {
			for(CheckoutEntry entry: record.getCheckoutEntries()) {
				if(LocalDate.now().isAfter(entry.getDueDate())) {
					book = entry.getBookCopy().getBook();
					System.out.println("Overdue book : "
							+ entry.getBookCopy().getBook().getIsbn() 
							+", copy num:"+ entry.getBookCopy().getCopyNum());
					System.out.println("checkout date : " + entry.getCheckoutDate() 
					+ ", due date: " + entry.getDueDate());
					System.out.println();
				}
			}
		}
		
		return book;
	}

}
