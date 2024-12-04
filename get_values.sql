-- Active: 1733316650851@@127.0.0.1@3306@Library
SELECT Book.*, Author.*
FROM WrittenBy
JOIN Book, Author
WHERE WrittenBy.Author_SSN = Author.SSN
AND WrittenBy.Book_ISBN = Book.ISBN;
