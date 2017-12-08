package coreInstruction;

import instruction_type.Addressing_mode;
import instruction_type.Operand;
import subneg.Subneg;

import java.util.ArrayList;

/**
 * Created by Quentin on 12/5/17.
 */
public abstract class CoreInstruction {
    protected Operand opA;
    protected Addressing_mode opA_mode;
    protected Operand opB;
    protected Addressing_mode opB_mode;

    public CoreInstruction(Operand opA, Addressing_mode opA_mode, Operand opB, Addressing_mode opB_mode) {
        this.opA = opA;
        this.opA_mode = opA_mode;
        this.opB = opB;
        this.opB_mode = opB_mode;
    }

    public abstract ArrayList<Subneg> generate();
}
