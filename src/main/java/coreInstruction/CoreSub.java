package coreInstruction;

import instruction_type.Addressing_mode;
import instruction_type.Operand;
import instruction_type.PCAddr;
import subneg.Subneg;

import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 */
public class CoreSub extends CoreInstruction {

    private boolean conditional; // if branch if the result is less than 0
    private PCAddr jumpToAddr;

    public CoreSub(Operand src, Addressing_mode src_mode, Operand dst, Addressing_mode dst_mode) {
        super(src, src_mode, dst, dst_mode);
        conditional = false;
    }

    public CoreSub(Operand src, Addressing_mode src_mode, Operand dst, Addressing_mode dst_mode, PCAddr jumpToAddr) {
        super(src, src_mode, dst, dst_mode);
        this.jumpToAddr = jumpToAddr;
        conditional = true;
    }

    public ArrayList<Subneg> generate() {
        ArrayList<Subneg> result = new ArrayList<Subneg>();

        if (conditional) result.add(new Subneg(this.opA_mode, this.opA, this.opB_mode, opB, jumpToAddr));
        else result.add(new Subneg(this.opA_mode, this.opA, this.opB_mode, opB));
        return result;
    }
}
