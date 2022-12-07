package dataaccess;

import java.util.HashMap;

public class Permission {
    private static HashMap<Operation, Auth> operationMap = new HashMap<>();
    static {
        operationMap.put(Operation.AddNewMember,Auth.ADMIN);
        operationMap.put(Operation.AddNewBook,Auth.ADMIN);
        operationMap.put(Operation.CheckoutBook,Auth.LIBRARIAN);
    }

    public static HashMap<Operation, Auth> getOperationMap() {
        return operationMap;
    }
}
