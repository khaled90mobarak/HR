package fortopapps.hr.checkinout;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by khaled on 01/01/2016.
 */
public class NetworkInfo {


    public String NetWorkname ;
    public String RouterIp ;

    // get the network information
    public static NetworkInfo getNetworkInfo (Context _context)
    {

        final WifiManager manager = (WifiManager) _context.getSystemService(_context.WIFI_SERVICE);


        WifiInfo wifiInfo = manager.getConnectionInfo();

        NetworkInfo network_data = new NetworkInfo();
        String name = wifiInfo.getSSID();
        int ipAddress = wifiInfo.getIpAddress();



        final DhcpInfo dhcp = manager.getDhcpInfo();

        String dns1 = intToIP(dhcp.dns1);
        String dns2 = intToIP(dhcp.dns2);
        String gatWay = intToIP(dhcp.gateway);
        String leaseduration = intToIP(dhcp.leaseDuration);
        String netMask = intToIP(dhcp.netmask);
        String serveraddress = intToIP(dhcp.serverAddress);


        String ipAddressValue = String.format("%d.%d.%d.%d",
                (ipAddress & 0xff),
                (ipAddress >> 8 & 0xff),
                (ipAddress >> 16 & 0xff),
                (ipAddress >> 24 & 0xff));

        network_data.NetWorkname = name;
        network_data.RouterIp =  serveraddress;


        network_data.NetWorkname =  "network100";
        network_data.RouterIp = "192.168.1.100";


        return network_data ;


    }

    /////////////////////////////

    public static String intToIP(int i) {
        return (( i & 0xFF)+ "."+((i >> 8 ) & 0xFF)+
                "."+((i >> 16 ) & 0xFF)+"."+((i >> 24 ) & 0xFF));
    }


}
