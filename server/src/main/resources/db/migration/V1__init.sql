-- Tabela Author
CREATE TABLE IF NOT EXISTS author (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    biography TEXT,
    birthdate DATE
);

-- Tabela Category
CREATE TABLE IF NOT EXISTS category (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Tabela Book
CREATE TABLE IF NOT EXISTS book (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    coverType VARCHAR(255),
    publicationDate DATE
);

-- Tabela Author_Book
CREATE TABLE IF NOT EXISTS author_book (
    author_id BIGINT,
    book_id BIGINT,
    PRIMARY KEY (author_id, book_id),
    FOREIGN KEY (author_id) REFERENCES author(id),
    FOREIGN KEY (book_id) REFERENCES book(id)
);

-- Tabela Category_Book
CREATE TABLE IF NOT EXISTS category_book (
    category_id BIGINT,
    book_id BIGINT,
    PRIMARY KEY (category_id, book_id),
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (book_id) REFERENCES book(id)
);