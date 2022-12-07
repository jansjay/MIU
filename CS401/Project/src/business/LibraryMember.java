package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private List<BorrowBook> borrowBooks;
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;
		borrowBooks = new ArrayList<>();
	}
	
	public String getMemberId() {
		return memberId;
	}

	public void setBorrowedBooks(BorrowBook bc) throws IllegalAccessException {
		if(bc == null) throw new IllegalArgumentException("Borrowed Book is null");
		if(bc.getLibraryMember().memberId != memberId)
			throw new IllegalAccessException("Book is already borrowed by other member");
		if(borrowBooks.stream().anyMatch(bb->bb.getBorrowedId() == bc.getBorrowedId()))
			throw new IllegalArgumentException("This Book Copy is already borrowed by this member");
		borrowBooks.add(bc);
	}
	
	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}


	private static final long serialVersionUID = -2226197306790714013L;
}
