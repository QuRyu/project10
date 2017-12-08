package instruction_type;

import utilities.Util;

public class Stack_ptr implements Operand {
    private int n; // the address pointed to

    public static Stack_ptr ptr = new Stack_ptr();

    private Stack_ptr() {
        n = -1;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stack_ptr stack_ptr = (Stack_ptr) o;

        return n == stack_ptr.n;
    }

    @Override
    public int hashCode() {
        return n;
    }
}
