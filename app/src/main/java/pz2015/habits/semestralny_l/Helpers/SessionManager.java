package pz2015.habits.semestralny_l.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/*
Session data manager.
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
    private static final String KEY_IS_LEVEL_I = "levelSize";
    private static final String KEY_IS_TIME = "time";
    private static final String KEY_IS_TYPE_OF_GAME = "typeOfGame";
    private static final String KEY_IS_MOVEMENTS = "numberOfMovements";

    private static final String KEY_IS_STATISTICS__MOVEMENTS = "arrayOfStatisticsMovements";
    private static final String KEY_IS_STATISTICS_TIME = "arrayOfStatisticsTime";
    private static final String KEY_IS_STATISTICS_LEVEL = "arrayOfStatisticsLevel";
    private static final String KEY_IS_STATISTICS_SIZE = "arrayOfStatisticsSize";

    public SessionManager(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        this.editor = sharedPreferences.edit();
    }


    public boolean getIsLoggedIn() {
        return this.sharedPreferences.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public int getTypeOfGame() { return this.sharedPreferences.getInt(KEY_IS_TYPE_OF_GAME, 4); }

    public int getLevelI() { return this.sharedPreferences.getInt(KEY_IS_LEVEL_I, 4); }

    public long getTime() { return this.sharedPreferences.getLong(KEY_IS_TIME, 0); }

    public String getName() {
        return this.sharedPreferences.getString(KEY_IS_NAME, null);
    }

    public int getMovements() { return this.sharedPreferences.getInt(KEY_IS_MOVEMENTS, 0); }

    public String getSalt() { return this.sharedPreferences.getString(KEY_IS_SALT, null); }

    public int getStatisticsSize() { return this.sharedPreferences.getInt(KEY_IS_STATISTICS_SIZE, 0); }
    public long getStatisticsTime(int i) { return this.sharedPreferences.getLong(KEY_IS_STATISTICS_TIME + "_" + i, 0); }
    public int getStatisticsLevel(int i) { return this.sharedPreferences.getInt(KEY_IS_STATISTICS_LEVEL + "_" + i, 0); }
    public int getStatisticsMovements(int i) { return this.sharedPreferences.getInt(KEY_IS_STATISTICS__MOVEMENTS + "_" + i, 0); }


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

    public void setTypeOfGame(int typeOfGame) {
        this.editor.putInt(KEY_IS_TYPE_OF_GAME, typeOfGame);

        // commint changes
        editor.commit();

        Log.d(TAG, "Type of Game session modified!");
    }

    public void setLevel(int level) {
        this.editor.putInt(KEY_IS_LEVEL_I, level);

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

    public void setStatistics(long time, int sizeBoard, int movements) {
        if (getStatisticsSize() == 0) {
            this.editor.putInt(KEY_IS_STATISTICS_SIZE, 1);
            this.editor.putLong(KEY_IS_STATISTICS_TIME + "_" + 0, time);
            this.editor.putInt(KEY_IS_STATISTICS_LEVEL + "_" + 0, sizeBoard);
            this.editor.putInt(KEY_IS_STATISTICS__MOVEMENTS + "_" + 0, movements);
        }
        else {
            this.editor.putInt(KEY_IS_STATISTICS_SIZE, getStatisticsSize() + 1);
            this.editor.putLong(KEY_IS_STATISTICS_TIME + "_" + getStatisticsSize(), time);
            this.editor.putInt(KEY_IS_STATISTICS_LEVEL + "_" + getStatisticsSize(), sizeBoard);
            this.editor.putInt(KEY_IS_STATISTICS__MOVEMENTS + "_" + getStatisticsSize(), movements);
        }

        // commint changes
        editor.commit();

        Log.d(TAG, "Statistics session modified!");
    }

    public void setMovements(int movements) {
        this.editor.putInt(KEY_IS_MOVEMENTS, movements);

        // commint changes
        editor.commit();

        Log.d(TAG, "Movements session modified!");
    }

}
