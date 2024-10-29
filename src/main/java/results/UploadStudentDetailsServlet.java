package results;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/uploadstudentdetails")
public class UploadStudentDetailsServlet extends HttpServlet {
	Connection con;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadStudentDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","srinadh");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			String s1=request.getParameter("rollnumber");
			String s2=request.getParameter("name");
			String s3=request.getParameter("mobileno");
			String s4=request.getParameter("fmobileno");
			String s5=request.getParameter("dob");
			String s6=request.getParameter("standerd");
			String s7=request.getParameter("branch");
			PreparedStatement pstmt=con.prepareStatement("insert into studentdetails values(?,?,?,?,?,?,?)");
			pstmt.setString(1, s1);
			pstmt.setString(2, s2);
			pstmt.setString(3, s3);
			pstmt.setString(4, s4);
			pstmt.setString(5, s5);
			pstmt.setString(6, s6);
			pstmt.setString(7, s7);
			pstmt.executeUpdate();
			PrintWriter pw=response.getWriter();
			pw.println("<html><body bgcolor=blue text=red><center>");
			pw.println("<h1>....................You have registred successfully................</h1>");
			//pw.println("<a href=StudentLogin.html>Login</a>");
			pw.println("</center></body></html>");
//			response.sendRedirect("StudentLogin.html");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
	}

}

