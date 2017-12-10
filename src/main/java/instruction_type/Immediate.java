package instruction_type;

import utilities.Util;

import java.security.InvalidParameterException;

public class Immediate implements Operand {
    private String n;

    public static final Immediate Zeros = new Immediate(0);
    public static final Immediate Minus_one = new Immediate(-1);
    public static final Immediate One = new Immediate(1);

    public Immediate(int n) {
        if (n < -128 || n > 127)
            throw new InvalidParameterException("Immediate value " + n + " is out of range[-128, 127]");
        this.n = Util.convertToBinary(n, true);
    }

    public String binary_representation() {
        return n;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Immediate immediate = (Immediate) o;

        return n.equals(immediate.n);
    }

    @Override
    public int hashCode() {
        return n.hashCode();
    }
}
