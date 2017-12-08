import java.util.ArrayList;


public class Push implements Instruction {
	private src;


	public Push (String src) throws Exception {
		this.src = src;
	}

	public ArrayList<CoreInstruction> generate() {
        return null;
    }
}