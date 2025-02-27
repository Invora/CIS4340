/*
Rule 01. Declarations and Initialization (DCL)
DCL01-J. Do not reuse public identifiers from the Java Standard Library
*/

public class R01_DCL01_J {

    //Compliant Solution (Class Name)
     class MyVector {
        private int val = 1;

        public boolean isEmpty() {
            if (val == 1) {   // Compares with 1 instead of 0
                return true;
            } else {
                return false;
            }
        }
        // Other functionality is same as java.util.Vector
    }

    // import java.util.Vector; omitted
    public class VectorUser {
        public static void main(String[] args) {
            MyVector v = new MyVector();
            if (v.isEmpty()) {
                System.out.println("Vector is empty");
            }
        }
    }
}