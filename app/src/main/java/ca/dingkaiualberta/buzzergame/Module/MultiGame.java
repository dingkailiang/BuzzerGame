package ca.dingkaiualberta.buzzergame.Module;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import ca.dingkaiualberta.buzzergame.R;

/**
 * Created by dingkai on 10/4/15.
 */
public class MultiGame implements Game{
    private Button restartButton;
    private TextView winner;
    private ArrayList<Button> modeList;
    private ArrayList<Button> playerList;
    private int playerNum = 0;

    public MultiGame(Button twoPlayers, Button threePlayers, Button fourPlayers, Button player1,
                     Button player2, Button player3, Button player4, Button bigPlayer1,
                     Button bigPlayer2, Button restartButton, TextView winner) {
        modeList = new ArrayList<>();
        playerList = new ArrayList<>();
        modeList.add(twoPlayers);
        modeList.add(threePlayers);
        modeList.add(fourPlayers);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);
        playerList.add(bigPlayer1);
        playerList.add(bigPlayer2);
        this.restartButton = restartButton;
        this.winner = winner;

        for (Button mode:modeList) {
            mode.setVisibility(View.VISIBLE);
        }
    }

    public void startGame(View button) {
        if (playerNum == 0){
            for (int i = 0; i < modeList.size(); ++i) {
                if (modeList.get(i) == button) playerNum = i + 2;
                modeList.get(i).setVisibility(View.GONE);
            }
        }
        restartButton.setVisibility(View.GONE);
        winner.setVisibility(View.GONE);
        switch (playerNum){
            case 2:
                playerList.get(4).setVisibility(View.VISIBLE);
                playerList.get(5).setVisibility(View.VISIBLE);
            case 3:
                playerList.get(1).setVisibility(View.VISIBLE);
                playerList.get(2).setVisibility(View.VISIBLE);
                playerList.get(4).setVisibility(View.VISIBLE);
            case 4:
                playerList.get(0).setVisibility(View.VISIBLE);
                playerList.get(1).setVisibility(View.VISIBLE);
                playerList.get(2).setVisibility(View.VISIBLE);
                playerList.get(3).setVisibility(View.VISIBLE);
        }
    }

    public void stopGame(View button){
        for (int i = 0; i < playerList.size(); ++i){
            playerList.get(i).setVisibility(View.GONE);
            if(playerList.get(i)==button){
                int index = i;
                if (i >= 4) index = index - 4;
                Recorder.getInstance().add(index,playerNum);
                Recorder.getInstance().save(button.getContext());
                restartButton.setVisibility(View.VISIBLE);
                winner.setVisibility(View.VISIBLE);
                winner.setText("Winner: "+playerList.get(i).getText());
            }
        }
    }

}