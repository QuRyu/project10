package instruction;

import coreInstruction.CoreInstruction;

import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 */
public class Load implements Instruction {
    private boolean isIndirect;
    private String dst;
    private String addr;

    public Load(String dst, String addr, boolean isIndirect) throws Exception {
        this.dst = dst;
        this.addr = addr;
        this.isIndirect = isIndirect;
    }

    public Load(String dst, String addr) throws Exception {
        this(dst, addr, false);
    }

    public ArrayList<CoreInstruction> generate() {
        return null;
    }
}
