import java.util.ArrayList;

public class Pop implements Instruction {
	private String dest;

	public Pop (String dest) throws Exception {
		this.dest = dest;
	}

	public ArrayList<CoreInstruction> generate() {
        return null;
    }
}