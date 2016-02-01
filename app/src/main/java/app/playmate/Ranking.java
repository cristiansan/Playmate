package app.playmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Ranking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
    }

    public void btntofoto(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Foto.class);
        startActivity(intent);
    }
}
