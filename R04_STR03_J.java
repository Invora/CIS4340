/*
Rule 04. Characters and Strings (STR)
STR03-J. Do not encode noncharacter data as a string
*/

import java.math.BigInteger;

public class R04_STR03_J {

    //Noncompliant Code Example
    public static void main(String[] args) {
        BigInteger x = new BigInteger("530500452766");
        byte[] byteArray = x.toByteArray();
        String s = new String(byteArray);
        byteArray = s.getBytes();
        x = new BigInteger(byteArray);
    }
}