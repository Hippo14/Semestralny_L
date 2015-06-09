package pz2015.habits.semestralny_l.Helpers;

/**
 * Created by ASUS on 2015-06-07.
 */
public class AppConfig {
    // Server user API URL
    public static String URL_API = "http://krzysiek.carimex.pl/android.php";

    public enum Levels {
        LEVEL_EASY("Easy", 4),
        LEVEL_MEDIUM("Medim", 5),
        LEVEL_HARD("Hard", 6);

        private final String level;
        private final int i;

        Levels(String level, int i) {
            this.level = level;
            this.i = i;
        }

        public String getLevel() { return this.level;}

        public int getI() { return this.i; }
    }

}
