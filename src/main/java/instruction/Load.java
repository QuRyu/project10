package instruction;

import coreInstruction.CoreInstruction;
import coreInstruction.CoreLoad;
import instruction_type.RAM;
import instruction_type.Register;
import utilities.Validation;

import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 */
public class Load implements Instruction {
    private boolean isIndirect;
    private Register dst;
    private RAM addr;

    public Load(String addr, String dst) {
        this(addr, dst, false);
    }

    public Load(String addr, String dst, boolean isIndirect) {
        Validation.validate_register_exception(dst);
        Validation.validate_memory_address(addr);
        this.dst = Register.createRegister(dst);
        this.addr = new RAM(Integer.parseInt(addr));
        this.isIndirect = isIndirect;
    }


    public ArrayList<CoreInstruction> generate() {
        ArrayList<CoreInstruction> result = new ArrayList<CoreInstruction>();
        result.add(new CoreLoad(addr, dst, isIndirect));
        return result;
    }
}
