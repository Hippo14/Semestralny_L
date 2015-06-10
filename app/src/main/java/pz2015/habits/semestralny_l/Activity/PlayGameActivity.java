package pz2015.habits.semestralny_l.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import pz2015.habits.semestralny_l.Helpers.AppConfig;
import pz2015.habits.semestralny_l.Helpers.SessionManager;
import pz2015.habits.semestralny_l.R;

public class PlayGameActivity extends MY_Activity {

    private Button btnTestYourMight;
    private Button btnCustomGame;

    private boolean visible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnTestYourMight = (Button) findViewById(R.id.btnTestYourMight);
        btnCustomGame = (Button) findViewById(R.id.btnCustom);

        btnTestYourMight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        btnCustomGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCustomGame();
            }
        });
    }

    private void startGame() {
        Intent intent = new Intent(PlayGameActivity.this, BoardActivity.class);
        sessionManager.setTypeOfGame(AppConfig.TypeOfGame.TestYourMight.getI());
        sessionManager.setLevel(3);
        startActivity(intent);
        finish();
    }

    private void startCustomGame() {
        LinearLayout customGameOptions = (LinearLayout) findViewById(R.id.customGameOptions);

        if (visible) {
            customGameOptions.setVisibility(View.INVISIBLE);
            visible = false;

        }
        else {
            customGameOptions.setVisibility(View.VISIBLE);
            visible = true;
        }


        Button btnCustomGamePLAY = (Button) findViewById(R.id.btnCustomPLAY);

        btnCustomGamePLAY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayGameActivity.this, BoardActivity.class);

                final EditText editBoardSize = (EditText) findViewById(R.id.boardSize);

                sessionManager.setTypeOfGame(AppConfig.TypeOfGame.CustomGame.getI());
                sessionManager.setLevel(Integer.parseInt(editBoardSize.getText().toString()) >= 3 ? Integer.parseInt(editBoardSize.getText().toString()) : 3);

                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PlayGameActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_play_game;
    }

    @Override
    protected Context getContext() { return this.getApplicationContext(); }

}
