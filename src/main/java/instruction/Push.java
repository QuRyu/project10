package instruction;

import coreInstruction.CoreInstruction;

import java.util.ArrayList;


public class Push implements Instruction {
	private String src;


	public Push (String src) throws Exception {
		this.src = src;
	}

	public ArrayList<CoreInstruction> generate() {
        return null;
    }
}