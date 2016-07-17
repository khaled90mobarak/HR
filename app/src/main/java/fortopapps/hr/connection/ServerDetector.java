package fortopapps.hr.connection;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class ServerDetector {
	
	
	
	 /**
     * this class used  to check the server 
     * 
     * 
     * 
     * */
	 String serverStatus; // string that hold the server status
	
	
	 /**
	     * Function to check the server
	     * @param uri - url of sever
	     * @param methode - method type(Get ,Post ,put).
	     * @param isOk(Boolean variable) 
	     *      - false - if the server is not available (response code 404)
	     *      - true  - if the server is available (response code 200)
	     * 
	     * */
	 
	 
	public boolean CheckServer(String uri,String method)
	{
		boolean isOK = false;
		try {
			System.out.println("*** GET Created Customer **");
		       URL getUrl = new URL(uri);
		       HttpURLConnection connection = (HttpURLConnection)getUrl.openConnection();
		       connection.setRequestMethod(method);
		       connection.setRequestProperty("WS-Login", "{\"appName\":\"miworld\", \"password\":\"miworldws\"}");
		       System.out.println("Content-Type: " + connection.getContentType());

		       if (connection .getResponseCode() == 200) {
		    	   isOK=true;
		           InputStream in = new BufferedInputStream(connection.getInputStream());
		            serverStatus = readStream(in); //assuming that "http://yourserverurl/yourstatusmethod" returns OK or ERROR depending on your server status check
		           Log.i("serverStatus= ",""+ serverStatus);
		          
		  
		       connection.disconnect();
			}
		} catch (Exception e) {
			System.out.println( " "+e.getMessage());
			serverStatus=" ";
			isOK=false;
		}
		return isOK;
		
	}
	
	 /**
     * Function to get the server status
     * @param in (InputStream) - application context
     * @return  string - hold the server status
     * */
	public static String readStream (InputStream in) throws IOException {
	    StringBuffer out = new StringBuffer();
	    byte[] b = new byte[4096];
	    for (int n; (n = in.read(b)) != -1;) {
	        out.append(new String(b, 0, n));
	    }
	    return out.toString();
	}

}
