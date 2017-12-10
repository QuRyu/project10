package instruction;

import coreInstruction.CoreInstruction;
import coreInstruction.CoreMove;
import instruction_type.Register;
import utilities.Validation;

import java.util.ArrayList;

public class Iport implements Instruction {
	private Register dst;


	public Iport(String dst) {
		Validation.validate_register_exception(dst);
		this.dst = Register.createRegister(dst);
	}

	public ArrayList<CoreInstruction> generate() {
	    ArrayList<CoreInstruction> result = new ArrayList<CoreInstruction>();
	    result.add(new CoreMove(new Register(Register.register_type.IPORT), dst));
        return result;
    }
}