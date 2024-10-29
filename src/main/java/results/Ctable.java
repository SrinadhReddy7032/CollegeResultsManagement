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
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/table")
public class Ctable extends HttpServlet {
	Connection con;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ctable() {
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
			String s1=request.getParameter("tname");
			Statement pstmt=con.createStatement();
			ResultSet rs=pstmt.executeQuery("select table_name from all_tables where owner='SYSTEM'");
			PrintWriter pw=response.getWriter();
				pw.println("<html><body bgcolor=red text=yellow><center>");
			while(rs.next()) {
			String f=rs.getString(1);
			if(f.equals(s1)) {
			pw.println("<h1>Welcome-----Royal "+f+"------</h1>");
			pw.println("<a href=Student.html>STUDENT ATTENDANCE CHECKUP</a>");
			}
			}
			pw.println("</center></body></html>");
			//else pw.println("<h2>INVALID USERNAME/PASSWORD</h2>");
			
			//}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

}
