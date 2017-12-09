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
        if (Integer.parseInt(n) >= -128 && Integer.parseInt(n) <= 127) {
            int num = Integer.parseInt(n);
            String v = convertToBinary(num);
            return v;
        }else{
            throw new IllegalArgumentException("argument our of range, should be between -128 and 127");
        }
    }

    public static String convertToBinary(int n) {
        if (n >= -128 && n <= 127) {
            String v = "00000000";
            if (n > 0) {

                BigInteger num_big = new BigInteger(String.valueOf(n));
                int l = num_big.bitLength();
                if (n != 0) {
                    v = v.substring(0, 8 - l) + Integer.toBinaryString(n);
                }else{
                    v = v.substring(0, 8 - l);

                }
            } else if (n < 0) {
                v = "10000000";
                int num1 = n + 128;
                BigInteger num_big = new BigInteger(String.valueOf(num1));
                int l = num_big.bitLength();
                if(num1 != 0) {
                    v = v.substring(0, 8 - l) + Integer.toBinaryString(num1);
                }else{
                    v = v.substring(0, 8 - l);

                }
            }
            return v;
        }else{

            throw new IllegalArgumentException("argument our of range, should be between -128 and 127");
        }
    }
//unsigned
    public static String convertToHex(String n) {
        if (Integer.parseInt(n) >= 0 && Integer.parseInt(n) <= 255) {
            int num = Integer.parseInt(n);

            String hex = Integer.toHexString(num);
            if (hex.equals("0")){
                hex = hex + "0";
            }
            return hex;

        }else{
            throw new IllegalArgumentException("argument our of range, should be between 0 and 255");

        }


    }

    public static String convertToHex(int n) {

        if (n >= 0 && n <= 255) {
            String num = String.valueOf(n);
            String hex = convertToHex(num);

            return hex;
        }else{
            throw new IllegalArgumentException("argument our of range, should be between 0 and 255");

        }
    }

}
