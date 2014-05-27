package jp.co.jjs.java_seminar;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import jp.co.jjs.java_seminar.model.Book;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/crud")
    private DataSource ds;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Book> bookList = new ArrayList<>();

        String bookname = request.getParameter("bookname");

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

        if (bookList.size() == 0) {
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("WEB-INF/jsp/notfound.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("bookList", bookList);
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("WEB-INF/jsp/result.jsp");
            dispatcher.forward(request, response);
        }
    }
}