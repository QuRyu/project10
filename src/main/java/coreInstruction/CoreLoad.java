package coreInstruction;

import instruction_type.*;
import subneg.Subneg;

import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 *
 * Read value from memory;
 * this instruction strictly follows its counterpart in project8,
 * it only takes memory address from the first register and take register as
 * the second argument
 */
public class CoreLoad extends CoreInstruction {
    private boolean isImmediate; // TODO: 12/9/17 make sure it is immediate or indirect
    private boolean isReturn;

    public CoreLoad(Memory addr, Register dst) {
        this(addr, dst, false);
    }

    public CoreLoad(Memory addr, Register dst, boolean isImmediate) {
        super(addr, Addressing_mode.MEMORY, dst, Addressing_mode.REGISTER);
        this.isImmediate = isImmediate;
    }

    // because Return instruction requires clearing PC address and then load memory address
    // into PC, it is necessary that the second instruction follow the meaning of the first one
    public CoreLoad(Memory addr, Register dst, boolean isImmediate, boolean isReturn) {
        super(addr, Addressing_mode.MEMORY, dst, Addressing_mode.REGISTER);
        this.isReturn = true;
    }

    public ArrayList<Subneg> generate() {
        ArrayList<Subneg> result = new ArrayList<Subneg>();
        result.add(new Subneg(this.opB_mode, this.opB, this.opB_mode, this.opB));
        if (isImmediate) {
            result.add(new Subneg(Addressing_mode.MEMORY, this.opA, this.opB_mode, this.opB));
        }
        if (isReturn) {
            result.add(new Subneg(Addressing_mode.REGISTER, this.opB, Addressing_mode.REGISTER, this.opB));
            result.add(new Subneg(Addressing_mode.MEMORY, this.opA, Addressing_mode.REGISTER, this.opB, 1));
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
