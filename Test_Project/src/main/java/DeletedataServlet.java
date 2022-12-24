import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteurl")
public class DeletedataServlet extends HttpServlet {
	private final static String query = "Delete from test_project where id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw= res.getWriter();
		
		// Set Context Type
		res.setContentType("text/html");
		
		// Link BootStrap
		pw.println("<link href='css/bootstrap.css' rel='stylesheet'></link>");
		
		// Get The Id for Delete
		int id = Integer.parseInt(req.getParameter("id"));
		
		// Load JDBC Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		// Generate the Connection
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///test","root","admin");
				PreparedStatement ps = con.prepareStatement(query);
				){
			
			// Set the Id for Delete
			ps.setInt(1, id);
			
			int count = ps.executeUpdate();
			if(count==1)
			{
				pw.println("<h2 class='bg-success text-center'>Record Deleted Successfully.</h2>");
			} else {
				pw.println("<h2 class='bg-danger text-center'>Record Not Deleted.</h2>");
			}
			
		}catch(SQLException se) {
			pw.println("<h3 class=bg-danger>"+se.getMessage()+"</h3>");
			se.printStackTrace();
		}
		
		pw.println("<a href='Index.html'><button class='btn btn-primary'>Home</button></a>");
		pw.println("&nbsp; &nbsp;");
		pw.println("<a href='showdata'><button class='btn btn-primary'>Show User</button></a>");
		//pw.println("</div>");
		
		pw.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
