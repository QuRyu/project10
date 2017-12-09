package instruction;

import coreInstruction.CoreInstruction;
import coreInstruction.CoreMove;
import instruction_type.Immediate;
import instruction_type.Register;

import java.util.ArrayList;


public class Halt implements Instruction {

	public Halt() {}

	public ArrayList<CoreInstruction> generate() {
	    ArrayList<CoreInstruction> result = new ArrayList<CoreInstruction>();
		result.add(new CoreMove(new Immediate(-1), new Register(Register.register_type.SPEC_RG)));
        return result;
    }
}