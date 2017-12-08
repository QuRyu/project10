package subneg;

import instruction_type.AddressType;
import instruction_type.Addressing_mode;
import utilities.Util;

/**
 * Created by Quentin on 12/5/17.
 */
public class Subneg {
    private String opA;
    private String opB;
    private Addressing_mode srcA;
    private Addressing_mode srcB;

    private String jumpToAddr;
    private AddressType atype;
    private int relative;

    public Subneg(Addressing_mode srcA, String opA, Addressing_mode srcB, String opB) {
        this(srcA, opA, srcB, opB, AddressType.PC);
    }

    public Subneg(Addressing_mode srcA, String opA, Addressing_mode srcB, String opB, int relative) {
        this(srcA, opA, srcB, opB, AddressType.RELATIVE);
        this.relative = relative;
    }

    public Subneg(Addressing_mode srcA, String opA, Addressing_mode srcB, String opB, String jumpAddr) {
        this(srcA, opA, srcB, opB, AddressType.ADDR);
        this.jumpToAddr = jumpAddr;
    }

    public Subneg(Addressing_mode srcA, String opA, Addressing_mode srcB, String opB, AddressType atype) {
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
