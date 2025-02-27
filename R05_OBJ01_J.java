/*
Rule 05. Object Orientation (OBJ)
OBJ01-J. Limit accessibility of fields
*/

public class R05_OBJ01_J {

    public static void main(String[] args) {

    }

    //Noncompliant Code Example (Public Primitive Field)
    public class Widget {
        public int total; // Number of elements

        void add() {
            if (total < Integer.MAX_VALUE) {
                total++;
                // ...
            } else {
                throw new ArithmeticException("Overflow");
            }
        }

        void remove() {
            if (total > 0) {
                total--;
                // ...
            } else {
                throw new ArithmeticException("Overflow");
            }
        }
    }
}