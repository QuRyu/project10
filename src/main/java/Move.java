import java.util.ArrayList;

public class Move implements Instruction {
	private boolean isImmediate;
	private String src;
	private String dest;

	public Move(String src, String dest, boolean isImmediate) throws Exception {
		this.src = src;
		this.dest = dest;
		this.isImmediate = isImmediate;
	} 

	public Move(String src, String dest) throws Exception {
		this(src,dest,false);
	}


	public ArrayList<CoreInstruction> generate() {
        return null;
    }

}