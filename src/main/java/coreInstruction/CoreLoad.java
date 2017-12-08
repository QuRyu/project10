package coreInstruction;

import instruction_type.*;
import subneg.Subneg;

import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 * Invariant, both dst and addr should be correct
 */
public class CoreLoad extends CoreInstruction {
    private boolean isImmediate;

    public CoreLoad(MemoryAddr addr, Addressing_mode addr_mode, Register dst, Addressing_mode dst_mode) {
        this(addr, addr_mode, dst, dst_mode, false);
    }

    public CoreLoad(MemoryAddr addr, Addressing_mode addr_mode, Register dst, Addressing_mode dst_mode, boolean isImmediate) {
        super(addr, addr_mode, dst, dst_mode);
        this.isImmediate = isImmediate;
    }

    public ArrayList<Subneg> generate() {
        ArrayList<Subneg> result = new ArrayList<Subneg>();
        result.add(new Subneg(this.opB_mode, this.opB, this.opB_mode, this.opB));
        if (isImmediate) {
            result.add(new Subneg(Addressing_mode.MEMORY, this.opA, this.opB_mode, this.opB));
        } else {
            result.add(new Subneg(Addressing_mode.REGISTER, Register.RF_singleton,
                    Addressing_mode.REGISTER, Register.RF_singleton)); // clear RF
            result.add(new Subneg(Addressing_mode.REGISTER, Register.RE_singleton,
                    Addressing_mode.REGISTER, Register.RF_singleton)); // move RE to RF
            result.add(new Subneg(Addressing_mode.IM, Immediate.Zeros,
                    Addressing_mode.REGISTER, Register.RF_singleton)); // invert RF
            result.add(new Subneg(Addressing_mode.IM, this.opA,
                    Addressing_mode.REGISTER, Register.RF_singleton)); // RF = RE + addr
            result.add(new Subneg(Addressing_mode.MEMORY_INDIRECT, Register.RF_singleton,
                    this.opB_mode, this.opB));
        }
        return result;
    }
}
