package app.playmate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Foto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Competitor competitor=(Competitor)getIntent().getParcelableExtra("competitor");

        setContentView(R.layout.activity_foto);

        ImageView imageView=(ImageView)findViewById(R.id.imageViewl);

        if(competitor!=null && competitor.getImagenes()!=null && competitor.getImagenes().length>0){
            Bitmap bitmap=competitor.getImagenes()[0].getImage();
            imageView.setImageBitmap(bitmap);
        }





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
