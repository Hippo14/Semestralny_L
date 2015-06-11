package pz2015.habits.semestralny_l.Helpers;

/*
Class for Application settings.
 */
public class AppConfig {

    // Server user API URL
    public static final String URL_API = "http://krzysiek.carimex.pl/android.php";

    public static final String TAG_LOGIN = "login";
    public static final String TAG_REGISTER = "register";
    public static final String TAG_SYNCHRO = "synchro";

    /**
    * Created by ASUS on 2015-06-10.
    */
    public enum TypeOfGame {
        TestYourMight(0),
        CustomGame(1);

        private final int i;

        private TypeOfGame(int i) {
            this.i = i;
        }

        public int getI() { return this.i; }
    }

}
