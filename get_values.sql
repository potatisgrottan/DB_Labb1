SELECT Book.*, Author.*
FROM WrittenBy
JOIN Book, Author
WHERE WrittenBy.Author_SSN = Author.SSN
AND WrittenBy.Book_ISBN = Book.ISBN;
