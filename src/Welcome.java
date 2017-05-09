import java.io.*;
import java.net.URL;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.codec.binary.Base64;


public class Welcome extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //System.out.println(request.getParameter("authorization_code")+"  check auth code post");
        request.getRequestDispatcher("welcome.html").forward(request, response);
        System.out.println("Welcome doPost method");
        
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        System.out.println("Post body data"+data);
       // PrintWriter out = response.getWriter();
        //out.println("Welcome user");
      }  
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	request.getRequestDispatcher("welcome.html").forward(request, response);
    	System.out.println("Welcome doGet method");
    	System.out.println(request.getParameter("authorization_code")+"  check auth code get");
    	String auth_code = request.getParameter("authorization_code");
    	if(auth_code != null){
    		try {
				sendPost(auth_code);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	
    }
    private void sendPost(String authorizationcode) throws Exception {

		String url = "https://api.pge.com/datacustodian/oauth/v2/token";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		//con.setRequestProperty("User-Agent", USER_AGENT);
		//con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String name = "ea25a370d35f45f59d41af603b4d912b";
		String password = "4d918d683bf94756bb3d12ae3b89b815";

		String authString = name + ":" + password;
		//System.out.println("auth string: " + authString);
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);
		//System.out.println("Base64 encoded auth string: " + authStringEnc);

		
		con.setRequestProperty("Authorization", "Basic " + authStringEnc);
		
		String urlParameters = "grant_type=authorization_code&code="+authorizationcode+"&redirect_uri=https://www.justsmartthings.com/TestWebProject/Welcome";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
    
}