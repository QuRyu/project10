package coreInstruction;

import instruction_type.*;
import subneg.Subneg;

import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 */
public class CoreGoto extends CoreInstruction {
    private PCAddr jump;

    public CoreGoto(PCAddr jump) {
        super(jump, Addressing_mode.MEMORY, jump, Addressing_mode.MEMORY);
    }

    public ArrayList<Subneg> generate() {
        ArrayList<Subneg> result = new ArrayList<Subneg>();
        result.add(new Subneg(Addressing_mode.IM, Immediate.Minus_one, Addressing_mode.IM, Immediate.Zeros, this.opB));
        return result;
    }
}
