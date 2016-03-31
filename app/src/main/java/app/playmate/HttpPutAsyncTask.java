package app.playmate;

import android.os.AsyncTask;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by SFRIAS on 16/01/2015.
 */
public class HttpPutAsyncTask extends AsyncTask<String, Void, String> {

    private OnTaskCompleted listener;
    private String content;

    public HttpPutAsyncTask(OnTaskCompleted listener){
        this.listener=listener;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    protected String doInBackground(String... urls) {

        return getFromURL(urls[0]);
    }

   // @Override
  //  protected void onPostExecute(String s) {
   //     listener.onTaskCompleted(s);
   // }

    public String getFromURL(String urlString){


        HttpURLConnection urlConnection = null;

        InputStream inputStream = null;

        String result = "";
        try {


            URL url = new URL(urlString);

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("PUT");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
            out.write(content);
            out.close();

            System.err.println("responseCode: "+ urlConnection.getResponseCode());

            inputStream=urlConnection.getInputStream();


            // convert inputstream to string
            if(inputStream != null){
                result = convertInputStreamToString(inputStream);

            }


        } catch (Exception e) {
            //Log.d("InputStream", e.getMessage());
        }

        return result;
    }


    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    @Override
    protected void onPostExecute(String result) {
        //super.onPostExecute(s);
        if(listener!=null){
            listener.onTaskPutCompleted(result);
        }

    }
}
