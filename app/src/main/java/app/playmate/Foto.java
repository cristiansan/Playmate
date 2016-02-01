package app.playmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Foto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
    }
    public void btnnext(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Foto.class);
        startActivity(intent);
    }

    public void btnprev(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Foto.class);
        startActivity(intent);
    }
}
