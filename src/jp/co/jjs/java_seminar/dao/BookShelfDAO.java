package jp.co.jjs.java_seminar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jp.co.jjs.java_seminar.model.Book;

public class BookShelfDAO {

    private DataSource ds;

    public BookShelfDAO(){
        try{
            Context initialContext = new InitialContext();
            Context envContext = (Context) initialContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/crud");
        }
        catch(NamingException e){
            e.printStackTrace();
        }
    }



    public ArrayList<Book> getBooks(String bookname){
        ArrayList<Book> bookList = new ArrayList<>();

        try (Connection con = ds.getConnection();
                PreparedStatement ps = con
                        .prepareStatement("SELECT * FROM BOOKSHELF WHERE TITLE LIKE ?")) {
            ps.setString(1, "%" + bookname + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getInt("ID"));
                    book.setName(rs.getString("TITLE"));
                    book.setIsbn(rs.getString("ISBN"));
                    book.setWriter(rs.getString("WRITER"));
                    book.setPublisher(rs.getString("PUBLISHER"));
                    book.setPrice(rs.getInt("PRICE"));
                    bookList.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;

    }
}
