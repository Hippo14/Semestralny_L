package pz2015.habits.semestralny_l.Helpers;

/**
 * Created by ASUS on 2015-06-07.
 */
public class AppConfig {
    // Server user API URL
    public static String URL_API = "http://krzysiek.carimex.pl/android.php";

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
