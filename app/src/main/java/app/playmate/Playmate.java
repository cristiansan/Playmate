package app.playmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Playmate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playmate);
    }
    public void btntoregistrada(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Registrada.class);
        startActivity(intent);
    }

    public void btntoregistrarse(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Registrarse.class);
        startActivity(intent);
    }


}
