package pz2015.habits.semestralny_l.Activity;

import android.app.Activity;
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


public class RegisterActivity extends Activity {

    private static final String TAG_REGISTER = "register";
    private EditText editFullName;
    private EditText editEmail;
    private EditText editPassword;
    private Button btnRegister;
    private Button btnLogin;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        
        editFullName = (EditText) findViewById(R.id.name);
        editEmail = (EditText) findViewById(R.id.email);
        editPassword = (EditText) findViewById(R.id.password);
        
        btnRegister = (Button) findViewById(R.id.btnMakeMe);
        btnLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);

        sessionManager = new SessionManager(getApplicationContext());

        // Check if logged
        if (sessionManager.getIsLoggedIn()) {
            Intent intent = new Intent(RegisterActivity.this, UserActivity.class);
            startActivity(intent);
            finish();
        }

        // btnLogin event click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // btnRegister event click
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editFullName.getText().toString();
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    registerUser(name, email, password);
                }
                else
                    // Empty data
                    Toast.makeText(getApplicationContext(), "Please enter data!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void registerUser(String name, String email, String password) {
        // Build list params
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("tag", TAG_REGISTER));
        list.add(new BasicNameValuePair("name", name));
        list.add(new BasicNameValuePair("email", email));
        list.add(new BasicNameValuePair("password", password));

        ConnectionManager connectionManager = new ConnectionManager(this, list);
        connectionManager.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_register, menu);
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
