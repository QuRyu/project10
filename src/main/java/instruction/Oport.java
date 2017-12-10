package instruction;

import coreInstruction.CoreInstruction;
import coreInstruction.CoreMove;
import instruction_type.Register;
import utilities.Validation;

import java.util.ArrayList;

public class Oport implements Instruction {
    private Register src;

    public Oport(String src) {
        Validation.validate_register_exception(src);
        this.src = Register.createRegister(src);
    }

    public ArrayList<CoreInstruction> generate() {
	    ArrayList<CoreInstruction> result = new ArrayList<CoreInstruction>();
	    result.add(new CoreMove(src, new Register(Register.register_type.OPORT)));
	    return result;
    }
}