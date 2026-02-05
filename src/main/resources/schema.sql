-- CREATE TABLE IF NOT EXISTS authors (
--     id SERIAL PRIMARY KEY,
--     name VARCHAR(50) NOT NULL,
--     country VARCHAR(50),
--     birth_year INT
-- );
--
-- CREATE TABLE IF NOT EXISTS books (
--     id SERIAL PRIMARY KEY,
--     title VARCHAR(100) NOT NULL,
--     isbn VARCHAR(100) UNIQUE NOT NULL,
--     author_id INT,
--     published_year INT,
--     available_copies INT DEFAULT 1,
--     FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE
-- );
--
-- CREATE TABLE IF NOT EXISTS borrowing_records (
--     id SERIAL PRIMARY KEY,
--     book_id INT,
--     borrower_name VARCHAR(100) NOT NULL,
--     borrower_date DATE NOT NULL,
--     return_date DATE NULL,
--     FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
-- );

CREATE TABLE IF NOT EXISTS authors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    country VARCHAR(50),
    birth_year INT
    );

CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    isbn VARCHAR(100) UNIQUE NOT NULL,
    author_id INT,  -- Matches authors.id
    published_year INT,
    available_copies INT DEFAULT 1,
    FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS borrowing_records (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT,  -- Matches books.id
    borrower_name VARCHAR(100) NOT NULL,
    borrower_date DATE NOT NULL,
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
    );