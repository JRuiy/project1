package ua.lviv.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.lviv.models.Book;
import ua.lviv.models.Person;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> showBooks() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public void createBook(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public Book showBook(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id},
                new BookMapper()).stream().findAny().orElse(null);
    }

    public void updateBook(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE book_id=?",
                book.getTitle(), book.getAuthor(), book.getYear(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public void addBookToPerson(int id, Person person) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", person.getId(), id);
    }

    public Person getNameOfPerson(int id) {
        return jdbcTemplate.query("SELECT p.person_id, p.full_name, p.year_of_born from Book join person p on Book.person_id = p.person_id where book_id=?",
                new Object[]{id}, new PersonMapper()).stream().findAny().orElse(null);
    }

    public void deleteBookFromPerson(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=? where book_id=?", null, id);
    }


    public List<Book> showBooksAtPerson(int id) {
        return jdbcTemplate.query("SELECT b.book_id, b.title, b.author, b.year, b.person_id FROM Book b JOIN person p on b.person_id = p.person_id WHERE p.person_id=?",
                new Object[]{id}, new BookMapper());
    }
}
