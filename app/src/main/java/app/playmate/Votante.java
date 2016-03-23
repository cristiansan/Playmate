package app.playmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Votante extends AppCompatActivity implements  OnTaskCompleted{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votante);
    }

    public void btnranking(View v)
    {

        HttpAsyncTask rankingTask=new HttpAsyncTask(this,getBaseContext());
        rankingTask.execute(MainActivity.urlServer + MainActivity.path + "/getRanking?count=10&appName=playmate");



    }

    @Override
    public void onTaskCompleted(Object s) {

        Competitor[] competitors= null;

        if(s!=null && ((String)s).length()>0) {
            try{
                JSONArray arr = new JSONArray((String) s);
                competitors= new Competitor[arr.length()];

                if(arr!=null && arr.length()>0){
                    for (int i=0; i<arr.length();i++){
                        JSONObject jsonObject= arr.getJSONObject(i);
                        Competitor competitor= new Competitor(jsonObject);
                        competitors[i]=competitor;
                    }

                }

            }catch (Exception e){

            }

        }




        Intent intent = new Intent(getApplicationContext(), Ranking.class);
        intent.putExtra("competitors",competitors);
        startActivity(intent);

    }

    @Override
    public void onTaskCompleted(Object s, Object o, ProgressBar progressBar) {

    }

    @Override
    public void onTaskPutCompleted(Object s) {

    }


}
