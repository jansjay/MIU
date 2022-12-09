package business;

import javax.swing.table.DefaultTableModel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class DataModelMapper {

	public static void addLibraryMember(LibraryMember member, DefaultTableModel model) {
		
			Address address = member.getAddress();
			String[] aRow = {
					member.getMemberId(),
					member.getFirstName(),
					member.getLastName(),			
					address.getStreet(),
					address.getCity(),
					address.getState(),
					address.getZip(),
					member.getTelephone()
			};
		model.addRow(aRow);
	}
	
	public static void addAllLibraryMember(List<LibraryMember> member, DefaultTableModel model) {
	
		member.forEach(m -> { addLibraryMember(m,model); });
	}
	
	public static LibraryMember mapLibraryMember(String...values) {
		return new LibraryMember(
				values[0],
				values[1],
				values[2],
				values[7],
				new Address(
						values[3],
						values[4],
						values[5],
						values[6])
				);
	}

	public static void UpdateModelRow(DefaultTableModel model, String[] values, int rowNumber) {
		for(int i=0; i<values.length; i++) {
			model.setValueAt(values[i], rowNumber, i);
		}
	}
	
	public static void addBooks(List<Book> books, DefaultTableModel model) {
		books
		.stream()
		.forEach(bk->{
			String[] aRow = {
					bk.getIsbn(),
					bk.getTitle(),
					bk.getAuthors()
					.stream().map(abk->abk.toString())
					.collect(Collectors.joining(",")),
					bk.getNumCopies()+"",
					bk.getMaxCheckoutLength()+""
			};
			model.addRow(aRow);
		});
	}

	public static void addCheckoutBook(CheckoutRecord cr, DefaultTableModel checkoutModel) {
		// TODO Auto-generated method stub
		cr
		.getCheckoutEntries()
		.stream()
		.forEach(ck->{
			String[] aRow= {
				cr.getMember().getMemberId(),
				ck.getBookCopy().getBook().getIsbn(),
				ck.getBookCopy().toString(),
				ck.getDueDate().toString()
			};
			checkoutModel.addRow(aRow);
		});
	}
}
