/**
 * @class UserLanguage.java
 * @author Louis Wong
 */

package Utility;

import java.util.Locale;
import java.util.ResourceBundle;

public class UserLanguage {

    public static ResourceBundle rb = ResourceBundle.getBundle("Utility/Nat", Locale.ENGLISH);

    /**
     *
     * @param theLocale user location to set the language to
     */
    public static void setLanguage(Locale theLocale) {
        rb = ResourceBundle.getBundle("Utility/Nat", theLocale);
    }


}
