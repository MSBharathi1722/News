import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Login extends HttpServlet{

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

        String mail = request.getParameter("mail");
        String password = request.getParameter("pwd");
        UserDb dbs = new UserDb();
        String name ;
        String passw ;

        passw = dbs.selectQuery(mail, "password"); 
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (password.equals(passw)){            
            name = dbs.selectQuery(mail, "username");
           
            RequestDispatcher rd = request.getRequestDispatcher("news.html");
            rd.forward(request, response);
        }
        
        else{
            out.println("<font color='red'><b>You have entered invalid user credentials </b></font>");
            RequestDispatcher rds = request.getRequestDispatcher("index.html");
            rds.include(request, response);
        }

    }

}
