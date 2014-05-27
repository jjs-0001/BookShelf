package jp.co.jjs.java_seminar;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/crud")
    private DataSource ds;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    String title = request.getParameter("title");
	    String isbn = request.getParameter("isbn");
	    String writer = request.getParameter("writer");
	    String publisher = request.getParameter("publisher");
	    int price = Integer.parseInt(request.getParameter("price"));
	    int i = 1;

	    try (Connection con = ds.getConnection();
                PreparedStatement ps = con
                        .prepareStatement("SELECT MAX(ID) FROM BOOKSHELF")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    i = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


	    try (Connection con = ds.getConnection();
                PreparedStatement ps = con
                        .prepareStatement("INSERT INTO BOOKSHELF VALUES(?, ?, ?, ?, ?, ?);")) {
            ps.setInt(1, i + 1);
            ps.setString(2, title);
            ps.setString(3, isbn);
            ps.setString(4, writer);
            ps.setString(5, publisher);
            ps.setInt(6, price);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

}
