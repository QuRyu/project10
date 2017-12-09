package utilities;

import instruction.Instruction;
import instruction_type.Register;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Arrays;
import java.math.BigInteger;
import java.util.ArrayList;
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

        int indx = 0;
        boolean found = false;
        while(found == false){
            if (ins.substring(indx,indx+1).equals(" ")){
                found = true;

            }
            indx = indx +1;
        }

        return Instruction.nstructions.contains(ins.substring(0,indx-1));

    }

    // addr [0..255]
    public static boolean validate_memory_address(String addr) {
        int addr1 = Integer.parseInt(addr);
        if (addr1 <= 255 && addr1 >= 0) {
            return true;
        }else {
            throw new IllegalArgumentException("argument our of range, should be between 0 and 255");
        }
    }



}
