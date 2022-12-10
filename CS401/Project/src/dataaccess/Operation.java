package dataaccess;

public enum Operation {
    CheckoutBook,
    AddNewMember,
    AddNewBook,
    AllMemberIds,
    AllBookIds,
    AllBooks,
	SaveMember,
	RemoveMember,
	SaveBook,
	SaveBookCopy,
	SearchMember,
	SearchMemberByIdFirstLastNames,
	SearchOverDueBookByIsbn,
	SearchBookByIsbnOrTitle,
	GetLibraryMembers,
	GetCheckedOutBookByMemberIdOrIsbn,
	SearchCheckedOutBookByMemberIdOrIsbn,
	GetAllAuthors,
	DeleteBook,
	PrintCheckoutRecordsByMember
}
