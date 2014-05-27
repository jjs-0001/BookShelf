package jp.co.jjs.java_seminar;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.jjs.java_seminar.dao.BookShelfDAO;
import jp.co.jjs.java_seminar.model.Book;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
    private static final long serialVersionUID = 1L;

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

        BookShelfDAO dao = new BookShelfDAO();
        bookList = dao.getBooks(bookname);

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