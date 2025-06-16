package in.login.server;

import jakarta.servlet.RequestDispatcher;
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


@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		// TODO Auto-generated method stub
		
			PrintWriter out=response.getWriter();			
			String e=request.getParameter("email");
			String u=request.getParameter("username");
			String p=request.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection dm=DriverManager.getConnection("jdbc:mysql://localhost:3306/registerdb","root","root");
			PreparedStatement pm=dm.prepareStatement("insert into reg values(?,?,?)");
			pm.setString(1,e);
			pm.setString(2,u);
			pm.setString(3,p);
			int count=pm.executeUpdate();
			if(count>0)
			{
				
				response.setContentType("text/html");
				out.print("<h3 style='color:green'>REGISTERED SUCCESSFULLY</h3>");
				RequestDispatcher rd=request.getRequestDispatcher("/Register.html");
				rd.include(request, response);
			}
			else {
				
				response.setContentType("text/html");
				out.print("<h3 style='color:RED'>REGISTERED SUCCESSFULLY</h3>");
				RequestDispatcher rd=request.getRequestDispatcher("/Register.html");
				rd.include(request, response);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			response.setContentType("text/html");
			out.print("<h3 style='color:RED'>Exception!!!"+ex.getMessage()+"</h3>");
			RequestDispatcher rd=request.getRequestDispatcher("/Register.html");
			rd.include(request, response);
			
			
		}
		}
	}


