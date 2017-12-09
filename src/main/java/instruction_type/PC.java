package instruction_type;

import utilities.Util;

public class PC implements Operand {
    private int n;

    public PC() { }

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

        PC pc = (PC) o;

        return n == pc.n;
    }

    @Override
    public int hashCode() {
        return n;
    }
}
