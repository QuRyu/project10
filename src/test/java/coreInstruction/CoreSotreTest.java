package coreInstruction;

import instruction_type.Addressing_mode;
import instruction_type.MemoryAddr;
import instruction_type.Register;
import org.junit.Assert;
import org.junit.Test;
import subneg.Subneg;

import java.util.ArrayList;

public class CoreSotreTest {

    MemoryAddr addr1 = new MemoryAddr(2);
    Register src1 = new Register(Register.register_type.RA);
    CoreStore store1 = new CoreStore(src1, Addressing_mode.REGISTER, addr1, Addressing_mode.MEMORY);

    @Test
    public void generate_test() throws Exception {
        ArrayList<Subneg> expected1 = new ArrayList<Subneg>();
        expected1.add(new Subneg(Addressing_mode.MEMORY, addr1, Addressing_mode.MEMORY, addr1));
        expected1.add(new Subneg(Addressing_mode.REGISTER, src1, Addressing_mode.MEMORY, addr1));

        ArrayList<Subneg> result1 = store1.generate();
        for (int i = 0; i < expected1.size(); i++) {
            Assert.assertTrue("expected the same result at index " + i, expected1.get(i).equals(result1.get(i)));
        }
    }
}
