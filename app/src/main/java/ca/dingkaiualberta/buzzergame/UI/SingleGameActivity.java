package ca.dingkaiualberta.buzzergame.UI;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import ca.dingkaiualberta.buzzergame.Module.SingleGame;
import ca.dingkaiualberta.buzzergame.R;

public class SingleGameActivity extends Activity {
    private SingleGame game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game);
        this.game = new SingleGame(
                (TextView) this.findViewById(R.id.welcome),
                (TextView) this.findViewById(R.id.score),
                (TextView) this.findViewById(R.id.early),
                (ImageView) this.findViewById(R.id.starimage),
                (Button) this.findViewById(R.id.start),
                (Button) this.findViewById(R.id.restart),
                (Button) this.findViewById(R.id.stop)
                );
    }

    public void onStartGame(View button){
        game.startGame();
    }

    public void onStopGame(View button){
        game.stopGame();
    }
}
