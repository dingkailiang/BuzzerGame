package ca.dingkaiualberta.buzzergame.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ca.dingkaiualberta.buzzergame.Module.Recorder;
import ca.dingkaiualberta.buzzergame.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Recorder.getInstance().load(this);
    }

    public void onSingleGame(View button) {
        Intent intent = new Intent(this, SingleGameActivity.class);
        this.startActivity(intent);
    }

    public void onMultiGame(View button) {
        Intent intent = new Intent(this, MultiGameActivity.class);
        this.startActivity(intent);
    }

    public void onViewStats(View button) {
        Intent intent = new Intent(this, StatsActivity.class);
        this.startActivity(intent);
    }
}
