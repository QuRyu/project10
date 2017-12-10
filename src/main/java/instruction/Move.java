package instruction;

import coreInstruction.CoreInstruction;
import coreInstruction.CoreMove;
import instruction_type.Immediate;
import instruction_type.Register;
import utilities.Validation;

import java.util.ArrayList;

public class Move implements Instruction {
	private boolean isImmediate;
	private Register src_re;
	private Immediate src_im;
	private Register dst;

	public Move(String src, String dst) {
        Validation.validate_register_exception(src, dst);
        this.src_re = Register.createRegister(src);
        this.dst = Register.createRegister(dst);
        this.isImmediate = false;
    }

    public Move(String immediate, String dst, boolean isImmediate) {
	    Validation.validate_register_exception(dst);
	    Validation.validate_immediate_value(immediate);
	    this.src_im = new Immediate(Integer.parseInt(immediate));
	    this.dst = Register.createRegister(dst);
	    this.isImmediate = true;
    }

	public ArrayList<CoreInstruction> generate() {
	    ArrayList<CoreInstruction> result = new ArrayList<CoreInstruction>();

	    if (isImmediate) result.add(new CoreMove(src_im, dst));
        else result.add(new CoreMove(src_re, dst));

        return result;
    }

}