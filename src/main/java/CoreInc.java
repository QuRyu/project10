import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 */
// Add instruction
public class CoreInc extends CoreInstruction {
    private String src;
    private Addressing_mode src_mode;
    private String dst; // both the source and destination
    private Addressing_mode dst_mode;

    public CoreInc(String src, Addressing_mode src_mode, String dst, Addressing_mode dst_mode) {
        this.src = src;
        this.src_mode = src_mode;
        this.dst = dst;
        this.dst_mode = dst_mode;
    }

    public ArrayList<Subneg> generate() {
        ArrayList<Subneg> result = new ArrayList<Subneg>();
        result.add(new Subneg(Addressing_mode.REGISTER, Register.SPEC_RF.binary_representation(),
                Addressing_mode.REGISTER, Register.SPEC_RF.binary_representation())); // clear RF
        result.add(new Subneg(src_mode, src, Addressing_mode.REGISTER, Register.SPEC_RF.binary_representation())); // move src to RF
        result.add(new Subneg(Addressing_mode.IM, "00000000",
                Addressing_mode.REGISTER, Register.SPEC_RF.binary_representation())); // invert RF
        result.add(new Subneg(Addressing_mode.REGISTER, Register.SPEC_RF.binary_representation(), dst_mode, dst)); // dst = RF + dst
        return result;
    }
}
