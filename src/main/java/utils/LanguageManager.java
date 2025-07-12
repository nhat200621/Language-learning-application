package utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
    private static String currentLanguage = "English";

    public static void setLanguage(String language) {
        currentLanguage = language;
    }

    public static String getCurrentLanguage() {
        return currentLanguage;
    }

    public static Locale getLocale() {
        switch (currentLanguage) {
            case "Tiếng Việt":
                return new Locale("vi", "VN");
            case "Español":
                return new Locale("es", "ES");
            default:
                return Locale.ENGLISH;
        }
    }

    public static ResourceBundle getBundle() {
        return null;
    }

    public static String get(String error) {
        return error;
    }
}


