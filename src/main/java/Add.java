import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 */
public class Add implements Instruction {

    private String opA;
    private String opB;

    public Add(String opA, String opB) throws Exception {
        this.opA = opA;
        this.opB = opB;
    }

    public ArrayList<CoreInstruction> generate() {
        return null;
    }
}
