package dataaccess;

import java.util.HashMap;
import java.util.List;

import business.Author;
import business.Book;
import business.CheckoutRecord;
import business.CrudMode;
import business.LibraryMember;
import business.LibrarySystemException;

public class Permission {
    private static HashMap<Operation, Auth> operationMap = new HashMap<>();
    static {
        operationMap.put(Operation.AddNewMember,Auth.ADMIN);
        operationMap.put(Operation.AddNewBook,Auth.ADMIN);
        operationMap.put(Operation.CheckoutBook,Auth.LIBRARIAN);
        operationMap.put(Operation.CheckoutBook,Auth.LIBRARIAN);
        operationMap.put(Operation.AddNewMember,Auth.ADMIN);
        operationMap.put(Operation.AddNewBook, Auth.ADMIN);
        operationMap.put(Operation.AllMemberIds,Auth.BOTH);
        operationMap.put(Operation.AllBookIds, Auth.BOTH);
        operationMap.put(Operation.AllBooks, Auth.BOTH);
        operationMap.put(Operation.SaveMember, Auth.BOTH);
        operationMap.put(Operation.RemoveMember,Auth.ADMIN);
        operationMap.put(Operation.SaveBook, Auth.BOTH);
        operationMap.put(Operation.SaveBookCopy, Auth.BOTH);
        operationMap.put(Operation.SearchMember, Auth.BOTH);
        operationMap.put(Operation.SearchMemberByIdFirstLastNames, Auth.BOTH);
        operationMap.put(Operation.SearchOverDueBookByIsbn, Auth.BOTH);
        operationMap.put(Operation.SearchBookByIsbnOrTitle, Auth.BOTH);
        operationMap.put(Operation.GetLibraryMembers, Auth.BOTH);
        operationMap.put(Operation.GetCheckedOutBookByMemberIdOrIsbn, Auth.LIBRARIAN);
        operationMap.put(Operation.SearchCheckedOutBookByMemberIdOrIsbn, Auth.LIBRARIAN);
        operationMap.put(Operation.GetAllAuthors, Auth.BOTH);
        operationMap.put(Operation.DeleteBook, Auth.ADMIN);
        operationMap.put(Operation.PrintCheckoutRecordsByMember, Auth.BOTH);
    }

    public static HashMap<Operation, Auth> getOperationMap() {
        return operationMap;
    }
}
