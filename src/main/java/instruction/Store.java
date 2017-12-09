package instruction;

import coreInstruction.CoreInstruction;
import coreInstruction.CoreStore;
import instruction_type.RAM;
import instruction_type.Register;
import utilities.Validation;

import java.util.ArrayList;

public class Store implements Instruction {
	private boolean isIndirect;
	private Register src;
	private RAM dst;

	public Store(String src, String dst) {
		this(src, dst, false);
	}

	public Store(String src, String dst, boolean isIndirect) {
		Validation.validate_register_exception(src);
		Validation.validate_memory_address(dst);
		this.src = Register.createRegister(src);
		this.dst = new RAM(Integer.parseInt(dst));
		this.isIndirect = isIndirect;
	}


	public ArrayList<CoreInstruction> generate() {
	    ArrayList<CoreInstruction> result = new ArrayList<CoreInstruction>();
	    result.add(new CoreStore(src, dst, isIndirect));
	    return result;
    }

}