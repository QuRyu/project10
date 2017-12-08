package instruction_type;

import utilities.Util;

public class PCAddr implements Operand {
    private int n;

    public PCAddr() { }

    public void setLine(int n) {
        this.n = n;
    }

    public void line_inc(int n) {
        this.n += n;
    }

    public String binary_representation() {
        return Util.convertToBinary(n);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PCAddr pcAddr = (PCAddr) o;

        return n == pcAddr.n;
    }

    @Override
    public int hashCode() {
        return n;
    }
}
