package czm.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helpers {
    private Helpers() {
        throw new IllegalStateException("Utility class");
    }
    public static String getCurrentTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
        return currentDateTime.format(formatter);
    }
}
