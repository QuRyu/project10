package coreInstruction;

import instruction_type.*;
import org.junit.Assert;
import org.junit.Test;
import subneg.Subneg;

import java.util.ArrayList;

public class CoreGotoTest {
    PC pcaddr = new PC();
    RAM addr1 = new RAM(2);
    CoreGoto goto1 = new CoreGoto(pcaddr);

    @Test
    public void generate_test() throws Exception {
        ArrayList<Subneg> expected1 = new ArrayList<Subneg>();
        expected1.add(new Subneg(Addressing_mode.IM, Immediate.Minus_one, Addressing_mode.IM, Immediate.Zeros, goto1.opB));

        ArrayList<Subneg> result1 = goto1.generate();

        Assert.assertTrue("expected the same result at index 0", expected1.get(0).equals(result1.get(0)));

    }
}
