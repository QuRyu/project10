package instruction;

import coreInstruction.CoreInstruction;

import java.util.ArrayList;

public class Call implements Instruction {
	private String lab;

	public Call (String lab) throws Exception {
		this.lab = lab;
	}
	
	public ArrayList<CoreInstruction> generate() {
        return null;
    }
}