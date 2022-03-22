package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import guru.springframework.jdbc.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;


public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int i) throws SQLException {
        rs.next();

        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));

        if (Objects.nonNull(rs.getString("book_id"))) {
            author.setBooks(new ArrayList<>());
            author.getBooks().add(mapBook(rs));
        }

        while (rs.next()) {
            author.getBooks().add(mapBook(rs));
        }

        return author;
    }

    private Book mapBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("book_id"));
        book.setIsbn(rs.getString("isbn"));
        book.setTitle(rs.getString("title"));
        book.setPublisher(rs.getString("publisher"));
        book.setAuthorId(rs.getLong("id"));
        return book;
    }
}
