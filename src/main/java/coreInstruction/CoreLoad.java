package coreInstruction;

import instruction_type.Addressing_mode;
import instruction_type.Register;
import subneg.Subneg;

import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 * Invariant, both dst and addr should be correct
 */
public class CoreLoad extends CoreInstruction {
    private String dst;
    private String addr;
    private Addressing_mode dst_mode;
    private Addressing_mode addr_mode;
    private boolean isImmediate;

    public CoreLoad(Addressing_mode dstMode, String dst, Addressing_mode addr_mode, String addr, boolean isImmediate) {
        this.dst = dst;
        this.dst_mode = dstMode;
        this.addr = addr;
        this.addr_mode = addr_mode;
        this.isImmediate = isImmediate;
    }

    public CoreLoad(Addressing_mode dst_mode, String dst, Addressing_mode addr_mode, String addr) {
        this(dst_mode, dst, addr_mode, addr, false);
    }

    public ArrayList<Subneg> generate() {
        ArrayList<Subneg> result = new ArrayList<Subneg>();
        result.add(new Subneg(dst_mode, dst, dst_mode, dst));
        if (isImmediate) {
            result.add(new Subneg(Addressing_mode.MEMORY, addr, dst_mode, dst));
        } else {
            result.add(new Subneg(Addressing_mode.REGISTER, Register.SPEC_RF.binary_representation(),
                    Addressing_mode.REGISTER, Register.SPEC_RF.binary_representation())); // clear RF
            result.add(new Subneg(Addressing_mode.REGISTER, Register.RE.binary_representation(),
                    Addressing_mode.REGISTER, Register.SPEC_RF.binary_representation())); // move RE to RF
            result.add(new Subneg(Addressing_mode.IM, "00000000",
                    Addressing_mode.REGISTER, Register.SPEC_RF.binary_representation())); // invert RF
            result.add(new Subneg(Addressing_mode.IM, addr,
                    Addressing_mode.REGISTER, Register.SPEC_RF.binary_representation())); // RF = RE + addr
            result.add(new Subneg(Addressing_mode.MEMORY_INDIRECT, Register.SPEC_RF.binary_representation(),
                    dst_mode, dst));
        }
        return result;
    }
}
