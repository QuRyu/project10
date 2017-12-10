package coreInstruction;

import instruction_type.*;
import org.junit.Assert;
import org.junit.Test;
import subneg.Subneg;

import java.util.ArrayList;

public class CoreIncTest {

    Immediate immediate = new Immediate(20);
    Register RA = new Register(Register.register_type.RA);
    CoreInc inc = new CoreInc(immediate, Addressing_mode.IM, RA, Addressing_mode.REGISTER);

    @Test
    public void generate_test() throws Exception {
        ArrayList<Subneg> expected1 = new ArrayList<Subneg>();
        expected1.add(new Subneg(Addressing_mode.REGISTER, Register.RF_singleton,
                Addressing_mode.REGISTER, Register.RF_singleton)); // clear RF
        expected1.add(new Subneg(inc.opA_mode, inc.opA,
                Addressing_mode.REGISTER, Register.RF_singleton)); // move src to RF
        expected1.add(new Subneg(Addressing_mode.IM, Immediate.Zeros,
                Addressing_mode.REGISTER, Register.RF_singleton)); // invert RF
        expected1.add(new Subneg(Addressing_mode.REGISTER, Register.RF_singleton, inc.opB_mode, inc.opB));
        ArrayList<Subneg> result1 = inc.generate();

        for (int i = 0; i < expected1.size(); i++) {
            Assert.assertTrue("expected the same result at index 0", expected1.get(i).equals(result1.get(i)));
        }
    }
}
