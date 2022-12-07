package business;

import dataaccess.DataAccess;

import java.io.Serializable;
import java.time.LocalDate;

public class BorrowBook implements Serializable {
    private static final long serialVersionUID = 2110690276685962829L;
    private static int counter = 0;
    private BookCopy bookCopy;
    private LocalDate borrowedDate;
    private LibraryMember libraryMember;
    private int borrowId;
    private BorrowBook(BookCopy bc, LibraryMember libMem){
        this.bookCopy = bc;
        this.libraryMember = libMem;
        this.borrowedDate = LocalDate.now();
        borrowId = ++counter;
    }

    public static BorrowBook borrowABook(String memberId, String isbn, DataAccess da){
        Book book = da.readBooksMap().get(isbn);
        LibraryMember libraryMember = da.readMemberMap().get(memberId);
        if(!book.isAvailable()) throw new IllegalArgumentException("Out of copies");
        BookCopy bookCopy = book.getNextAvailableCopy();
        return new BorrowBook(bookCopy,libraryMember);
    }
    public LocalDate getBorrowedDate(){return borrowedDate;}
    public LibraryMember getLibraryMember(){return libraryMember;}
    public BookCopy getBookCopy(){return bookCopy;}
    public int getBorrowedId(){return borrowId;}
}
