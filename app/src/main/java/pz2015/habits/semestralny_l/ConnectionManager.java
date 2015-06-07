package pz2015.habits.semestralny_l;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by ASUS on 2015-06-07.
 */
public class ConnectionManager extends AsyncTask<Void, Void, Void> {

    private final String TAG_LOGIN = "login";
    private final String TAG_REGISTER = "register";

    private Context context;
    private List<NameValuePair> list;
    private JSONObject json;
    private JSONParser jParser;
    private SessionManager sessionManager;
    private ProgressDialog progressDialog;


    public ConnectionManager(Context context, List<NameValuePair> list) {
        this.context = context;
        this.jParser = new JSONParser();
        this.json = null;
        this.sessionManager = new SessionManager(context);
        this.progressDialog = new ProgressDialog(context);
        this.list = list;
        this.progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog.setMessage("Connecting...");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {

        json = jParser.getJSONFromUrl(AppConfig.URL_API, list);

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        progressDialog.dismiss();

        boolean error = jParser.getError();

        // Check for error in json
        if (!error) {
            // user successfully logged in
            // Create login session
            sessionManager.setLogin(true);

            // Launch User Activity
            Intent intent = new Intent(context, UserActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        else {
            // error in login. Get the error message
            String errorMsg = jParser.getErrorMsg();
            Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
        }
    }

}
