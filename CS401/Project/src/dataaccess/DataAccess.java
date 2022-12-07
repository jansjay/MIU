package dataaccess;

import java.util.HashMap;
import java.util.List;

import business.Book;
import business.CheckoutRecord;
import business.LibraryMember;
import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member); 
	public void saveNewBook(Book book); 
	public void saveBookCopy(Book book); 
	public Book searchBookByIsbn(String isbn);
	public LibraryMember searchMemberById(String id);
	public void saveCheckoutRecord(CheckoutRecord record);
	public CheckoutRecord retrieveCheckoutRecordByMemberId(String memberId);
	public List<CheckoutRecord> retrieveCheckoutRecordByBookIsbn(String isbn);
}
