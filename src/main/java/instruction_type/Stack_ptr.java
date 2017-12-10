package instruction_type;

import utilities.Util;

/**
 * Singleton pattern;
 * every object trying to get an instance of stack_ptr must use the copy method
 */
public class Stack_ptr implements Memory {
    private int n; // the address pointed to

    public static Stack_ptr singleton = new Stack_ptr(0);

    private Stack_ptr(int n) {
        this.n = n;
    }

    public String binary_representation() {
        return Util.convertToBinary(n, false);
    }

    public Stack_ptr incr_copy() {
        return new Stack_ptr(n++);
    }

    public Stack_ptr copy_decr() {
        return new Stack_ptr(n--);
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
