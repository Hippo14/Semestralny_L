package pz2015.habits.semestralny_l.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by ASUS on 2015-06-07.
 */
public class SessionManager {

    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;
    Context context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "JavaSemestralnyLogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_IS_SALT = "salt";
    private static final String KEY_IS_NAME = "name";
    private static final String KEY_IS_LEVEL_NAME = "level_name";
    private static final String KEY_IS_LEVEL_I = "level_size";
    private static final String KEY_IS_TIME = "time";

    public SessionManager(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        this.editor = sharedPreferences.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        this.editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public void setLogin(boolean isLoggedIn, String salt, String name) {
        this.editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        this.editor.putString(KEY_IS_SALT, salt);
        this.editor.putString(KEY_IS_NAME, name);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public boolean getIsLoggedIn() {
        return this.sharedPreferences.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getName() {
        return this.sharedPreferences.getString(KEY_IS_NAME, null);
    }

    public int getLevelI() { return this.sharedPreferences.getInt(KEY_IS_LEVEL_I, 4); }
    public String getLevelName() { return this.sharedPreferences.getString(KEY_IS_LEVEL_NAME, null); }

    public long getTime() { return this.sharedPreferences.getLong(KEY_IS_TIME, 0); }

    public void setLevel(AppConfig.Levels level) {
        this.editor.putInt(KEY_IS_LEVEL_I, level.getI());
        this.editor.putString(KEY_IS_LEVEL_NAME, level.getLevel());

        // commint changes
        editor.commit();

        Log.d(TAG, "Level session modified!");
    }

    public void setTime(long time) {
        this.editor.putLong(KEY_IS_TIME, time);

        // commint changes
        editor.commit();

        Log.d(TAG, "Time session modified!");
    }
}
