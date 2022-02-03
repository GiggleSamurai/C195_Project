package Utility;

import java.util.Locale;
import java.util.ResourceBundle;

public class UserLanguage {
    //Locale userLocale = new Locale("fr","FR");
    //Locale enLocale = Locale.ENGLISH;
    public static ResourceBundle rb = ResourceBundle.getBundle("Utility/Nat", Locale.ENGLISH);

    public static void setLanguage(Locale theLocale) {
        rb = ResourceBundle.getBundle("Utility/Nat", theLocale);

    }
    //Locale enLocale = Locale.ENGLISH;

}
