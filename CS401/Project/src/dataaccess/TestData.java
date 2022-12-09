package dataaccess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import business.Address;
import business.Author;
import business.Book;
import business.BookCopy;
import business.CheckoutEntry;
import business.CheckoutRecord;
import business.LibraryMember;

/**
 * This class loads data into the data repository and also
 * sets up the storage units that are used in the application.
 * The main method in this class must be run once (and only
 * once) before the rest of the application can work properly.
 * It will create three serialized objects in the dataaccess.storage
 * folder.
 * 
 *
 */
public class TestData {
	
	
	public static void main(String[] args) {
		TestData td = new TestData();
		td.bookData();
		td.libraryMemberData();
		td.userData();
		DataAccess da = new DataAccessFacade();
		
		System.out.println(da.readBooksMap());
		System.out.println(da.readUserMap());
		
		td.testAddNewMemberData();
		
		td.testAddNewBook();
		
		td.testSearchBookByIsbnExistsInList();
		
		//test checkout record 
		
		td.testSaveCheckoutRecords();
		
		td.testExistingCheckoutRecords();
		
		td.testLoadAllAuthors();
		
	}
	
	///create books
	public void bookData() {
		allBooks.get(0).addCopy();
		allBooks.get(0).addCopy();
		allBooks.get(1).addCopy();
		allBooks.get(3).addCopy();
		allBooks.get(2).addCopy();
		allBooks.get(2).addCopy();
		DataAccessFacade.loadBookMap(allBooks);
	}
	
	public void testAddNewBook() {
		System.out.println("Test to add new book");
		Book book = new Book("045-228-2667", "Science of Being and Art of Living", 21, Arrays.asList(allAuthors.get(5)));
		DataAccess da = new DataAccessFacade();
		da.saveNewBook(book);
		System.out.println(da.readBooksMap());
	}
	
	public void testSearchBookByIsbnExistsInList() {
		System.out.println("Test to search book by isbn");
		DataAccess da = new DataAccessFacade();
		Book book = da.searchBookByIsbn("045-228-2667");
		if(book != null)
			System.out.println(book);
		else
			System.out.println("No book found by isbn: " + "045-228-2667");
	}
	
	public void testSaveCheckoutRecords() {
		System.out.println("Test to checkout record of member");
		LibraryMember member = new LibraryMember("1001", "Andy", "Rogers", "641-223-2211", addresses.get(4));
		CheckoutRecord record = new CheckoutRecord(member, LocalDate.now());
		DataAccess da = new DataAccessFacade();
		Book book = da.searchBookByIsbn("045-228-2667");
		if(book != null)
			System.out.println(book);
		else
			System.out.println("No book found by isbn: " + "045-228-2667");
		BookCopy bookCopy = book.getNextAvailableCopy();
		LocalDate checkoutDate = LocalDate.now().plusDays(book.getMaxCheckoutLength());
		CheckoutEntry entry = new CheckoutEntry(bookCopy, LocalDate.now(), checkoutDate);
		record.addCheckoutEntry(entry);
		da.saveCheckoutRecord(record);
	}
	
	public void testExistingCheckoutRecords() { 
		DataAccess da = new DataAccessFacade();
		CheckoutRecord record = da.retrieveCheckoutRecordByMemberId("1001");
		if(record != null) {
			for(CheckoutEntry ent: record.getCheckoutEntries()) {
				System.out.println("entry book isbn: " + ent.getBookCopy().getBook().getIsbn());
			}
		} else {
			System.out.println("No checkout record found");
		}
	}
	
	public void userData() {
		DataAccessFacade.loadUserMap(allUsers);
	}
	
	//create library members
	public void libraryMemberData() {
		LibraryMember libraryMember = new LibraryMember("1001", "Andy", "Rogers", "641-223-2211", addresses.get(4));
		members.add(libraryMember);
		libraryMember = new LibraryMember("1002", "Drew", "Stevens", "702-998-2414", addresses.get(5));
		members.add(libraryMember);
		
		libraryMember = new LibraryMember("1003", "Sarah", "Eagleton", "451-234-8811", addresses.get(6));
		members.add(libraryMember);
		
		libraryMember = new LibraryMember("1004", "Ricardo", "Montalbahn", "641-472-2871", addresses.get(7));
		members.add(libraryMember);
		
		DataAccessFacade.loadMemberMap(members);	
	}
	
