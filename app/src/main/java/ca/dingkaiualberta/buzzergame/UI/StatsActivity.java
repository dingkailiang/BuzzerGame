package ca.dingkaiualberta.buzzergame.UI;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


import ca.dingkaiualberta.buzzergame.Module.SingleGame;
import ca.dingkaiualberta.buzzergame.R;

public class StatsActivity extends Activity {
    private SingleGame game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

    }
}
