package coreInstruction;

import instruction_type.Addressing_mode;
import subneg.Subneg;

import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 */
public class CoreStore extends CoreInstruction {
    private String src;
    private Addressing_mode src_mode;
    private String addr;
    private Addressing_mode addr_mode;

    public CoreStore(String src, Addressing_mode src_mode, String addr, Addressing_mode addr_mode) {
        this.src = src;
        this.src_mode = src_mode;
        this.addr = addr;
        this.addr_mode = addr_mode;
    }

    public ArrayList<Subneg> generate() {
        ArrayList<Subneg> result = new ArrayList<Subneg>();
        result.add(new Subneg(addr_mode, addr, addr_mode, addr)); // clear memory address
        result.add(new Subneg(src_mode, src, addr_mode, addr));
        return result;
    }

}
