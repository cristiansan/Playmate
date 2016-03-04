package app.playmate;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by SFRIAS on 16/01/2015.
 */
public class HttpAsyncTask extends AsyncTask<String, Void, String> {

    private OnTaskCompleted listener;
    private static Context context;


    public HttpAsyncTask(OnTaskCompleted listener, Context context){
        this.listener=listener;
        this.context= context;
    }

    @Override
    protected String doInBackground(String... urls) {

        return getFromURL(urls[0]);
    }

    @Override
    protected void onPostExecute(String s ) {
        if(listener!=null) {
            listener.onTaskCompleted(s);
        }
    }

    public static String getFromURL(String urlString){

        //Log.i("TEST push", "contenidoStr 333");


        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;


        String result = null;
        try {


            URL url = new URL(urlString);

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }

            result = buffer.toString();

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }


}
