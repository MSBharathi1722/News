import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
public class Signup extends HttpServlet{

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

        String mail = request.getParameter("mail");
        String password = request.getParameter("pwd");
        String name = request.getParameter("name");
        NewUser dbs = new NewUser();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if(mail.equals(null) || password.equals(null) || name.equals(null)){
                RequestDispatcher rd = request.getRequestDispatcher("signup.html");
                rd.include(request, response);
        }
        else{
            String userExist = dbs.checkUser(mail);
            if(userExist.equals("false")){
                dbs.insertNewUser(mail,name,password);
                RequestDispatcher rd = request.getRequestDispatcher("success.html");
                rd.include(request, response);
            }
            else if(userExist.equals("true")){ 
                out.println("alert('User already exists with the given mail ID')");     
                RequestDispatcher rd = request.getRequestDispatcher("signup.html");
                rd.include(request, response);
            }
            else{
                out.println("<h1>"+userExist+"</h1>");
            }
        }

    }
        
}

