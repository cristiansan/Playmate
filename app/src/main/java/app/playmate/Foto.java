package app.playmate;

import android.graphics.Bitmap;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Foto extends AppCompatActivity  implements OnTaskCompleted{

    private Competitor competitor;
    private ProgressBar progressBar;
    private ImageView imageView;

    private int imageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        competitor=(Competitor)getIntent().getParcelableExtra("competitor");

        setTitle(competitor.getNombre()+" "+ competitor.getApellido());
        setContentView(R.layout.activity_foto);

        imageView=(ImageView)findViewById(R.id.imageView2);
        progressBar = (ProgressBar)findViewById(R.id.progress_content);

        TextView textViewBiography= (TextView)findViewById(R.id.textViewBiography);
        textViewBiography.setText(competitor.getBiografia());

        if(competitor.getImagenes()!=null && competitor.getImagenes().length<2){
            ImageView imageViewl=(ImageView)findViewById(R.id.imageViewl);
            ImageView imageViewr=(ImageView)findViewById(R.id.imageViewr);
            imageViewl.setVisibility(View.GONE);
            imageViewr.setVisibility(View.GONE);
        }




        showImageAt(0);


    }


    public void showImageAt(int i){
        if(competitor!=null && competitor.getImagenes()!=null && competitor.getImagenes().length>i){

            CompetitorImage competitorImage=competitor.getImagenes()[i];
            Bitmap bitmap= competitorImage.getImage();

            if(bitmap==null){

                if(progressBar!=null){
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(true);
                }

                new ImageLoadTask(competitorImage, imageView,progressBar,this).execute();


            }
            else{
                imageView.setImageBitmap(bitmap);
            }
        }


    }


    public void btnnext(View v)
    {

        if(competitor!=null && competitor.getImagenes()!=null ){
            if(imageIndex<competitor.getImagenes().length){
                imageIndex++;
            }else{
                imageIndex=0;
            }

            showImageAt(imageIndex);
        }

    }

    public void btnprev(View v)
    {
        imageIndex--;
        if(imageIndex<0){
            if(competitor!=null && competitor.getImagenes()!=null && competitor.getImagenes().length>0){
                imageIndex=competitor.getImagenes().length-1;
            }else{
                imageIndex=0;
            }

        }

        showImageAt(imageIndex);


        //Intent intent = new Intent(getApplicationContext(), Foto.class);
        //startActivity(intent);
    }


    @Override
    public void onTaskCompleted(Object s) {

    }


    @Override
    public void onTaskCompleted(Object imageView,Object o, ProgressBar progressBar) {
        ImageView iv=(ImageView)imageView;
        CompetitorImage competitorImage= (CompetitorImage)o;


        if(progressBar!=null){
            progressBar.setVisibility(View.GONE);
        }


        if(competitorImage!= null && competitorImage.getImage()!=null){
            if(iv!=null){
                iv.setImageBitmap(competitorImage.getImage());
            }

        }


    }


}
