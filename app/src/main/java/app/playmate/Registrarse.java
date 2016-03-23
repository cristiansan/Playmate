package app.playmate;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Date;

public class Registrarse extends AppCompatActivity implements OnTaskCompleted  /*, AdapterView.OnItemSelectedListener*/ {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playmate_registrarse);


        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.country_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);



        Button button = (Button) findViewById(R.id.newuser);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editTextNombre = (EditText) findViewById(R.id.editTextNombre);
                EditText editTextApellido = (EditText) findViewById(R.id.editTextApellido);
                EditText editTextFechaNac = (EditText) findViewById(R.id.editTextFechaNac);
                //EditText editTextPais = (EditText) findViewById(R.id.editTextPais);
                EditText editTextMail = (EditText) findViewById(R.id.editTextMail);



                String iso=getResources().getStringArray(R.array.country_iso_array)[spinner.getSelectedItemPosition()];


                Competitor competitor = new Competitor();
                competitor.setNombre(editTextNombre.getText().toString());
                competitor.setApellido(editTextApellido.getText().toString());
                competitor.setBiografia(" ");
                competitor.setPais(iso);
                competitor.setFechaNacimiento(new Date());
                competitor.setMail(editTextMail.getText().toString());
                competitor.setUser2(editTextMail.getText().toString());
                Toast.makeText(getApplicationContext(),""+iso, Toast.LENGTH_LONG).show();
                try{
                    doPut(competitor.toJSon().toString());
                }catch (Exception e){

                }



            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void doPut(String body) {

        HttpPutAsyncTask httpPutAsyncTask = new HttpPutAsyncTask(this);
        httpPutAsyncTask.setContent(body);
        httpPutAsyncTask.execute(MainActivity.urlServer + MainActivity.path + "/newCompetitor");


    }
/*
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();


        String iso=getResources().getStringArray(R.array.country_iso_array)[position];
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item+" "+iso, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
*/

    @Override
    public void onTaskCompleted(Object s) {

    }

    @Override
    public void onTaskCompleted(Object s, Object o, ProgressBar progressBar) {

    }

    @Override
    public void onTaskPutCompleted(Object s) {
        String string=(String)s;
        System.out.println("onTaskPutCompleted: "+string);

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Registrarse Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://app.playmate/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Registrarse Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://app.playmate/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
