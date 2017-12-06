import java.util.HashMap;
import java.util.Map;

/**
 * Created by Quentin on 12/5/17.
 */
public class Subneg {
    private String opA;
    private String opB;
    private src_type srcA;
    private src_type srcB;

    private String jumpToAddr;
    private addr_type atype;
    private int relative;

    // type of the third parameter
    // PC denotes sequential implementation, while ADDR denotes jump_to address
    enum addr_type {PC, ADDR, RELATIVE}

    // type of addresses
    enum src_type {IM, REGISTER, MEMORY, Input, Output}
    private final static Map<src_type, String> src_map = new HashMap<src_type, String>();
    static {
        src_map.put(src_type.IM, "000");
        src_map.put(src_type.REGISTER, "001");
        src_map.put(src_type.MEMORY, "010");
        src_map.put(src_type.Input, "011");
        src_map.put(src_type.Output, "100");
    }

    public Subneg(src_type srcA, String opA, src_type srcB, String opB) {
        this(srcA, opA, srcB, opB, addr_type.PC);
    }

    public Subneg(src_type srcA, String opA, src_type srcB, String opB, int relative) {
        this(srcA, opA, srcB, opB, addr_type.RELATIVE);
        this.relative = relative;
    }

    public Subneg(src_type srcA, String opA, src_type srcB, String opB, String jumpAddr) {
        this(srcA, opA, srcB, opB, addr_type.ADDR);
        this.jumpToAddr = jumpAddr;
    }

    public Subneg(src_type srcA, String opA, src_type srcB, String opB, addr_type atype) {
        this.srcA = srcA;
        this.opA = opA;
        this.srcB = srcB;
        this.opB = opB;
        this.atype = atype;
    }

    public String dump(int curPC) {
        StringBuilder temp = new StringBuilder();
        temp.append(src_map.get(srcA));
        temp.append(opA);
        temp.append(src_map.get(srcB));
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
