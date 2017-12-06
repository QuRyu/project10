import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 */
public class Add implements Instruction {
    private boolean isIndirect;
    private String opA;
    private String opB;
    private int n; // line number

    public Add(String opA, String opB, int n, boolean isIndirect) throws Exception {
        if (!Register.tableE.contains(opA) || !Register.tableB.contains(opB))
            throw new Exception("wrong operand " + (Register.tableE.contains(opA) ? opB : opA) + "at line " + n);
        this.opA = opA;
        this.opB = opB;
        this.n = n;
        isIndirect = isIndirect;
    }

    public Add(String opA, String opB, int n) throws Exception {
        this(opA, opB, n,false);
    }

    public ArrayList<CoreInstruction> generate() {
        return null;
    }
}
