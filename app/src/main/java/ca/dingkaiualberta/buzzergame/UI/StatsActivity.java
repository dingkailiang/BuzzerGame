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
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , "");
        i.putExtra(Intent.EXTRA_SUBJECT, "BuzzerStats");
        i.putExtra(Intent.EXTRA_TEXT   , Recorder.getInstance().toString());
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
