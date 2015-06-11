package pz2015.habits.semestralny_l.Helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import pz2015.habits.semestralny_l.Activity.UserActivity;

/*
Managment connection with PHP server.
 */
public class ConnectionManager extends AsyncTask<Void, Void, Void> {

    private Context context;
    private List<NameValuePair> list;
    private JSONObject json;
    private JSONParser jParser;
    private SessionManager sessionManager;
    private ProgressDialog progressDialog;
    private String successMsg = "Success!";


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
        ConnectionDetector cd = new ConnectionDetector(context);
        if (cd.isConnectingToInternet())
            json = jParser.getJSONFromUrl(AppConfig.URL_API, list);
        else
            jParser.setError("No internet connection!");

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        progressDialog.dismiss();
        progressDialog.cancel();

        boolean error = jParser.getError();
        String salt = " ";
        String name = " ";
        String tag = " ";

        // Check for error in json
        if (!error) {
            // user successfully logged in

            // check tag
            try {
                tag = json.getString("tag");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (!tag.toString().equals(AppConfig.TAG_SYNCHRO.toString())) {
                try {
                    salt = json.getString("salt");
                    name = json.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Create login session
                sessionManager.setLogin(true, salt, name);
                // Launch User Activity
                Intent intent = new Intent(context, UserActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
            else {
                // Toast success
                String errorMsg = jParser.getErrorMsg();
                Toast.makeText(context, successMsg, Toast.LENGTH_LONG).show();
            }
        }
        else {
            // error in login. Get the error message
            String errorMsg = jParser.getErrorMsg();
            Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
        }
    }

}
