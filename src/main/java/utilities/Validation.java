package utilities;

import instruction.Instruction;
import instruction_type.Register;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Quentin on 12/6/17.
 * Edited by Lucia on 12/8/2017
 */
public class Validation {

    public static boolean validate_register(String regis) {
        return Register.register_lookup(regis);
    }

    public static boolean validate_register(String opA, String opB) {
        return Register.register_lookup(opA) && Register.register_lookup(opB);
    }

    // check if the register is valid; throw exception if not
    public static void validate_register_exception(String regis) {
        if (!validate_register(regis))
            throw new IllegalArgumentException("Register " + regis + " is not valid");
    }

    public static void validate_register_exception(String opA, String opB) {
        if (!validate_register(opA, opB))
            throw new IllegalArgumentException("Register " + (validate_register(opA) ? opB : opA) + " is not valid");
    }

    public static boolean validate_instruction(String ins) {
        return Instruction.nstructions.contains(ins);
    }

    // addr [0..255]
    public static boolean validate_memory_address(String addr) {
        int addr1 = Integer.parseInt(addr);
        if (addr1 <= 255 && addr1 >= 0) return true;
        else throw new IllegalArgumentException("memory address " + addr + " is out of boundary [0..255]");

    }

    // addr is between the range [0..255] and has already been verified
    private static String convertToBinary(String addr) {
        throw new NotImplementedException();
    }
}
