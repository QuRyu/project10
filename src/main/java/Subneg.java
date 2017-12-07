/**
 * Created by Quentin on 12/5/17.
 */
public class Subneg {
    private String opA;
    private String opB;
    private Addressing_mode srcA;
    private Addressing_mode srcB;

    private String jumpToAddr;
    private addr_type atype;
    private int relative;

    // type of the third parameter
    // PC denotes sequential implementation, while ADDR denotes jump_to address
    enum addr_type {PC, ADDR, RELATIVE}


    public Subneg(Addressing_mode srcA, String opA, Addressing_mode srcB, String opB) {
        this(srcA, opA, srcB, opB, addr_type.PC);
    }

    public Subneg(Addressing_mode srcA, String opA, Addressing_mode srcB, String opB, int relative) {
        this(srcA, opA, srcB, opB, addr_type.RELATIVE);
        this.relative = relative;
    }

    public Subneg(Addressing_mode srcA, String opA, Addressing_mode srcB, String opB, String jumpAddr) {
        this(srcA, opA, srcB, opB, addr_type.ADDR);
        this.jumpToAddr = jumpAddr;
    }

    public Subneg(Addressing_mode srcA, String opA, Addressing_mode srcB, String opB, addr_type atype) {
        this.srcA = srcA;
        this.opA = opA;
        this.srcB = srcB;
        this.opB = opB;
        this.atype = atype;
    }

    public String dump(int curPC) {
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
