 CREATE TABLE books (
     ID          number identity(1,1) primary key,
     title        varchar2(50),
     language    varchar2(30),
     pages       number,
     author      varchar2(50),
     year        number
 );

INSERT INTO books (title, language, pages, author, year)
VALUES ('Lord of the rings', 'English', 400, 'JRR Tolkien', 1954 )