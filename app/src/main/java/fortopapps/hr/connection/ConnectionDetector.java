package fortopapps.hr.connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetector {
	
	 /**
     * this class used  to check connection
     * @param constractor  context - activity context
     * 
     * 
     * */
	private Context _context;
	 
    public ConnectionDetector(Context context){
        this._context = context;
    }
 
    
    /**
     * Function to check connection
     
     * @return  boolean
     *       true - if the connection is available and network status (is connected) 
     *       false if the connection is not available and network status (is not connected)
     * */
   
 // Check weather Internet connection is available or not
 	public boolean isConnectingToInternet() {
 		System.out.println("in checkInternetConnection");
 		final ConnectivityManager conMgr = 
 			(ConnectivityManager)_context.getSystemService(Context.CONNECTIVITY_SERVICE);
 		if (conMgr.getActiveNetworkInfo() != null
 				&& conMgr.getActiveNetworkInfo().isAvailable()
 				&& conMgr.getActiveNetworkInfo().isConnected()) {
 			System.out.println("in if in checkInternetConnection");
 			return true;
 		} else {
 			System.out.println("Internet Connection Not Present");
 			return false;
 		}
 	}//end method checkInternetConnection
 	/*==================================================================*/
 	

}
