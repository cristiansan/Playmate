package app.playmate;

import android.widget.ProgressBar;

/**
 * Created by SFRIAS on 16/01/2015.
 */
public interface OnTaskCompleted {


    void onTaskCompleted(Object s);

    void onTaskCompleted(Object s, Object o, ProgressBar progressBar);


}
