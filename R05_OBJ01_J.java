/*
Rule 05. Object Orientation (OBJ)
OBJ01-J. Limit accessibility of fields
*/

public class R05_OBJ01_J {

    public static void main(String[] args) {

    }

    //Compliant Solution (Private Primitive Field)
    public class Widget {
        private int total; // Declared private

        public int getTotal () {
            return total;
        }

        // Definitions for add() and remove() remain the same
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