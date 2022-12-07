package dataaccess;

import java.util.HashMap;

import business.Book;
import business.BorrowBook;
import business.LibraryMember;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member);

	public void saveBorrowBook(BorrowBook borrowBook);
	public HashMap<Integer, BorrowBook> readBorrowBookMap();
}
