package business;

import java.util.List;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public void saveMember(LibraryMember member);
	public void saveBook(Book book);
	public void saveBookCopy(Book book);
	public void checkoutBook(String memberId, String isbn) throws LibrarySystemException;
}
