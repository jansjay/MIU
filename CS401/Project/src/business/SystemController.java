package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.Operation;
import dataaccess.Permission;
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
	public List<String> allMemberIds()  throws LibrarySystemException {
		checkAuthorized(Operation.AllMemberIds);
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds()  throws LibrarySystemException {
		checkAuthorized(Operation.AllBookIds);
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}
	
	@Override
	public List<Book> allBooks()  throws LibrarySystemException {
		checkAuthorized(Operation.AllBooks);
		List<Book> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().values());
		return retval;
	}

	//UseCase2: methods
	@Override
	public void saveMember(LibraryMember member, CrudMode mode)  throws LibrarySystemException {
		checkAuthorized(Operation.SaveMember);
		if(mode == CrudMode.Create) {
			if(Validator.isEmpty(member.getMemberId())) throw new IllegalArgumentException("Empty Member ID");
			LibraryMember existingMember = da.searchMemberById(member.getMemberId());
			if(existingMember != null && !existingMember.getMemberId().isEmpty())
				throw new IllegalArgumentException("Member ID Already exists");
		}
		else{
			if(!Validator.validateMemberId(member.getMemberId())) throw new IllegalArgumentException("Invalid Member ID");
		}
		da.saveNewMember(member);
	}
	
	//UseCase3: methods
	@Override
	public void saveBook(Book book, CrudMode mode)  throws LibrarySystemException {
		checkAuthorized(Operation.SaveBook);
		if(mode == CrudMode.Create) {
			if(Validator.isEmpty(book.getIsbn())) throw new IllegalArgumentException("Empty ISBN");
			Book existingBook = da.searchBookByIsbn(book.getIsbn());
			if(existingBook != null && !existingBook.getIsbn().isEmpty())
				throw new IllegalArgumentException("ISBN Already exists");
		}
		else{
			if(!Validator.validateIsbn(book.getIsbn())) throw new IllegalArgumentException("Invalid ISBN");
		}
		if(!Validator.validateBookTitle(book.getTitle())) throw new IllegalArgumentException("Invalid Title");
		if(!Validator.validateBookAuthors(book.getAuthors())) throw new IllegalArgumentException("No Authors");
		if(!Validator.validateBookCopies(book.getNumCopies())) throw new IllegalArgumentException("Invalid Num of Copies");
		if(!Validator.validateBookCheckoutLength(book.getMaxCheckoutLength())) throw new IllegalArgumentException("Invalid Checkout length. \nIt should be 7 or 21 days.");
		da.saveNewBook(book);
	}

	//UseCase5
	@Override
	public void saveBookCopy(Book book)  throws LibrarySystemException {
		checkAuthorized(Operation.SaveBookCopy);
		book.addCopy();
		da.saveBookCopy(book);
	}
	
	//UseCase4 - methods
	public void checkoutBook(String memberId, String isbn) throws LibrarySystemException {
		
		checkAuthorized(Operation.CheckoutBook);
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
		saveBook(book, CrudMode.Update);
	}
	
	//UseCase 6: (Optional 1)
	public LibraryMember searchMember(String memberId)  throws LibrarySystemException {
		checkAuthorized(Operation.SearchMember);
		LibraryMember member = da.searchMemberById(memberId);
		//
		return member;
	}
	
	public void printCheckoutRecordsByMember(String memberId)  throws LibrarySystemException {
		checkAuthorized(Operation.PrintCheckoutRecordsByMember);
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
	public Book searchOverDueBookByIsbn(String isbn)  throws LibrarySystemException {
		checkAuthorized(Operation.SearchOverDueBookByIsbn);
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

	@Override
	public List<LibraryMember> getLibraryMembers() throws LibrarySystemException {
		checkAuthorized(Operation.GetLibraryMembers);
		
		return da.readMemberMap()
				.values()
				.stream()
				.sorted((m1,m2)->m1.getMemberId().compareTo(m2.getMemberId()))
				.toList();
	}
	
	@Override
	public void removeMember(String memberId) throws LibrarySystemException  {
		checkAuthorized(Operation.RemoveMember);
		//TODO: Have to remove all relevant records from relevant tables
		da.removeMember(memberId);
	}
	
	@Override
	public List<Book> searchBookByIsbnOrTitle(String isbnOrTitle) throws LibrarySystemException {
		checkAuthorized(Operation.SearchBookByIsbnOrTitle);
		
		List<Book> ds = da
				.readBooksMap()
				.values()
				.parallelStream()
				.filter(
						bk -> bk.getIsbn().contains(isbnOrTitle) 
						|| bk.getTitle().contains(isbnOrTitle)
						|| isbnOrTitle.isEmpty())
				.toList();
		
			return ds;
	}
	@Override
	public List<CheckoutRecord> getCheckedOutBookByMemberIdOrIsbn(String value)  throws LibrarySystemException {
		checkAuthorized(Operation.GetCheckedOutBookByMemberIdOrIsbn);
		List<CheckoutRecord> crs = new ArrayList<>();	
		CheckoutRecord memCr=	da.retrieveCheckoutRecordByMemberId(value);
		if(memCr !=null) crs.add(memCr);
		List<CheckoutRecord> isbnCr = da.retrieveCheckoutRecordByBookIsbn(value);
		if(isbnCr !=null) 
			for(CheckoutRecord rec: isbnCr)
				if(!crs.contains(rec))
					crs.add(rec);
		return crs;
	}
	
	@Override
	public List<CheckoutRecord> searchCheckedOutBookByMemberIdOrIsbn(String value)  throws LibrarySystemException {
		checkAuthorized(Operation.SearchCheckedOutBookByMemberIdOrIsbn);
		List<CheckoutRecord> crs = new ArrayList<>();	
		List<CheckoutRecord> memCr=	da.searchCheckoutRecordByMemberId(value);
		if(memCr !=null) crs.addAll(memCr);
		List<CheckoutRecord> isbnCr = da.searchCheckoutRecordByBookIsbn(value);
		if(isbnCr !=null) {
			for(CheckoutRecord rec : isbnCr) {
				List<CheckoutEntry> toRemove = new ArrayList<>();
				if(!crs.contains(rec)) {
					for(CheckoutEntry entry: rec.getCheckoutEntries())
						if(!entry.getBookCopy().getBook().getIsbn().toLowerCase().contains(value.toLowerCase()))
						{
							toRemove.add(entry);
						}
					rec.getCheckoutEntries().removeAll(toRemove);
					crs.add(rec);
				}
			}
		}
		return crs;
	}

	@Override
	public List<Author> getAllAuthors()  throws LibrarySystemException {
		checkAuthorized(Operation.GetAllAuthors);
		return da.getAllAuthors();
	}

	@Override
	public void deleteBook(Book book)  throws LibrarySystemException {
		checkAuthorized(Operation.DeleteBook);
		da.deleteBook(book);
	}

	@Override
	public List<LibraryMember> searchMemberByIdFirstLastNames(String searchValue)  throws LibrarySystemException {
		checkAuthorized(Operation.SearchMemberByIdFirstLastNames);
		return da.searchMemberByMemberIdFirstNameLastName(searchValue);
	}
	
	private void checkAuthorized(Operation operation) throws LibrarySystemException {
    	try{Auth role = Permission.getOperationMap().get(operation);
    	Auth currentRole = Context.getContext().getAuth();
    	if(currentRole != null && role == currentRole || role == Auth.BOTH) {
    		return;
    	}
    	}
    	catch(LoginException ex)
    	{
    		throw new LibrarySystemException("User is not allowed to perform operation " + operation.toString() + " " + ex.getMessage());
    	}
    	throw new LibrarySystemException("User is not allowed to perform operation " + operation.toString());
    }
}
