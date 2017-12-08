package instruction_type;

import utilities.Util;

public class PCAddr implements Operand {
    private int n;

    public PCAddr() { }

    public void setLine(int n) {
        this.n = n;
    }

    public String binary_representation() {
        return Util.convertToBinary(n);
    }

}
