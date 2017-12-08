package instruction;

import coreInstruction.CoreInstruction;

import java.util.ArrayList;

public class Oport implements Instruction {
	private String src;


	public Oport(String src) throws Exception {
		this.src = src;
	}

	public ArrayList<CoreInstruction> generate() {
        return null;
    }
}