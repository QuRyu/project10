package instruction;

import coreInstruction.CoreGoto;
import coreInstruction.CoreInstruction;
import coreInstruction.CoreSub;
import instruction_type.*;
import utilities.Validation;

import java.util.ArrayList;

public class Branch implements Instruction {
    private PC jumpToAddr;
    private boolean conditional;
    private Register condRegis;

    // PC should be created during scanning phase of the code
    public Branch(PC jumpToAddr) {
        this.jumpToAddr = jumpToAddr;
        conditional = false;
    }

    // test if a the designated address is less than zero
	public Branch(PC jumpToAddr, String register) {
        Validation.validate_register_exception(register);
        this.condRegis = Register.createRegister(register);
		this.jumpToAddr = jumpToAddr;
		conditional = true;
	}


	public ArrayList<CoreInstruction> generate() {
        ArrayList<CoreInstruction> result = new ArrayList<CoreInstruction>();

        if (conditional)
            result.add(new CoreSub(condRegis, Addressing_mode.REGISTER, Immediate.Zeros, Addressing_mode.IM, jumpToAddr));
        else
            result.add(new CoreGoto(jumpToAddr));

        return result;
    }


}