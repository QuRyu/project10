import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 */
public class Load implements Instruction {
    private boolean isIndirect;
    private String dst;
    private String addr;

    public Load(String dst, String addr, int n, boolean isIndirect) throws Exception {
        if (!Register.tableE.contains(dst) || !Register.tableB.contains(addr))
            throw new Exception("wrong operand " + (Register.tableE.contains(dst) ? addr : dst) + "at line " + n);
        this.dst = dst;
        this.addr = addr;
        this.isIndirect = isIndirect;
    }

    public Load(String dst, String addr, int n) throws Exception {
        this(dst, addr, n,false);
    }

    public ArrayList<CoreInstruction> generate() {
        return null;
    }
}
