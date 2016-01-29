package app.playmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnplaymate(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Playmate.class);
        startActivity(intent);
    }

    public void btnvotante(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Votante.class);
        startActivity(intent);
    }

}
