import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class UpdateDataServlet extends HttpServlet {
	private final String query = "update test_project set name=?, email=?, contact=?, gender=?, qualification=?, percentage=?, certificate=?, city=? where id=?";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		
		// set the Content Type
		res.setContentType("text/html");
		
		// Link BootStrap
		pw.println("<link href='css/bootstrap.css' rel='stylesheet'></link>");
		
		// set Id for Update
		int id = Integer.parseInt(req.getParameter("id"));
		 // get the value of change input
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String contact = req.getParameter("contact");
		String gender = req.getParameter("gender");
		String qulification = req.getParameter("qul");
		String percentage = req.getParameter("per");
		String certificate = req.getParameter("cer");
		String city = req.getParameter("city");
		
		// Load JDBC Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// Generate the Connection
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///test","root","admin");
				PreparedStatement ps = con.prepareStatement(query);
				){
			// Set the Value
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, contact);
			ps.setString(4, gender);
			ps.setString(5, qulification);
			ps.setString(6, percentage);
			ps.setString(7, certificate);
			ps.setString(8, city);
			
			// Set the ID
			ps.setInt(9, id);
			
			// ResultSet
//			ResultSet rs = ps.executeUpdate();
//			rs.next();
			
			int count = ps.executeUpdate();
			if (count==1)
			{
				pw.println("<h3 class='bg-success text-center'>Record Updated Successfully.</h3>");
			}
			else {
				pw.println("<h3 class='bg-danger text-center'>Record Not Updated.</h3>");
			}
			
			pw.println("<td> <a href='Index.html'> <button type='button' class='btn btn-success' > Home </button> </a> </td>");
			pw.println("<td> <a href='showdata'> <button type='button' class='btn btn-success ml-5'> Show Data </button> </a> </td>");
			
		}catch(SQLException se) {
			pw.println("<h3>'"+se.getMessage()+"'</h3>");
			se.printStackTrace();
		}
		}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
