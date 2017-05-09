import java.io.*;
import java.net.URL;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.omg.CORBA.NameValuePair;

import com.csvreader.CsvReader;

import java.sql.*;
import java.util.ArrayList;
import java.net.HttpURLConnection;
 


public class Dashboard extends HttpServlet{
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 	String cell1 = null, cell2 = null, cell3 = null, cell4 = null;
	        response.setContentType("text/html;charset=UTF-8");
		 	

	        System.out.println("Welcome doPost method");
	        
	        CsvReader WemoCsv = new CsvReader("C:/Users/Prajakta/Desktop/WemoCalculated.csv");
	        WemoCsv.readHeaders();
	        
	        while(WemoCsv.readRecord()){
		        cell1 = WemoCsv.get(0);
		        System.out.println(WemoCsv.get(0));
		        
		         cell2 = WemoCsv.get(1);
		        System.out.println(WemoCsv.get(1));
		        
		         cell3 = WemoCsv.get(2);
		        System.out.println(WemoCsv.get(2));
		        
		         cell4 = WemoCsv.get(3);
		        System.out.println(WemoCsv.get(3));  
	        }
	        
	        
	        //request.getRequestDispatcher("dashboard.html").forward(request, response);
            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard/dashboard.jsp");
            request.setAttribute("cell1",cell1);
            request.setAttribute("cell2",cell2);
            request.setAttribute("cell3",cell3);
            request.setAttribute("cell4",cell4);
            // set your String value in the attribute
            dispatcher.forward( request, response );
	        
	        
	       
	 }

}
