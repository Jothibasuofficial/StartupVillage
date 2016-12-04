package sv.com.login;

import java.io.IOException;
import java.io.PrintWriter;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
 
/**
 * Servlet implementation class MySQLConnect
 */
@WebServlet("/LoginCheck") 
public class LoginCheck extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
 
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
       /*if((username.equalsIgnoreCase("jothibasu"))&&(password.equalsIgnoreCase("admin"))){  
        	out.println("<div style='position: absolute; top:100px; left:400px;'>");
            out.println("<font size='15' color='blue'>Welcome "+username+" !</font>");
            out.println("</div>");
        	RequestDispatcher rd=request.getRequestDispatcher("/courses.html");  
            rd.include(request, response); 
        }  
        else{  
        	out.println("<div style='position: absolute; top:180px; left:400px;'>");
            out.println("<font size='15' color='darkgray'>Sorry UserName or Password Error!</font>");
            out.println("</div>");
            RequestDispatcher rd=request.getRequestDispatcher("/login.html");  
            rd.include(request, response);  
                          
            }*/
       try {
    	   Class.forName("com.mysql.jdbc.Driver");
           String url = "jdbc:mysql://localhost:3306/moviedb";
           String dbusername = "root";
           String dbpassword = "developer";
            Connection conn = DriverManager.getConnection(url,dbusername,dbpassword);
            //out.println(conn);
            
            PreparedStatement pst = conn.prepareStatement("Select username,userpassword from logindetials where username=? and userpassword=?");
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                out.println("Correct login credentials");
            } 
            else {
                out.println("Incorrect login credentials");
            }
        } 
        catch (Exception e) {
            out.println( e.getMessage());
        }
    }
}
