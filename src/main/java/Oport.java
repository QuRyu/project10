import java.util.ArrayList;

public class Oport implements Instruction {
	private String src;


	public Iport(String src) throws Exception {
		this.src = src;
	}

	public ArrayList<CoreInstruction> generate() {
        return null;
    }
}