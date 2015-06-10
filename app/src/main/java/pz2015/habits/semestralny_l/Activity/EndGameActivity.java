package pz2015.habits.semestralny_l.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import pz2015.habits.semestralny_l.R;
import pz2015.habits.semestralny_l.Helpers.SessionManager;


public class EndGameActivity extends MY_Activity {

    private long time;
    private TextView txtTime;
    private TextView txtDifficult;
    private Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager = new SessionManager(getApplicationContext());
        time = sessionManager.getTime() / 1000;

        txtTime = (TextView) findViewById(R.id.time);
        txtDifficult = (TextView) findViewById(R.id.difficult);
        btnExit = (Button) findViewById(R.id.btnExit);

        txtTime.setText(Long.toString(time) + " sec");
        //txtDifficult.setText("On level " + difficult);

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
    protected int getLayoutResourceId() {
        return R.layout.activity_end_game;
    }

}
