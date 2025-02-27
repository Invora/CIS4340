/*
Rule 08. Visibility and Atomicity (VNA)
VNA04-J. Ensure that calls to chained methods are atomic
 */

public class R08_VNA04_J {

    public static void main(String[] args) {

    }

    //Noncompliant Code Example
    final class USCurrency {
        // Change requested, denomination (optional fields)
        private int quarters = 0;
        private int dimes = 0;
        private int nickels = 0;
        private int pennies = 0;

        public USCurrency() {}

        // Setter methods
        public USCurrency setQuarters(int quantity) {
            quarters = quantity;
            return this;
        }
        public USCurrency setDimes(int quantity) {
            dimes = quantity;
            return this;
        }
        public USCurrency setNickels(int quantity) {
            nickels = quantity;
            return this;
        }
        public USCurrency setPennies(int quantity) {
            pennies = quantity;
            return this;
        }
    }

    // Client code:
    class ExampleClientCode {

        private final USCurrency currency = new USCurrency();
        // ...

        public ExampleClientCode() {

            Thread t1 = new Thread(new Runnable() {
                @Override public void run() {
                    currency.setQuarters(1).setDimes(1);
                }
            });
            t1.start();

            Thread t2 = new Thread(new Runnable() {
                @Override public void run() {
                    currency.setQuarters(2).setDimes(2);
                }
            });
            t2.start();

            //...
        }
    }
}