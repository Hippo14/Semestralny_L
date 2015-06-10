package pz2015.habits.semestralny_l.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import pz2015.habits.semestralny_l.Helpers.ConnectionManager;
import pz2015.habits.semestralny_l.R;
import pz2015.habits.semestralny_l.Helpers.SessionManager;


public class MainActivity extends MY_Activity {

    private Button btnLogin;
    private Button btnRegister;
    private EditText editEmail;
    private EditText editPassword;

    private static final String TAG_LOGIN = "login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        editEmail = (EditText) findViewById(R.id.email);
        editPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        // SessionManager
        sessionManager = new SessionManager(getApplicationContext());

        // User is already logged?
        if (sessionManager.getIsLoggedIn()) {
            // Logged
            Intent intent = new Intent(MainActivity.this, UserActivity.class);
            startActivity(intent);
            finish();
        }

        // btnLogin event click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                // Check for empty data
                if (email.length() > 0 && password.length() > 0)
                    checkLogin(email, password);
                else
                    // Empty data
                    Toast.makeText(getApplicationContext(), "Please enter data!", Toast.LENGTH_LONG).show();
            }
        });

        // btnRegister event click
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void checkLogin(String email, String password) {
        // Build list params
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("tag", TAG_LOGIN));
        list.add(new BasicNameValuePair("email", email));
        list.add(new BasicNameValuePair("password", password));

        ConnectionManager connectionManager = new ConnectionManager(this, list);
        connectionManager.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // I wont this! :<
        //getMenuInflater().inflate(R.menu.menu_main, menu);
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
        return R.layout.activity_main;
    }

    @Override
    protected Context getContext() { return this.getApplicationContext(); }

}
