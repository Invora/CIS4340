/* Rule 00. Input Validation and Data Sanitization (IDS) */
/* DS03-J. Do not log unsanitized user input */
public class R00_IDS03_J{

    public static void main(String[] args) {

        if (loginSuccessful) {
            logger.severe("User login succeeded for: " + sanitizeUser(username));
        } else {
            logger.severe("User login failed for: " + sanitizeUser(username));
        }

    }
public static String sanitizeUser(String username) {
        return Pattern.matches("[A-Za-z0-9_]+", username)
        ? username : "unauthorized user";
    }
}