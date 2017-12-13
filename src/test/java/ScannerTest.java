import org.junit.Assert;
import instruction.*;
import instruction_type.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ScannerTest {

    @Test
    public void scan_test() {
        ArrayList<String> instructions = new ArrayList<String>(Arrays.asList("MOVE RA RB", "ADD 8 RC", "BRANCH 0", "ADDDD RA RB"));

        ArrayList<Instruction> expected = new ArrayList<Instruction>(Arrays.asList(new Move("RA","RB"), new Add("8","RC"), new Branch(new PC(0))));
        try{Assert.assertTrue("test1 "+Scanner.scan(instructions).instructions, expected == Scanner.scan(instructions).instructions);
        }catch (RuntimeException e){
            Assert.assertTrue(true);

        };
    }
}
