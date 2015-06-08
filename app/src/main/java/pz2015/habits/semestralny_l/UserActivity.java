package pz2015.habits.semestralny_l;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;


public class UserActivity extends Activity {

    private TextView txtName;
    private SessionManager sessionManager;
    private Button btnLogout;
    private Button btnEasyGame;
    private Button btnMediumGame;
    private Button btnHardGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user);

        txtName = (TextView) findViewById(R.id.name);

        btnEasyGame = (Button) findViewById(R.id.btnEasyGame);
        btnMediumGame = (Button) findViewById(R.id.btnMediumGame);
        btnHardGame = (Button) findViewById(R.id.btnHardGame);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        sessionManager = new SessionManager(getApplicationContext());

        if (!sessionManager.getIsLoggedIn())
            logoutUser();

        String name = sessionManager.getName();

        txtName.setText(name);

        btnEasyGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(AppConfig.LEVEL_EASY);
            }
        });

        btnMediumGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(AppConfig.LEVEL_MEDIUM);
            }
        });

        btnHardGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(AppConfig.LEVEL_HARD);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    private void startGame(int level) {
        sessionManager.setLevel(level);
        Intent intent = new Intent(UserActivity.this, BoardActivity.class);
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
}
