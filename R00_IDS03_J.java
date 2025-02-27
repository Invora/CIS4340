/* Rule 00. Input Validation and Data Sanitization (IDS) */
/* DS03-J. Do not log unsanitized user input */
public class R00_IDS03_J{

    //Noncompliant Code Example
    public static void main(String[] args) {

        if (loginSuccessful) {
            logger.severe("User login succeeded for: " + username);
        } else {
            logger.severe("User login failed for: " + username);
        }

    }
}