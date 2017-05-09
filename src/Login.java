import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.Base64;

public class Login extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
   
       
        
       response.setStatus(302);
    
       //response.sendRedirect("https://api.pge.com/datacustodian/test/oauth/v2/authorize");
       
       
        response.sendRedirect("https://api.pge.com/datacustodian/oauth/v2/authorize?client_id=ea25a370d35f45f59d41af603b4d912b&redirect_uri=https://www.justsmartthings.com/TestWebProject/Welcome&response_type=code");
        

      /*  
        if(Validate.checkUser(email, pass))
        
            RequestDispatcher rs = request.getRequestDispatcher("Welcome");
            rs.forward(request, response);
        
        else
        {
           out.println("Username or Password incorrect");
           RequestDispatcher rs = request.getRequestDispatcher("index.html");
           rs.include(request, response);
        }*/
    }  
}