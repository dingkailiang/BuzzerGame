package ca.dingkaiualberta.buzzergame.UI;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ca.dingkaiualberta.buzzergame.Module.MultiGame;
import ca.dingkaiualberta.buzzergame.R;

public class MultiGameActivity extends ActionBarActivity {
    private MultiGame game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_game);
        game = new MultiGame(
                (Button) this.findViewById(R.id.twoplayers),
                (Button) this.findViewById(R.id.threeplayers),
                (Button) this.findViewById(R.id.fourplayers),
                (Button) this.findViewById(R.id.player1),
                (Button) this.findViewById(R.id.player2),
                (Button) this.findViewById(R.id.player3),
                (Button) this.findViewById(R.id.player4),
                (Button) this.findViewById(R.id.bigPlayer1),
                (Button) this.findViewById(R.id.bigPlayer2),
                (Button) this.findViewById(R.id.restartButton),
                (TextView) this.findViewById(R.id.winner)
        );
    }

    public void onStartGame(View button){
        game.startGame(button);
    }

    public void onStopGame(View button){
        game.stopGame(button);
    }

}
