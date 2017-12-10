package instruction;

import coreInstruction.CoreInstruction;
import coreInstruction.CoreLoad;
import instruction_type.Register;
import instruction_type.Stack_ptr;

import java.util.ArrayList;

public class Return implements Instruction {

	public Return () {}

	public ArrayList<CoreInstruction> generate() {
	    ArrayList<CoreInstruction> result = new ArrayList<CoreInstruction>();
	    result.add(new CoreLoad(Stack_ptr.singleton.copy_decr(),
				new Register(Register.register_type.PC)));
	    return result;
    }
}