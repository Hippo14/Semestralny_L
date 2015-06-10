package pz2015.habits.semestralny_l.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import pz2015.habits.semestralny_l.Helpers.SessionManager;

/**
 * Created by ASUS on 2015-06-10.
 */
public abstract class MY_Activity extends Activity {

    protected SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.sessionManager = new SessionManager(getContext());
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(getLayoutResourceId());


    }

    protected abstract int getLayoutResourceId();

    protected abstract Context getContext();

}
