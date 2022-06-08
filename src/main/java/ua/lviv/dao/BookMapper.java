package ua.lviv.dao;

import org.springframework.jdbc.core.RowMapper;
import ua.lviv.models.Book;
import ua.lviv.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("book_id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setYear(resultSet.getInt("year"));
        book.setPerson_id(resultSet.getInt("person_id"));
        return book;
    }
}
