package instruction;

import coreInstruction.CoreInstruction;
import coreInstruction.CoreStore;
import instruction_type.PC;
import instruction_type.Register;
import instruction_type.Stack_ptr;

import java.util.ArrayList;

public class Call implements Instruction {
    private PC calling_site;

    // PC should be created during the scanning phase
	public Call(PC calling_site) {
		this.calling_site = calling_site;
	}

	// save PC
	public ArrayList<CoreInstruction> generate() {
	    ArrayList<CoreInstruction> result = new ArrayList<CoreInstruction>();
	    result.add(new CoreStore(new Register(Register.register_type.SP),
				Stack_ptr.singleton.incr_copy()));
	    return result;
    }
}