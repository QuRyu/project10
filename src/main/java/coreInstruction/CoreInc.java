package coreInstruction;

import instruction_type.Addressing_mode;
import instruction_type.Immediate;
import instruction_type.Operand;
import instruction_type.Register;
import subneg.Subneg;

import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 *
 * add src and sub and put the result in dst;
 * this instruction allows different kinds of addressing modes and guarantee that those
 * values will be written back, except when Input is used as the second argument and
 * Output is used as the first argument (in both cases no value will be written back
 * or read in
 */
public class CoreInc extends CoreInstruction {

    public CoreInc(Operand src, Addressing_mode src_mode, Operand dst, Addressing_mode dst_mode) {
        super(src, src_mode, dst, dst_mode);
    }

    public ArrayList<Subneg> generate() {
        ArrayList<Subneg> result = new ArrayList<Subneg>();
        result.add(new Subneg(Addressing_mode.REGISTER, Register.RF_singleton,
                Addressing_mode.REGISTER, Register.RF_singleton)); // clear RF
        result.add(new Subneg(this.opA_mode, opA,
                Addressing_mode.REGISTER, Register.RF_singleton)); // move src to RF
        result.add(new Subneg(Addressing_mode.IM, Immediate.Zeros,
                Addressing_mode.REGISTER, Register.RF_singleton)); // invert RF
        result.add(new Subneg(Addressing_mode.REGISTER, Register.RF_singleton, this.opB_mode, opB)); // dst = RF + dst
        return result;
    }
}
