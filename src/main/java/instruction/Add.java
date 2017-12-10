package instruction;

import coreInstruction.CoreInc;
import coreInstruction.CoreInstruction;
import instruction_type.Addressing_mode;
import instruction_type.Register;
import utilities.Validation;

import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 */
public class Add implements Instruction {

    private Register src;
    private Register dst;

    public Add(String src, String dst) {
        Validation.validate_register_exception(src, dst);
        this.src = Register.createRegister(src);
        this.dst = Register.createRegister(dst);
    }

    public ArrayList<CoreInstruction> generate() {
        ArrayList<CoreInstruction> result = new ArrayList<CoreInstruction>();
        result.add(new CoreInc(src, Addressing_mode.REGISTER, dst, Addressing_mode.REGISTER));
        return result;
    }
}
