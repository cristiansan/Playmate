package app.playmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Votante extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votante);
    }

    public void btnranking(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Ranking.class);
        startActivity(intent);
    }
}
