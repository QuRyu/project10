package utilities;

import org.junit.Assert;
import org.junit.Test;

import static utilities.Validation.validate_register;
import static utilities.Validation.validate_instruction;
import static utilities.Validation.validate_memory_address;
//import static utilities.Validation.convertToBinary;



public class TestVailidation {
    /**
    @Test
    public void validation_test() {
        Assert.assertTrue("Test 1 " + validate_register("ra", "RB"), true == validate_register("RA", "RB"));
        Assert.assertTrue("Test 2 " + validate_register("RC", "RD"), true == validate_register("RC", "RD"));
        Assert.assertTrue("Test 3 " + validate_register("RE", "SP"), true == validate_register("RE", "SP"));
        Assert.assertTrue("Test 4 " + validate_instruction("oport 8 ra"), true == validate_instruction("oport 8 ra"));
        try {
            Assert.assertTrue("Test 5" , validate_memory_address("290"));
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);

        }
        Assert.assertTrue("Test 6" + validate_memory_address("20"), true == validate_memory_address("20"));

    }
    */
}
