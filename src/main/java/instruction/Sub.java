package instruction;

import coreInstruction.CoreInstruction;

import java.util.ArrayList;


public class Sub implements Instruction {
	private String opA;
	private String opB;
	private String opC;

	public Sub(String opA, String opB, String opC) throws Exception {
		this.opA = opA;
		this.opB = opB;
		this.opC = opC;
	}

	public ArrayList<CoreInstruction> generate() {
        return null;
    }

}