package dataaccess;

import java.util.HashMap;
import java.util.List;

import business.Book;
import business.BorrowBook;
import business.CheckoutRecord;
import business.LibraryMember;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveBorrowBook(BorrowBook borrowBook);
	public HashMap<Integer, BorrowBook> readBorrowBookMap();
	public void saveNewMember(LibraryMember member); 
	public void saveNewBook(Book book); 
	public void saveBookCopy(Book book); 
	public Book searchBookByIsbn(String isbn);
	public LibraryMember searchMemberById(String id);
	public void saveCheckoutRecord(CheckoutRecord record);
	public CheckoutRecord retrieveCheckoutRecordByMemberId(String memberId);
	public List<CheckoutRecord> retrieveCheckoutRecordByBookIsbn(String isbn);
}
