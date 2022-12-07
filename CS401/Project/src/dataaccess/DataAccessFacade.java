package dataaccess;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import business.Book;
import business.BorrowBook;
import business.BookCopy;
import business.CheckoutEntry;
import business.CheckoutRecord;
import business.LibraryMember;


public class DataAccessFacade implements DataAccess {
	
	enum StorageType {
		BOOKS, MEMBERS, USERS, CHECKOUT_RECORDS, BORROWBOOKS;
	}
	// Windows user can use
	
	public static final String OUTPUT_DIR = System.getProperty("user.dir")
			+ "\\src\\dataaccess\\storage";
	
	// For Mac Users path can use / 
	//public static final String OUTPUT_DIR = System.getProperty("user.dir")
			//+ "/src/dataaccess/storage";
	
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	
	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap() {
		//Returns a Map with name/value pairs being
		//   memberId -> LibraryMember
		return (HashMap<String, LibraryMember>) readFromStorage(
				StorageType.MEMBERS);
	}
	
	//UseCase2 - save new member by admin 
	//implement: other save operations
	public void saveNewMember(LibraryMember member) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		String memberId = member.getMemberId();
		mems.put(memberId, member);
		saveToStorage(StorageType.MEMBERS, mems);	
	}

	@Override
	public void saveBorrowBook(BorrowBook borrowBook) {
		HashMap<Integer, BorrowBook> borrowedBookMap = readBorrowBookMap();
		if(borrowedBookMap == null) borrowedBookMap = new HashMap<>();
		int borrowedId = borrowBook.getBorrowedId();
		borrowedBookMap.put(borrowedId, borrowBook);
		saveToStorage(StorageType.BORROWBOOKS, borrowedBookMap);
	}

	public HashMap<Integer, BorrowBook> readBorrowBookMap() {
		return (HashMap<Integer, BorrowBook>) readFromStorage(StorageType.BORROWBOOKS);
	}


	//UseCase3 - save book by admin 
	@Override
	public void saveNewBook(Book book) {
		HashMap<String, Book> books = readBooksMap();
		books.put(book.getIsbn(), book);
		saveToStorage(StorageType.BOOKS, books);
	}
	
	public void saveBookCopy(Book book) {
		HashMap<String, Book> books = readBooksMap();
		books.put(book.getIsbn(), book);
		saveToStorage(StorageType.BOOKS, books);
	}
	
	@SuppressWarnings("unchecked")
	public  HashMap<String,Book> readBooksMap() {
		//Returns a Map with name/value pairs being
		//   isbn -> Book
		return (HashMap<String,Book>) readFromStorage(StorageType.BOOKS);
	}
	
	//UseCase4 - search book by ISBN 
	@Override
	public Book searchBookByIsbn(String isbn) {
		HashMap<String, Book> booksMap =  readBooksMap();
		if(booksMap.containsKey(isbn)) {
			return booksMap.get(isbn);
		}
		return null;
	}
	
	@Override
	public LibraryMember searchMemberById(String id) {
		 HashMap<String, LibraryMember>  mems = readMemberMap();
		 if(mems.containsKey(id)) {
			 return mems.get(id);
		 }
		 return null;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		//Returns a Map with name/value pairs being
		//   userId -> User
		return (HashMap<String, User>)readFromStorage(StorageType.USERS);
	}
	
	@Override
	public void saveCheckoutRecord(CheckoutRecord record) {
		
		HashMap<String, CheckoutRecord> recordsMap = readCheckoutRecordsMap();
				
		if(recordsMap == null) {
			recordsMap = new HashMap<>();
		}
	
		recordsMap.put(record.getMember().getMemberId(), record);
		saveToStorage(StorageType.CHECKOUT_RECORDS, recordsMap);
	}
	

	@SuppressWarnings("unchecked")
	public HashMap<String, CheckoutRecord> readCheckoutRecordsMap() {
		return (HashMap<String, CheckoutRecord>)readFromStorage(StorageType.CHECKOUT_RECORDS);
	}
	
	@Override
	public CheckoutRecord retrieveCheckoutRecordByMemberId(String memberId) {
		HashMap<String, CheckoutRecord> recordsMap = readCheckoutRecordsMap();
		if(recordsMap.containsKey(memberId)) {
			return recordsMap.get(memberId);
		}
		return null;
	}
	@Override
	public List<CheckoutRecord> retrieveCheckoutRecordByBookIsbn(String isbn) {
		HashMap<String, CheckoutRecord> recordsMap = readCheckoutRecordsMap();
		
		List<CheckoutRecord> recordList = new ArrayList<>();
		for(String key: recordsMap.keySet()) {
			CheckoutRecord rd = recordsMap.get(key);
			for(CheckoutEntry entry: rd.getCheckoutEntries()) {
				if(entry.getBookCopy().getBook().getIsbn().equalsIgnoreCase(isbn)
						&& LocalDate.now().isAfter(entry.getDueDate())) {
					recordList.add(rd);
				}
			} 		
		}
	
		return recordList;
	}
	
	
	/////load methods - these place test data into the storage area
	///// - used just once at startup  
	
		
	static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, books);
	}
	static void loadUserMap(List<User> userList) {
		HashMap<String, User> users = new HashMap<String, User>();
		userList.forEach(user -> users.put(user.getId(), user));
		saveToStorage(StorageType.USERS, users);
	}
 
	static void loadMemberMap(List<LibraryMember> memberList) {
		HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		saveToStorage(StorageType.MEMBERS, members);
	}
	
	static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
	}
	
	static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return retVal;
	}
	
	
	
	final static class Pair<S,T> implements Serializable{
		
		S first;
		T second;
		Pair(S s, T t) {
			first = s;
			second = t;
		}
		@Override 
		public boolean equals(Object ob) {
			if(ob == null) return false;
			if(this == ob) return true;
			if(ob.getClass() != getClass()) return false;
			@SuppressWarnings("unchecked")
			Pair<S,T> p = (Pair<S,T>)ob;
			return p.first.equals(first) && p.second.equals(second);
		}
		
		@Override 
		public int hashCode() {
			return first.hashCode() + 5 * second.hashCode();
		}
		@Override
		public String toString() {
			return "(" + first.toString() + ", " + second.toString() + ")";
		}
		private static final long serialVersionUID = 5399827794066637059L;
	}

}
