package ca.dingkaiualberta.buzzergame.Module;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

import ca.dingkaiualberta.buzzergame.R;

/**
 * Created by dingkai on 10/4/15.
 */
public class SingleGame implements Game{
    private TextView welcome;
    private TextView score;
    private TextView early;
    private ImageView starImage;
    private Button startButton;
    private Button restartButton;
    private Button stopButton;
    private double recTime;
    private double delaySec;
    private Handler timer;

    public SingleGame(TextView welcome, TextView score, TextView early, ImageView starImage, Button startButton, Button restartButton, Button stopButton) {
        this.welcome = welcome;
        this.score = score;
        this.early = early;
        this.starImage = starImage;
        this.startButton = startButton;
        this.restartButton = restartButton;
        this.stopButton = stopButton;
        this.recTime = 0;
        this.delaySec = 0;
        this.welcome.setVisibility(View.VISIBLE);
        this.startButton.setVisibility(View.VISIBLE);
        timer = new Handler();
    }

    public void startGame(View button){
        if (button == startButton) {
            welcome.setVisibility(View.GONE);
            startButton.setVisibility(View.GONE);
        }else {
            score.setVisibility(View.GONE);
            early.setVisibility(View.GONE);
            restartButton.setVisibility(View.GONE);
        }
        stopButton.setVisibility(View.VISIBLE);
        recTime = System.nanoTime();
        long delayTime = new Random().nextInt(1900) + 10;
        delaySec = delayTime/1000.0;
        timer.postDelayed(new Runnable() {
            @Override
            public void run() {
                starImage.setVisibility(View.VISIBLE);
            }
        }, delayTime);

    }

    public void stopGame(View button){
        stopButton.setVisibility(View.GONE);
        starImage.setVisibility(View.GONE);
        restartButton.setVisibility(View.VISIBLE);
        recTime = (System.nanoTime() - recTime)/ 1000000000.0;
        if (recTime < delaySec){
            timer.removeCallbacksAndMessages(null);
            early.setVisibility(View.VISIBLE);
        }else{
            score.setText(String.format("Reaction Time: %.2f seconds", recTime - delaySec));
            score.setVisibility(View.VISIBLE);
            Recorder.getInstance().add(recTime - delaySec);
            Recorder.getInstance().save(button.getContext());
        }
    }
}
