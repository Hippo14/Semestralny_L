package pz2015.habits.semestralny_l;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;
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
    private static final String KEY_IS_LEVEL = "level";

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

    public int getLevel() { return this.sharedPreferences.getInt(KEY_IS_LEVEL, 4); }

    public void setLevel(int level) {
        this.editor.putInt(KEY_IS_LEVEL, level);

        // commint changes
        editor.commit();

        Log.d(TAG, "Level session modified!");
    }
}
