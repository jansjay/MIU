package business;

import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public List<Book> allBooks();
	public void saveMember(LibraryMember member, CrudMode mode);
	public void removeMember(String memberId);
	public void saveBook(Book book, CrudMode mode);
	public void saveBookCopy(Book book);
	public void checkoutBook(String memberId, String isbn) throws LibrarySystemException;
	public LibraryMember searchMember(String memberId);
	public List<LibraryMember> searchMemberByIdFirstLastNames(String searchValue);
	public Book searchOverDueBookByIsbn(String isbn);
	public List<Book> searchBookByIsbnOrTitle(String isbnOrTitle);
	public List<LibraryMember> getLibraryMembers();
	public List<CheckoutRecord> getCheckedOutBookByMemberIdOrIsbn(String value);
	public List<CheckoutRecord> searchCheckedOutBookByMemberIdOrIsbn(String value);
	public List<Author> getAllAuthors();
	public void deleteBook(Book book);
}
