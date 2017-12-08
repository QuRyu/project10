package instruction;

import coreInstruction.CoreInstruction;

import java.util.ArrayList;

public class Store implements Instruction {
	private boolean isIndirect;
	private String src;
	private String addr;

	public Store (String src, String addr, boolean isIndirect) throws Exception {
		this.src = src;
		this.addr = addr;
		this.isIndirect = isIndirect;
	}

	public Store (String src, String addr) throws Exception {
		this(src, addr, false);
	}

	public ArrayList<CoreInstruction> generate() {
        return null;
    }

}