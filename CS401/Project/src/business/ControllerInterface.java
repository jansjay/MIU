package business;

import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public void logout() throws LoginException;
	public List<String> allMemberIds()  throws LibrarySystemException;
	public List<String> allBookIds()  throws LibrarySystemException;
	public List<Book> allBooks()  throws LibrarySystemException;
	public void saveMember(LibraryMember member, CrudMode mode)  throws LibrarySystemException;
	public void removeMember(String memberId)  throws LibrarySystemException;
	public void saveBook(Book book, CrudMode mode)  throws LibrarySystemException;
	public void saveBookCopy(Book book)  throws LibrarySystemException;
	public void checkoutBook(String memberId, String isbn) throws LibrarySystemException;
	public LibraryMember searchMember(String memberId) throws LibrarySystemException;
	public List<LibraryMember> searchMemberByIdFirstLastNames(String searchValue) throws LibrarySystemException;
	public Book searchOverDueBookByIsbn(String isbn) throws LibrarySystemException;
	public List<Book> searchBookByIsbnOrTitle(String isbnOrTitle) throws LibrarySystemException;
	public List<LibraryMember> getLibraryMembers() throws LibrarySystemException;
	public List<CheckoutRecord> getCheckedOutBookByMemberIdOrIsbn(String value) throws LibrarySystemException;
	public List<CheckoutRecord> searchCheckedOutBookByMemberIdOrIsbn(String value) throws LibrarySystemException;
	public List<Author> getAllAuthors() throws LibrarySystemException;
	public void deleteBook(Book book) throws LibrarySystemException;
}
