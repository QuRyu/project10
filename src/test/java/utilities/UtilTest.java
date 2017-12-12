package utilities;

import org.junit.Assert;
import org.junit.Test;

import static utilities.Util.convertToBinary;
import static utilities.Util.convertToHex;

public class UtilTest {

    @Test
    public void convertToBinary_test() {
        Assert.assertTrue(
                "convertToBinary function, given 0, does not produce 00000000" +
                "but " + convertToBinary(0,true),
                "00000000".equals(convertToBinary(0,true)));
        Assert.assertTrue("Test 2, the result should be 00001010 but is " + convertToBinary(10,true) ,"00001010".equals(convertToBinary(10,true)));
        Assert.assertTrue("Test 3, the result should be 11111000 but is " + convertToBinary(-8,true),"11111000".equals(convertToBinary(-8,true)));
        Assert.assertTrue("Test 4, the result should be 00000000 but is " + convertToBinary("0",true),"00000000".equals(convertToBinary("0",true)));
        Assert.assertTrue("Test 5, the result should be 00001010 but is " + convertToBinary("10",true) ,"00001010".equals(convertToBinary("10",true)));
        Assert.assertTrue("Test 6, the result should be 11111000 but is " + convertToBinary("-8",true),"11111000".equals(convertToBinary("-8",true)));
        Assert.assertTrue("Test 7, the result should be 10000000 but is " + convertToBinary(-128,true),"10000000".equals(convertToBinary(-128,true)));
        Assert.assertTrue("Test 8, the result should be 01111111 but is " + convertToBinary(127,true),"01111111".equals(convertToBinary(127,true)));
        try {
            Assert.assertTrue("Test 9, the result should be exception but is " + convertToBinary(-148,false),!"10000000".equals(convertToBinary(-148,false)));
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
        try {
            Assert.assertTrue("Test 10, the result should be exception but is " + convertToBinary(148,true),!"10000000".equals(convertToBinary(148,true)));
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
        Assert.assertTrue("Test 11, the result should be 7f but is " + convertToHex(127),"7f".equals(convertToHex(127)));
        Assert.assertTrue("Test 12, the result should be ff but is " + convertToHex(255),"ff".equals(convertToHex(255)));
        Assert.assertTrue("Test 13, the result should be 00 but is " + convertToHex(0),"00".equals(convertToHex(0)));
        Assert.assertTrue("Test 14, the result should be 7f but is " + convertToHex("127"),"7f".equals(convertToHex("127")));
        Assert.assertTrue("Test 15, the result should be ff but is " + convertToHex("255"),"ff".equals(convertToHex("255")));
        Assert.assertTrue("Test 16, the result should be 00 but is " + convertToHex("0"),"00".equals(convertToHex("0")));



    }
}
