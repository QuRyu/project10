package instruction_type;

import utilities.Util;

import java.security.InvalidParameterException;

public class MemoryAddr implements Operand {
    String n;

    public MemoryAddr(int n) {
        if (n < 0 || n > 255)
            throw new InvalidParameterException("Memory address " + n + " should be in the range [0..255]");
        this.n = Util.convertToBinary(n);
    }

    public String binary_representation() {
        return n;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemoryAddr that = (MemoryAddr) o;

        return n.equals(that.n);
    }

    @Override
    public int hashCode() {
        return n.hashCode();
    }
}
