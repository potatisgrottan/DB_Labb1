UPDATE Book 
SET author_ssn = '196507310001' 
WHERE Title LIKE '%Harry Potter%';

SELECT Author_SSN, Title
FROM Book
WHERE Title LIKE '%Harry Potter%';
