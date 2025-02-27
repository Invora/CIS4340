import java.io.File;
/*
Rule 02. Expressions (EXP)
XP00-J. Do not ignore values returned by methods
*/
public class R02_XP00_J{
    public static void main(String[] args) {

    }
    //Compliant Solution (File Deletion)
    public void deleteFile(){

        File someFile = new File("someFileName.txt");
        // Do something with someFile
        if (!someFile.delete()) {
            // Handle failure to delete the file
        }
    }
}