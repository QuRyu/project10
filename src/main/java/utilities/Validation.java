package utilities;

import instruction.Instruction;
import instruction_type.Register;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Quentin on 12/6/17.
 * Edited by Lucia on 12/8/2017
 */
public class Validation {

    public static boolean validate_register(String opA, String opB) {
        if (!Register.register_lookup(opA) || !Register.register_lookup(opB))
            return false;
        return true;
    }

    public static boolean validate_instruction(String ins) {
        return Instruction.nstructions.contains(ins);
    }

    // addr [0..255]
    public static boolean validate_memory_address(String addr) {
        int addr1 = Integer.parseInt(addr);
        if (addr1 <= 255 || addr1 >= 0) {
            return true;
        }else {
            throw new NotImplementedException();
        }
    }

    // addr is between the range [0..255] and has already been verified
    private static String convertToBinary(String addr) {
        throw new NotImplementedException();
    }
}
