import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class IntegratedTest {

    public void scaffold(ArrayList<String> program, ArrayList<String> expected) {
        ArrayList<String> result = Generator.generateCoreInstruction(Scanner.scan(program));
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertTrue("generated program is not the same at index " + i + "\n" +
                    "expected: " + expected.get(i) + "\n" +
                    "generated: " + result.get(i), expected.get(i).equals(result.get(i)));
        }
    }

    @Test
    public void test1() {
        ArrayList<String> program = new ArrayList<String>();
        program.add("Movei 2 RB");
        program.add("Push RB");
        program.add("label: ");
        program.add("Add RB RA");
        program.add("Branch label");


        ArrayList<String> expected = new ArrayList<String>();
        expected.add("001" + "00000001" + "001" + "00000001" + "00000001");
        expected.add("000" + "00000010" + "001" + "00000001" + "00000010");
        expected.add("010" + "00000000" + "010" + "00000000" + "00000011");
        expected.add("001" + "00000001" + "010" + "00000000" + "00000100");
        expected.add("001" + "00000101" + "001" + "00000101" + "00000101");
        expected.add("001" + "00000001" + "001" + "00000101" + "00000110");
        expected.add("000" + "00000000" + "001" + "00000101" + "00000111");
        expected.add("001" + "00000000" + "001" + "00000101" + "00001000");
        expected.add("000" + "11111111" + "000" + "00000000" + "00000011"); // TODO: 12/12/17 explnation for why it is 011 instead of 100; CPU architecture

        scaffold(program, expected);
    }

}
