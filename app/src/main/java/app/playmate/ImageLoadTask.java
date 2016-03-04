package app.playmate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by SFRIAS on 16/01/2015.
 */
public class ImageLoadTask extends AsyncTask<Void, Void, View> {

    private Object content;
    private View imageView;
    private OnTaskCompleted listener;
    private ProgressBar progressBar;

    public ImageLoadTask(Object content, View imageView, ProgressBar progressBar, OnTaskCompleted listener) {
        this.content = content;
        this.imageView=imageView;
        this.progressBar= progressBar;
        this.listener=listener;
    }

    public ImageLoadTask(Object content, View imageView, OnTaskCompleted listener) {
        this.content = content;
        this.imageView=imageView;
        this.listener=listener;
    }


    @Override
    protected View doInBackground(Void... params) {
        try {

            /*
            if(progressBar!=null){
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setIndeterminate(true);
            }*/

            String imageLink=null;


            imageLink=((CompetitorImage)content).getImageLink();


            URL urlConnection = new URL(imageLink);
            HttpURLConnection connection = (HttpURLConnection) urlConnection
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            ((CompetitorImage) content).setImage(myBitmap);

            //imageView.setImageBitmap(myBitmap);
            return imageView;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(View imageView) {
        //super.onPostExecute(result);
        //content.setImage(result);

        /*
        if(progressBar!=null){
            progressBar.setVisibility(View.GONE);
        }*/

        Bitmap image=null;


        image=((CompetitorImage) content).getImage();

        if(image !=null){
            listener.onTaskCompleted(imageView,content,progressBar);
        }
    }
}
