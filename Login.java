package in.login.server;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Log")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();			
		String e=request.getParameter("em");
		String p=request.getParameter("pwd");
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection dm=DriverManager.getConnection("jdbc:mysql://localhost:3306/registerdb","root","root");
		PreparedStatement pm=dm.prepareStatement("select * from reg where email=? and password=?");
		pm.setString(1,e);
		pm.setString(2,p);
		ResultSet rs=pm.executeQuery();
		if(rs.next())
		{   
			//HttpSession session=request.getSession();
		    //session.setAttribute("session_username",rs.getString("username"));
			response.setContentType("text/html");
			out.print("<h3 style='color:green'>LOGIN SUCCESSFULLY</h3>");
			RequestDispatcher rd=request.getRequestDispatcher("/Profile.html");
			rd.include(request, response);
		}
		else {
			
			response.setContentType("text/html");
			out.print("<h3 style='color:RED'> LOGIN UNSUCCESSFULLY</h3>");
			RequestDispatcher rd=request.getRequestDispatcher("/Login.html");
			rd.include(request, response);
		}
	}
	catch(Exception ex) {
		ex.printStackTrace();
		response.setContentType("text/html");
		out.print("<h3 style='color:RED'>Exception!!!"+ex.getMessage()+"</h3>");
		RequestDispatcher rd=request.getRequestDispatcher("/Login.html");
		rd.include(request, response);
		
		
	}
	
	}

}
