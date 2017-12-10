package instruction;

import coreInstruction.CoreInstruction;
import coreInstruction.CoreLoad;
import instruction_type.Register;
import instruction_type.Stack_ptr;
import utilities.Validation;

import java.util.ArrayList;

public class Pop implements Instruction {
    private Register dst;

	public Pop (String dst) {
		Validation.validate_register_exception(dst);
		this.dst = Register.createRegister(dst);
	}

	public ArrayList<CoreInstruction> generate() {
	    ArrayList<CoreInstruction> result = new ArrayList<CoreInstruction>();
	    result.add(new CoreLoad(Stack_ptr.singleton.copy_decr(), dst));
	    return result;
    }
}