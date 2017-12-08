package coreInstruction;

import instruction_type.Addressing_mode;
import subneg.Subneg;

import java.util.ArrayList;

/**
 * Created by Quentin on 12/6/17.
 */
public class CoreGoto extends CoreInstruction {
    private String jumpAddr;

    public CoreGoto(String jumpto) {
        this.jumpAddr = jumpto;
    }

    public ArrayList<Subneg> generate() {
        ArrayList<Subneg> result = new ArrayList<Subneg>();
        result.add(new Subneg(Addressing_mode.IM, "11111111", Addressing_mode.IM, "00000000", jumpAddr));
        return result;
    }
}
