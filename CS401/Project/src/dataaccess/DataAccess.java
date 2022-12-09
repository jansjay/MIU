package dataaccess;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import business.Author;
import business.Book;
import business.CheckoutRecord;
import business.LibraryMember;

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
	public List<CheckoutRecord> searchCheckoutRecordByMemberId(String memberId);
	public List<CheckoutRecord> searchCheckoutRecordByBookIsbn(String isbn);	
	public void removeMember(String memberId);
	public List<Author> getAllAuthors();
	public void deleteBook(Book book);
}
