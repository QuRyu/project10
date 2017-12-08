package coreInstruction;

import instruction_type.Addressing_mode;
import instruction_type.Operand;
import subneg.Subneg;

import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 */
public class CoreSub extends CoreInstruction {

    public CoreSub(Operand src, Addressing_mode src_mode, Operand dst, Addressing_mode dst_mode) {
        super(src, src_mode, dst, dst_mode);
    }


    public ArrayList<Subneg> generate() {
        ArrayList<Subneg> result = new ArrayList<Subneg>();
        result.add(new Subneg(this.opA_mode, this.opA, this.opB_mode, opB));
        return result;
    }
}
