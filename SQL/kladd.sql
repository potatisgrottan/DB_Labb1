SELECT * FROM Book;
SELECT * FROM Author;
SELECT * FROM WrittenBy;

DESCRIBE Book;
DESCRIBE Author;
DESCRIBE WrittenBy;

SELECT * FROM Book
JOIN WrittenBy ON WrittenBy.Book_ISBN = Book.ISBN
JOIN Author ON WrittenBy.Author_SSN = Author.SSN;

ALTER TABLE Book DROP author_ssn;