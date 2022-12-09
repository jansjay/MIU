package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	private LibraryMember member;
	private LocalDate recordDate;
	private List<CheckoutEntry> checkoutEntries;
	
	public CheckoutRecord(LibraryMember member, LocalDate recordDate) {
		this.member = member;
		this.recordDate = recordDate;
		checkoutEntries = new ArrayList<CheckoutEntry>(); 
	}
	
	public void addCheckoutEntry(CheckoutEntry checkoutEntry) {
		this.checkoutEntries.add(checkoutEntry);
	}
	
	public List<CheckoutEntry> getCheckoutEntries() {
		return checkoutEntries;
	}
	
	public LibraryMember getMember() {
		return member;
	}
	public void setMember(LibraryMember member) {
		this.member = member;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof CheckoutRecord)) return false;
		CheckoutRecord other = (CheckoutRecord)obj;
		return member.getMemberId().equals(other.member.getMemberId());
	}
}
