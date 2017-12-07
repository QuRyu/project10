import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Quentin on 12/6/17.
 */
public class Validation {

    public static boolean validate_register(String opA, String opB) {
        if (!Register.registers.contains(opA) || !Register.registers.contains(opB))
            return false;
        return true;
    }

    public static boolean validate_instruction(String ins) {
        return Instruction.nstructions.contains(ins);
    }

    public static boolean validate_memory_address(String addr) {
        throw new NotImplementedException();
    }

    // addr is between the range [0..255] and has already been verified
    private static String convertToBinary(String addr) {
        throw new NotImplementedException();
    }
}
