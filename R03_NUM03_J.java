/*
Rule 03. Numeric Types and Operations (NUM)
NUM03-J. Use integer types that can fully represent the possible range of unsigned data
*/

import java.io.DataInputStream;
import java.io.IOException;

public class R03_NUM03_J {
    public static void main(String[] args) {

    }

    public static long getInteger(DataInputStream is) throws IOException {
        return is.readInt() & 0xFFFFFFFL; //Mask with 32 one-bits
    }
}