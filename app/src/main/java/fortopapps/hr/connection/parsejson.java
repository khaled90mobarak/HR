package fortopapps.hr.connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by khaled on 12/28/15.
 */
public class parsejson {

/*
    JSONObject josparse ;

    public parsejson (String json ){

        try {
            josparse = new JSONObject(json) ;
        }
        catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

    public ArrayList ReadJson()
    {

//        Object json = new JSONTokener(data).nextValue();
//        if (json instanceof JSONObject) {
//            //you have an object
//        }
//        else if (json instanceof JSONArray) {
//            //you have an array
//        }
        ArrayList Arr = new ArrayList();
        if (josparse instanceof Map)
        {

        }
        else if (josparse instanceof List){

         }


        return null;
    }


    public String GetValue(  String key)
    {
        try {
            return   jobj.getString(key);
        }
        catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

      return  "";
    }
*/





    public ArrayList GetArray(JSONArray jsonarr)
    {
        ArrayList arr = new ArrayList();

        try {

            JSONObject jRepliesParameters ;
            for (int i=0; i<jsonarr.length() ;i++)
            {

                jRepliesParameters = jsonarr.getJSONObject(i);

                arr.add(jRepliesParameters);

            }

        }
        catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return arr;
    }

    public ArrayList GetArray(String json)
    {

        try {

            JSONArray obj = new JSONArray(json);

          return   GetArray(obj);

         }
        catch (JSONException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
        }

        return null;
  }

    public Map GetDic ( String json )
    {

        try {
        JSONObject  jObject = new   JSONObject(json);

         return GetDic(jObject);
    }
    catch (JSONException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }

 return null;
  }
    public Map GetDic ( JSONObject  jObject )
    {
        Map<String,Object> map = new HashMap<String,Object>();

        try {


            Iterator iter = jObject.keys();

            while(iter.hasNext()){
                String key = (String)iter.next();
                Object aObj = jObject.get(key);
                // String value = jObject.getString(key);
                map.put(key,aObj);
            }
        }
        catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return map;
    }


}
