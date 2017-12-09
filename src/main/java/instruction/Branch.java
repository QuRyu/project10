package instruction;

import coreInstruction.CoreGoto;
import coreInstruction.CoreInstruction;
import coreInstruction.CoreSub;
import instruction_type.Addressing_mode;
import instruction_type.Immediate;
import instruction_type.Operand;
import instruction_type.PC;

import java.util.ArrayList;

public class Branch implements Instruction {
    private PC jumpToAddr;
    private boolean conditional;
    private Operand src;
    private Addressing_mode src_mode;

    // PC should be created during scanning phase of the code
    public Branch(PC jumpToAddr) {
        this.jumpToAddr = jumpToAddr;
        conditional = false;
    }

    // test if a the designated address is less than zero
	public Branch(PC jumpToAddr, Operand src, Addressing_mode src_mode) {
		this.jumpToAddr = jumpToAddr;
		this.src = src;
		this.src_mode = src_mode;
		conditional = true;
	}


	public ArrayList<CoreInstruction> generate() {
        ArrayList<CoreInstruction> result = new ArrayList<CoreInstruction>();

        if (conditional)
            result.add(new CoreSub(src, src_mode, Immediate.Zeros, Addressing_mode.IM, jumpToAddr));
        else
            result.add(new CoreGoto(jumpToAddr));

        return result;
    }


}