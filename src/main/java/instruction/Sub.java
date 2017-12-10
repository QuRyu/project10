package instruction;

import coreInstruction.CoreInstruction;
import coreInstruction.CoreSub;
import instruction_type.Addressing_mode;
import instruction_type.Register;
import utilities.Validation;

import java.util.ArrayList;


public class Sub implements Instruction {
	private Register src;
	private Register dst;

	public Sub(String src, String dst) throws Exception {
		Validation.validate_register_exception(src, dst);
		this.src = Register.createRegister(src);
		this.dst = Register.createRegister(dst);
	}

	public ArrayList<CoreInstruction> generate() {
	    ArrayList<CoreInstruction> result = new ArrayList<CoreInstruction>();
	    result.add(new CoreSub(src, Addressing_mode.REGISTER, dst, Addressing_mode.REGISTER));
	    return result;
    }

}