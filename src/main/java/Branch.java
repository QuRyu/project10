import java.util.ArrayList;

public class Branch implements Instruction {
	private String lab;
	private boolean conditional;

	public Branch (String lab, boolean conditional) throws Exception {
		this.lab = lab;
		this.conditional = conditional;
	}

	public Branch (String lab) throws Exception {
		this(lab, false);
	}

	public ArrayList<CoreInstruction> generate() {
        return null;
    }


}