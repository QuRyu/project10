import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 */
public class Add implements Instruction {
    private boolean isIndirect;
    private String opA;
    private String opB;

    public Add(String opA, String opB, boolean isIndirect) throws Exception {
        this.opA = opA;
        this.opB = opB;
        isIndirect = isIndirect;
    }

    public Add(String opA, String opB) throws Exception {
        this(opA, opB, false);
    }

    public ArrayList<CoreInstruction> generate() {
        return null;
    }
}
