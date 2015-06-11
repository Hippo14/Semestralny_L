package pz2015.habits.semestralny_l.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pz2015.habits.semestralny_l.R;


/*
Main screen activity for logged user.
 */
public class UserActivity extends MY_Activity {

    private TextView txtName;
    private Button btnLogout;
    private Button btnPlayGame;
    private Button btnStatistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set UI
        txtName = (TextView) findViewById(R.id.name);

        btnPlayGame = (Button) findViewById(R.id.btnPlayGame);
        btnStatistics = (Button) findViewById(R.id.btnStatistics);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        // If user isnt logged (just in case..)
        if (!sessionManager.getIsLoggedIn())
            logoutUser();

        String name = sessionManager.getName();
        txtName.setText(name);

        btnPlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        btnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statistics();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    private void statistics() {
        Intent intent = new Intent(UserActivity.this, StatisticsActivity.class);
        startActivity(intent);
        finish();
    }

    private void startGame() {
        Intent intent = new Intent(UserActivity.this, PlayGameActivity.class);
        startActivity(intent);
        finish();
    }

    private void logoutUser() {
        sessionManager.setLogin(false);

        Intent intent = new Intent(UserActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user, menu);
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
    protected int getLayoutResourceId() {
        return R.layout.activity_user;
    }

    @Override
    protected Context getContext() { return this.getApplicationContext(); }

}
