package coreInstruction;

import instruction_type.Addressing_mode;
import instruction_type.Operand;
import subneg.Subneg;

import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 */
public class CoreStore extends CoreInstruction {

    public CoreStore(Operand src, Addressing_mode src_mode, Operand addr, Addressing_mode addr_mode) {
        super(src, src_mode, addr, addr_mode);
    }

    public ArrayList<Subneg> generate() {
        ArrayList<Subneg> result = new ArrayList<Subneg>();
        result.add(new Subneg(this.opB_mode, this.opB, this.opB_mode, this.opB)); // clear memory address
        result.add(new Subneg(this.opA_mode, this.opA, this.opB_mode, this.opB));
        return result;
    }

}
