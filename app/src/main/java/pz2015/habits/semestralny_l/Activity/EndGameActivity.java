package pz2015.habits.semestralny_l.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import pz2015.habits.semestralny_l.Helpers.AppConfig;
import pz2015.habits.semestralny_l.R;
import pz2015.habits.semestralny_l.Helpers.SessionManager;


public class EndGameActivity extends MY_Activity {

    private long time;
    private int sizeBoard;

    private TextView txtTime;
    private TextView txtBoardSize;
    private Button btnExit;
    private Button btnNextLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        time = sessionManager.getTime() / 1000;
        sizeBoard = sessionManager.getLevelI();

        txtTime = (TextView) findViewById(R.id.time);
        txtBoardSize = (TextView) findViewById(R.id.boardSizeText);
        btnNextLevel = (Button) findViewById(R.id.btnNextLevel);
        btnExit = (Button) findViewById(R.id.btnExit);


        txtTime.setText(Long.toString(time) + " sec");
        txtBoardSize.setText("On " + Integer.toString(sizeBoard) + "x" + Integer.toString(sizeBoard));

        if (sessionManager.getTypeOfGame() == AppConfig.TypeOfGame.TestYourMight.getI())
            btnNextLevel.setVisibility(View.VISIBLE);

        btnNextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndGameActivity.this, BoardActivity.class);

                sessionManager.setLevel(sessionManager.getLevelI() + 1);

                startActivity(intent);
                finish();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndGameActivity.this, UserActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_end_game, menu);
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
    protected void onPause() {
        super.onPause();

        if (isFinishing())
            sessionManager.setStatistics(time, sizeBoard);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EndGameActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_end_game;
    }

    @Override
    protected Context getContext() { return this.getApplicationContext(); }

}
