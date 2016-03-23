package app.playmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by SFRIAS on 16/01/2015.
 */
public class MyContentArrayAdapter extends ArrayAdapter<Competitor> implements OnTaskCompleted {

    private final Context context;
    private final List<Competitor> values;




    public MyContentArrayAdapter(Context context, List<Competitor> values) {
        super(context, R.layout.list_item, values);
        this.context = context;
        this.values = values;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);



        Competitor content=values.get(position);

        ImageView imageView= (ImageView) rowView.findViewById(R.id.image);
        TextView textViewTitle = (TextView) rowView.findViewById(R.id.title);
       // TextView textViewDescription = (TextView) rowView.findViewById(R.id.description);


        ProgressBar progressBar = (ProgressBar)rowView.findViewById(R.id.progress_content);
        textViewTitle.setText(content.getNombre() + " " + content.getApellido() + " - " + obtenerEdad(content.getFechaNacimiento()));
        //textViewDescription.setText("" + content.getFechaNacimiento().toString());

        ImageView flag=(ImageView)rowView.findViewById(R.id.flag);

        int resId=getContext().getResources().getIdentifier("flag_"+content.getPais() , "drawable", getContext().getPackageName());
        if(resId!=0){
            flag.setImageResource(resId);
        }





        if(content.getImagenes()!=null && content.getImagenes().length>0){
            if(content.getImagenes()[0].getImage()==null){

                if(progressBar!=null){
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(true);
                }

                new ImageLoadTask(content.getImagenes()[0], imageView,progressBar,this).execute();



            }
            else{
                imageView.setImageBitmap(content.getImagenes()[0].getImage());
            }
        }


        return rowView;
    }


    public int obtenerEdad(Date fechaNacimiento){
        long today= new Date().getTime();
        long birt=fechaNacimiento.getTime();

        long diff= today-birt;
        long div=31536000000L;
        return (int)(diff/(div));
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

    @Override
    public void onTaskPutCompleted(Object s) {

    }


}
