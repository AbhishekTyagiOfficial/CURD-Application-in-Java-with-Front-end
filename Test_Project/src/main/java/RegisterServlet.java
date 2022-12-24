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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private final static String query = "insert into test_project ( name, email, contact, gender, qualification, percentage, certificate, city) values(?,?,?,?,?,?,?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		
		// Set Content Type
		res.setContentType("text/html");
		
		// link Bootstrap
		pw.println("<link href='css/bootstrap.css' rel='stylesheet'></link>");
		
		// Get The values
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String contact = req.getParameter("contact");
		String gender = req.getParameter("gender");
		String qualification = req.getParameter("qul");
		String percentage = req.getParameter("per");
		String certificate = req.getParameter("cer");
		String city = req.getParameter("city");
		
		
		// Load JDBC Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		// Create Connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///test","root","admin");
				PreparedStatement ps = con.prepareStatement(query);
				){
			
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, contact);
			ps.setString(4, gender);
			ps.setString(5, qualification);
			ps.setString(6, percentage);
			ps.setString(7, certificate);
			ps.setString(8, city);
			
			int count = ps.executeUpdate();
			if (count==1)
			{
				pw.println("<h3 class='bg-success' text-center>Record Added Successfully.</h3>");
			}
			else {
				pw.println("<h3 class='bg-danger' text-center>Record Not Added.</h3>");
			}
		} 
		catch (SQLException se) {
			pw.println("<h3 class='bg-danger'>"+"se.getMessage()"+"</h3>");
			se.getStackTrace();
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		pw.println("<a href='Index.html'><button class='btn btn-primary'>Home</button></a>");
		pw.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
