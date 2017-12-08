package subneg;

import instruction_type.Addressing_mode;
import instruction_type.Operand;
import utilities.Util;

/**
 * Created by Quentin on 12/5/17.
 */
public class Subneg {
    private Operand opA;
    private  Operand opB;
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

    public String dump(int curPC, int stack_ptr) {
        StringBuilder temp = new StringBuilder();
        temp.append(srcA.binary_representation());
        temp.append(opA);
        temp.append(srcB.binary_representation());
        temp.append(opB);
        switch (atype) {
            case ADDR:
                temp.append(jumpToAddr);
            case PC:
                temp.append(Util.convertToBinary(curPC));
            case RELATIVE:
                temp.append(Util.convertToBinary(curPC + relative));
        }
        temp.append("00");
        assert (temp.length() == 32);
        return temp.toString();
    }

}
