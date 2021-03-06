package app.playmate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import java.util.ArrayList;

public class Ranking extends AppCompatActivity implements AbsListView.OnItemClickListener{


    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Parcelable[] competitors=getIntent().getParcelableArrayExtra("competitors");

        ArrayList<Competitor> arrayList= new ArrayList<Competitor>();
        for(int i=0;i<competitors.length;i++){
            Competitor competitor= (Competitor)competitors[i];
            arrayList.add(competitor);
        }



        setContentView(R.layout.activity_ranking);

        mListView = (AbsListView)findViewById(android.R.id.list);
        mAdapter = new MyContentArrayAdapter(getApplicationContext(), arrayList);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Parcelable[] competitors=getIntent().getParcelableArrayExtra("competitors");

        if(competitors!=null && competitors.length > position){
            Intent intent = new Intent(getApplicationContext(), Foto.class);
            intent.putExtra("competitor",competitors[position]);
            startActivity(intent);
        }


    }



 /*   public void btntofoto(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Foto.class);
        startActivity(intent);
    }*/
}
