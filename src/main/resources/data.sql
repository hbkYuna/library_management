INSERT INTO USER (ID, USERNAME, PASSWORD, ROLE)
VALUES (nextval('user_seq'), 'noah', '$2a$10$9TeBFudS7HsgCa4sSvP//O627sMq.KiTFrOr8IzrVlYw5c8aoKzNm', 'ROLE_USER' );

INSERT INTO USER (ID, USERNAME, PASSWORD, ROLE)
VALUES (nextval('user_seq'), 'admin', '$2a$10$9TeBFudS7HsgCa4sSvP//O627sMq.KiTFrOr8IzrVlYw5c8aoKzNm', 'ROLE_ADMIN' );

INSERT INTO BOOK (ID, AUTHOR, TITLE, DESCRIPTION, PAGES, YEAR)
VALUES (nextval('book_seq'), 'JRR Tolkien', 'Lord Of The Rings', 'My precious', 579, 1959);

INSERT INTO BOOK (ID, AUTHOR, TITLE, DESCRIPTION, PAGES, YEAR)
VALUES (nextval('book_seq'), 'JRR Tolkien', 'Lord Of The Rings 2', 'Still my precious', 800, 1963);

INSERT INTO BOOK (ID, AUTHOR, TITLE, DESCRIPTION, PAGES, YEAR)
VALUES (nextval('book_seq'), 'JRR Tolkien', 'Lord Of The Rings 6', 'Still my precious', 800, 1963);

INSERT INTO BOOK (ID, AUTHOR, TITLE, DESCRIPTION, PAGES, YEAR)
VALUES (nextval('book_seq'), 'JRR Tolkien', 'Lord Of The Rings 7', 'Still my precious', 800, 1963);

INSERT INTO BOOK (ID, AUTHOR, TITLE, DESCRIPTION, PAGES, YEAR)
VALUES (nextval('book_seq'), 'JRR Tolkien', 'Lord Of The Rings 8', 'Still my precious', 800, 1963);

INSERT INTO BOOK (ID, AUTHOR, TITLE, DESCRIPTION, PAGES, YEAR)
VALUES (nextval('book_seq'), 'JRR Tolkien', 'Lord Of The Rings 9', 'Still my precious', 800, 1963);

INSERT INTO BOOK (ID, AUTHOR, TITLE, DESCRIPTION, PAGES, YEAR)
VALUES (nextval('book_seq'), 'JRR Tolkien', 'Lord Of The Rings 10', 'Still my precious', 800, 1963);

INSERT INTO BOOK (ID, AUTHOR, TITLE, DESCRIPTION, PAGES, YEAR)
VALUES (nextval('book_seq'), 'JK ROWLING', 'Harry Potter 2', 'Still my precious', 800, 1983);

INSERT INTO BOOK (ID, AUTHOR, TITLE, DESCRIPTION, PAGES, YEAR)
VALUES (nextval('book_seq'), 'JK ROWLING', 'Harry Potter', 'Still my precious', 800, 1993);
