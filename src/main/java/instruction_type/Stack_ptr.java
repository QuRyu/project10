package instruction_type;

import utilities.Util;

public class Stack_ptr implements Operand {
    private int n; // the address pointed to

    public static Stack_ptr ptr = new Stack_ptr();

    private Stack_ptr() {
        n = 0;
    }

    public String binary_representation() {
        return Util.convertToBinary(n);
    }

    public void increment() {
        n += 1;
    }

    public void decrement() {
        n -= 1;
    }

}
