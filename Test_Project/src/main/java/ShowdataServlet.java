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

@WebServlet("/showdata")
public class ShowdataServlet extends HttpServlet {
	private final static String query = "Select * from test_project";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		
		// Set the ContentType
		res.setContentType("text/html");
		
		// Link BootStrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		pw.println("<link rel='stylesheet' href='css/custom.css'></link>");
		pw.println("<h2 class=text-danger style='text-align:center'>** SHOW TABLE DATA **</h2>");
		
		
		// load JDBC Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (Exception e ) {
			e.printStackTrace();
			}
		
		// Load the Connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///test","root","admin");
				PreparedStatement ps = con.prepareStatement(query);
				){
			ResultSet rs = ps.executeQuery();
				pw.println("<body class='body'>");
				pw.println("<form class='frm' >");
					pw.println("<div style='margin:auto;margin-top:50px;width:1200px;'>");
						pw.println("<table class='table'>");
						pw.println("<a href='Index.html'><button type='button' class='btn btn-primary mb-3'>Home</button></a>");
							pw.println("<tr>");
								pw.println("<td> ID </td>");
								pw.println("<td> Name</td>");
								pw.println("<td> Email </td>");
								pw.println("<td> Contact </td>");
								pw.println("<td> Gender</td>");
								pw.println("<td> Qulification </td>");
								pw.println("<td> Percentage </td>");
								pw.println("<td> Certificate </td>");
								pw.println("<td> City </td>");
								pw.println("<td> Edit </td>");
								pw.println("<td> Delete </td>");
							pw.println("</tr>");
							
							while(rs.next())
							{
								pw.println("<tr>");
									pw.println("<td>" +rs.getInt(1)+ "</td>");
									pw.println("<td> "+rs.getString(2)+"</td>");
									pw.println("<td> "+rs.getString(3)+" </td>");
									pw.println("<td> "+rs.getString(4)+" </td>");
									pw.println("<td> "+rs.getString(5)+" </td>");
									pw.println("<td> "+rs.getString(6)+" </td>");
									pw.println("<td> "+rs.getString(7)+" </td>");
									pw.println("<td> "+rs.getString(8)+" </td>");
									pw.println("<td> "+rs.getString(9)+" </td>");
									pw.println("<td> <a href='editurl?id="+rs.getInt(1) +"'> <button type='button' class='btn btn-warning'>Edit</button></a> </td>");
									pw.println("<td> <a href='deleteurl?id="+rs.getInt(1)+"'> <button type='button' class='btn btn-danger'>Delete</button></a>  </td>");
								pw.println("</tr>");
							}
						pw.println("</table>");
					//	pw.println("<a href='Index.html'><button type='button' class='btn btn-success'>Home</button></a>");
				pw.println("</div>");
				pw.println("</form>");
			pw.println("</body>");
			
		}catch(SQLException se) {
			pw.println("<h3 class=class='bg-danger'>"+se.getMessage()+"</h3>");
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		// close the stream
		pw.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
