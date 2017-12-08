package instruction;

import coreInstruction.CoreInstruction;

import java.util.ArrayList;

public class Iport implements Instruction {
	private String dest;


	public Iport(String dest) throws Exception {
		this.dest = dest;
	}

	public ArrayList<CoreInstruction> generate() {
        return null;
    }
}