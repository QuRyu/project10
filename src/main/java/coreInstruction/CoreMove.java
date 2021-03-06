package coreInstruction;

import instruction_type.Addressing_mode;
import instruction_type.Immediate;
import instruction_type.Register;
import subneg.Subneg;

import java.util.ArrayList;

/**
 * Created by Quentin on 12/8/2017
 *
 * This instruction moves the content of one register/immediate value to another register;
 * since it uses RF as the intermediate register, any behaviour to change the move the content of RF is undefined
 */
public class CoreMove extends CoreInstruction {

    private boolean isImmediate;

    public CoreMove(Register src, Register dst) {
        super(src, Addressing_mode.REGISTER, dst, Addressing_mode.REGISTER);
    }

    public CoreMove(Immediate src, Register dst) {
        super(src, Addressing_mode.IM, dst, Addressing_mode.REGISTER);
        isImmediate = true;
    }

    public ArrayList<Subneg> generate() {
        ArrayList<Subneg> result = new ArrayList<Subneg>();
        if (isImmediate) {
            result.add(new Subneg(Addressing_mode.REGISTER, opB,
                    Addressing_mode.REGISTER, opB));
            result.add(new Subneg(Addressing_mode.IM, opA,
                    Addressing_mode.REGISTER, opB));
        }
        else{
            result.add(new Subneg(Addressing_mode.REGISTER, Register.RF_singleton,
                    Addressing_mode.REGISTER, Register.RF_singleton)); // clear RF
            result.add(new Subneg(Addressing_mode.REGISTER, this.opB,
                    Addressing_mode.REGISTER, this.opB)); // clear dst
            result.add(new Subneg(this.opA_mode, this.opA,
                    Addressing_mode.REGISTER, Register.RF_singleton)); // move src to RF
            result.add(new Subneg(Addressing_mode.REGISTER, Register.RF_singleton,
                    this.opB_mode, this.opB)); // move RF to dst
        }
        return result;
    }
}
