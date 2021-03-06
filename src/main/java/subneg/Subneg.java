package subneg;

import instruction_type.Addressing_mode;
import instruction_type.Operand;
import utilities.Util;

/**
 * Created by Quentin on 12/5/17.
 */
public class Subneg {
    private Operand opA;
    private Operand opB;
    private Addressing_mode srcA;
    private Addressing_mode srcB;

    private Operand jumpToAddr;
    private AddressType atype;
    private int relative;

    public enum AddressType {
        PC, ADDR, RELATIVE;
    }

    public Subneg(Addressing_mode srcA, Operand opA, Addressing_mode srcB, Operand opB) {
        this(srcA, opA, srcB, opB, AddressType.PC);
    }

    public Subneg(Addressing_mode srcA, Operand opA, Addressing_mode srcB, Operand opB, int relative) {
        this(srcA, opA, srcB, opB, AddressType.RELATIVE);
        this.relative = relative;
    }

    public Subneg(Addressing_mode srcA, Operand opA, Addressing_mode srcB, Operand opB, Operand jumpAddr) {
        this(srcA, opA, srcB, opB, AddressType.ADDR);
        this.jumpToAddr = jumpAddr;
    }

    public Subneg(Addressing_mode srcA, Operand opA, Addressing_mode srcB, Operand opB, AddressType atype) {
        this.srcA = srcA;
        this.opA = opA;
        this.srcB = srcB;
        this.opB = opB;
        this.atype = atype;
    }

    public String dump(int curPC) {
        StringBuilder temp = new StringBuilder("");
        temp.append(srcA.binary_representation());
        temp.append(opA.binary_representation());
        temp.append(srcB.binary_representation());
        temp.append(opB.binary_representation());
        switch (atype) {
            case ADDR:
                temp.append(jumpToAddr.binary_representation());
                break;
            case PC:
                temp.append(Util.convertToBinary(curPC+1, false));
                break;
            case RELATIVE:
                temp.append(Util.convertToBinary(curPC + relative, false));
                break;
        }

        assert (temp.toString().length() == 30);
        return temp.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subneg subneg = (Subneg) o;

        if (!opA.equals(subneg.opA)) return false;
        if (!opB.equals(subneg.opB)) return false;
        if (srcA != subneg.srcA) return false;
        if (srcB != subneg.srcB) return false;
        return atype == subneg.atype;
    }

    @Override
    public int hashCode() {
        int result = opA.hashCode();
        result = 31 * result + opB.hashCode();
        result = 31 * result + srcA.hashCode();
        result = 31 * result + srcB.hashCode();
        result = 31 * result + atype.hashCode();
        return result;
    }
}
