package coreInstruction;

import instruction_type.Addressing_mode;
import instruction_type.Immediate;
import instruction_type.RAM;
import instruction_type.Register;
import org.junit.Assert;
import org.junit.Test;
import subneg.Subneg;

import java.util.ArrayList;

public class CoreLoadTest {
    RAM memaddr = new RAM(220);
    Register RA = new Register(Register.register_type.RA);
    CoreLoad load = new CoreLoad(memaddr,  RA,true);

    @Test
    public void generate_test() throws Exception {
        ArrayList<Subneg> expected1 = new ArrayList<Subneg>();

        expected1.add(new Subneg(Addressing_mode.REGISTER, Register.RF_singleton,
                Addressing_mode.REGISTER, Register.RF_singleton)); // clear RF
        expected1.add(new Subneg(Addressing_mode.REGISTER, Register.RE_singleton,
                Addressing_mode.REGISTER, Register.RF_singleton)); // move RE to RF
        expected1.add(new Subneg(Addressing_mode.IM, Immediate.Zeros,
                Addressing_mode.REGISTER, Register.RF_singleton)); // invert RF
        expected1.add(new Subneg(Addressing_mode.IM, load.opA,
                Addressing_mode.REGISTER, Register.RF_singleton)); // RF = RE + addr
        expected1.add(new Subneg(Addressing_mode.MEMORY_INDIRECT, Register.RF_singleton,
                load.opB_mode, load.opB));
        ArrayList<Subneg> result1 = load.generate();
        for (int i = 0; i < expected1.size(); i++) {
            Assert.assertTrue("expected the same result at index 0", expected1.get(i).equals(result1.get(i)));
        }
    }
}
