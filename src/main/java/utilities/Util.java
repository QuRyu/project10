package utilities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.math.BigInteger;

/**
 * Created by Quentin on 12/6/17.
 * Edited by Lucia on 12/8/2017
 */
public class Util {

    // output 8 bit long binary number
    public static String convertToBinary(String n) {
        int num = Integer.parseInt(n);
        String v = convertToBinary(num);
        return v;
    }

    public static String convertToBinary(int n) {
        String v = "00000000";
        if (n > 0) {
            BigInteger num_big = new BigInteger(String.valueOf(n));
            int l = num_big.bitLength();
            v = v.substring(0,8-l) + Integer.toBinaryString(n);
        }else if(n < 0){
            int num1 = n + 128;
            BigInteger num_big = new BigInteger(String.valueOf(num1));
            int l = num_big.bitLength();
            v = v.substring(0,8-l) + Integer.toBinaryString(num1);

        }
        return v;
    }

    public static String convertToHex(String n) {
        throw new NotImplementedException();
    }

    public static String convertToHex(int n) {
        throw new NotImplementedException();
    }

}
