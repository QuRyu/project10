package coreInstruction;

import instruction_type.*;
import subneg.Subneg;

import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 *
 * This instruction stores the value of a designated register into memory;
 * it strictly follows the counterpart in memory representation: only register is allowed as
 * the first argument, memory address (stack_ptr, RAM) is allowed as the second argument
 */
public class CoreStore extends CoreInstruction {
    private boolean isIndirect;

    public CoreStore(Register src, Memory addr) {
        this(src, addr, false);
    }
    
    public CoreStore(Register src, Memory addr, boolean isIndirect) {
        super(src, Addressing_mode.REGISTER, addr, Addressing_mode.MEMORY);
        this.isIndirect = isIndirect;
    }

    public ArrayList<Subneg> generate() {
        ArrayList<Subneg> result = new ArrayList<Subneg>();
        result.add(new Subneg(this.opB_mode, this.opB, this.opB_mode, this.opB)); // clear memory address
        result.add(new Subneg(this.opA_mode, this.opA, this.opB_mode, this.opB)); // move in
        return result;
    }

}
