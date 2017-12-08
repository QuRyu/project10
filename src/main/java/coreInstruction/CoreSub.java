package coreInstruction;

import instruction_type.Addressing_mode;
import subneg.Subneg;

import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 */
public class CoreSub extends CoreInstruction {
    private String src;
    private Addressing_mode src_mode;
    private String dst;
    private Addressing_mode dst_mode;

    public CoreSub(String src, Addressing_mode src_mode, String dst, Addressing_mode dst_mode) {
        this.src = src;
        this.src_mode = src_mode;
        this.dst = dst;
        this.dst_mode = dst_mode;
    }


    public ArrayList<Subneg> generate() {
        ArrayList<Subneg> result = new ArrayList<Subneg>();
        result.add(new Subneg(src_mode, src, dst_mode, dst));
        return result;
    }
}
