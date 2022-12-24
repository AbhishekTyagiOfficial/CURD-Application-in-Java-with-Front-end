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

@WebServlet("/editurl")
public class EditDataServlet extends HttpServlet {
	private final static String query = "Select name, email, contact, gender, qualification, percentage, certificate, city from test_project where id=?";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		PrintWriter pw = res.getWriter();

		// Set Content Type
		res.setContentType("text/html");

		// link BootStrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css' ></link>");
		pw.println("<link rel='stylesheet' href='css/custom.css' ></link>");

		// Set Header
		pw.println("<h2 class='text-danger text-center'>** EDIT DATA **</h2>");

		// get the id for Update
		int id = Integer.parseInt(req.getParameter("id"));

		// load the JDBC Driver 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Generate The COnnection
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///test", "root", "admin");
				PreparedStatement ps = con.prepareStatement(query);) {
			ps.setInt(1, id);
			
			// ResultSet
			ResultSet rs = ps.executeQuery();
			rs.next();

			// get the value into Table
			pw.println("<body class='body'>");
				pw.println("<form action='edit?id="+id+"' method='post' class='frm'>");
				pw.println("<div style='margin:auto;margin-top:50px;width:800px;'>");
						pw.println("<table class='table'>");
				
				// button
			pw.println("<a href='Index.html'><button type='button' class='btn btn-primary mb-3' >Home</button></a>");
			pw.println("<a href='showdata'> <button type='button' class='btn btn-warning mb-3' style='margin-left: 39em;'> Show Data </button> </a> ");
							pw.println("<tr>");
								pw.println("<td> Name </td>");
								pw.println("<td> <input type='text' name='name'  value='" + rs.getString(1) + "' /></td>");
							pw.println("</tr>");
				
							pw.println("<tr>");
								pw.println("<td> Email </td>");
								pw.println("<td> <input type='email' name='email'  value='" + rs.getString(2) + "' /></td>");
							pw.println("</tr>");
				
							pw.println("<tr>");
								pw.println("<td> Contact </td>");
								pw.println("<td> <input type='text' name='contact'  value='" + rs.getString(3) + "' /></td>");
							pw.println("</tr>");
				
							pw.println("<tr>");
								pw.println("<td> Gender </td>");
								pw.println("<td> <input type='text' name='gender'  value='" + rs.getString(4) + "' /></td>");
							pw.println("</tr>");
				
							pw.println("<tr>");
								pw.println("<td> Qulification </td>");
								pw.println("<td> <input type='text' name='qul'  value='" + rs.getString(5) + "' /></td>");
							pw.println("</tr>");
							
							pw.println("<tr>");
								pw.println("<td> Percentage </td>");
								pw.println("<td> <input type='text' name='per'  value='" + rs.getString(6) + "' /></td>");
							pw.println("</tr>");
				
							pw.println("<tr>");
								pw.println("<td> Certificate </td>");
								pw.println("<td> <input type='text' name='cer'  value='" + rs.getString(7) + "' /></td>");
							pw.println("</tr>");
				
										
							pw.println("<tr>");
								pw.println("<td> City </td>");
								pw.println("<td> <input type='text' name='city'  value='" + rs.getString(8) + "' /></td>");
							pw.println("</tr>");
			// Button
		 // pw.println("<td> <a href='Index.html'><button type='button' class='btn btn-primary'> Home </button> </a> </td>");
		//	pw.println("<td> <a href='showdata'> <button type='button' class='btn btn-warning'> Show Data </button> </a> </td>");
					
			
					pw.println("</table>");
					pw.println(" <button type='submit' class='btn btn-success' style='margin-left:8.5cm;'> Update </button>");
				pw.println("</div>");
			pw.println("</form>");
				
			pw.println("</body>");
			
			
		
		} catch (SQLException se) {
			pw.println("<h3>'" + se.getMessage() + "'</h3>");
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}