	public void testAddNewMemberData() {
		System.out.println("Test to add new member");
		LibraryMember mem = new LibraryMember("1005", "Tomas", "Adison", "641-472-2872", addresses.get(1));
		DataAccess da = new DataAccessFacade();
		da.saveNewMember(mem);
		System.out.println(da.readMemberMap());
	}
	
	///////////// DATA //////////////
	List<LibraryMember> members = new ArrayList<LibraryMember>();
	@SuppressWarnings("serial")
	
	List<Address> addresses = new ArrayList<Address>() {
		{
			add(new Address("101 S. Main", "Fairfield", "IA", "52556"));
			add(new Address("51 S. George", "Georgetown", "MI", "65434"));
			add(new Address("23 Headley Ave", "Seville", "Georgia", "41234"));
			add(new Address("1 N. Baton", "Baton Rouge", "LA", "33556"));
			add(new Address("5001 Venice Dr.", "Los Angeles", "CA", "93736"));
			add(new Address("1435 Channing Ave", "Palo Alto", "CA", "94301"));
			add(new Address("42 Dogwood Dr.", "Fairfield", "IA", "52556"));
			add(new Address("501 Central", "Mountain View", "CA", "94707"));
			add(new Address("503 West", "Window View", "MA", "55707"));
			add(new Address("601 Street st", "Fountain View", "CA", "93407"));
		}
	};
	
	public void testLoadAllAuthors() {
		System.out.println("Test to load All authors");
		DataAccess da = new DataAccessFacade();
		DataAccessFacade.loadAuthors(allAuthors);
		
	}
	@SuppressWarnings("serial")
	public List<Author> allAuthors = new ArrayList<Author>() {
		{
			add(new Author("Joe", "Thomas", "641-445-2123", addresses.get(0), "A happy man is he."));
			add(new Author("Sandra", "Thomas", "641-445-2123", addresses.get(0), "A happy wife is she."));
			add(new Author("Nirmal", "Pugh", "641-919-3223", addresses.get(1), "Thinker of thoughts."));
			add(new Author("Andrew", "Cleveland", "976-445-2232", addresses.get(2), "Author of childrens' books."));
			add(new Author("Sarah", "Connor", "123-422-2663", addresses.get(3), "Known for her clever style."));
			add(new Author("Maharishi ", "Mahesh Yogi", "045-228-2667", addresses.get(1), "Science of Being and Art of Living"));
			add(new Author("Andrew", "Simons", "045-238-2567", addresses.get(6), "What goes around comes around"));
			add(new Author("Ricky", "Ponting", "055-338-3567", addresses.get(7), "Famous personality"));
		}
	};
	
	@SuppressWarnings("serial")
	List<Book> allBooks = new ArrayList<Book>() {
		{
			add(new Book("23-11451", "The Big Fish", 21, Arrays.asList(allAuthors.get(0), allAuthors.get(1))));
			add(new Book("28-12331", "Antartica", 7, Arrays.asList(allAuthors.get(2))));
			add(new Book("99-22223", "Thinking Java", 21, Arrays.asList(allAuthors.get(3))));
			add(new Book("48-56882", "Jimmy's First Day of School", 7, Arrays.asList(allAuthors.get(4))));
			add(new Book("68-56882", "Andrew going Home", 7, Arrays.asList(allAuthors.get(5))));
			add(new Book("38-56882", "Ha Ha got you!!!", 7, Arrays.asList(allAuthors.get(6))));
		}
	};
	
	@SuppressWarnings("serial")
	List<User> allUsers = new ArrayList<User>() {
		{
			add(new User("101", "xyz", Auth.LIBRARIAN));
			add(new User("102", "abc", Auth.ADMIN));
			add(new User("103", "111", Auth.BOTH));
		}
	};
	
	

}
