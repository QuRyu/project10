package instruction;

import coreInstruction.CoreInstruction;
import coreInstruction.CoreStore;
import instruction_type.Register;
import instruction_type.Stack_ptr;
import utilities.Validation;

import java.util.ArrayList;


public class Push implements Instruction {

    private Register src;

    public Push(String src) {
        Validation.validate_register_exception(src);
        this.src = Register.createRegister(src);
    }

	public ArrayList<CoreInstruction> generate() {
	    ArrayList<CoreInstruction> result = new ArrayList<CoreInstruction>();
	    result.add(new CoreStore(src, Stack_ptr.singleton.incr_copy()));
	    return result;
    }
}