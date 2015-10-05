package ca.dingkaiualberta.buzzergame.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import ca.dingkaiualberta.buzzergame.Module.Recorder;
import ca.dingkaiualberta.buzzergame.R;

public class StatsActivity extends Activity {
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        result = (TextView) this.findViewById(R.id.result);
        result.setText(Recorder.getInstance().toString());
    }

    public void onClear(View button){
        Recorder.getInstance().clear(this);
        result.setText(Recorder.getInstance().toString());
    }
    // Following line based on http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application/2197841#2197841 retrieved 2015-10-04
    public void onEmail(View button){
        Recorder.getInstance().email(this);
    }
}
