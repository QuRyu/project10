import coreInstruction.CoreInstruction;
import instruction.Instruction;
import subneg.Subneg;

import java.util.ArrayList;

public class Generator {

    public static ArrayList<String> generateCoreInstruction(ParseResult parsed) {
        ArrayList<Instruction> instructions = parsed.instructions;
        ArrayList<PCUpdater> addrs = parsed.jumps;
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<CoreInstruction> tempCore;

        int curLine = 0;

        for (int i =0; i<parsed.instructions.size(); i++) {
            ArrayList<Subneg> tempNeg = new ArrayList<Subneg>();
            Instruction ins = instructions.get(i);
            tempCore = ins.generate();
            for (CoreInstruction ci : tempCore) {
                tempNeg.addAll(ci.generate());
            }
            incrementPCAddr(addrs, curLine, tempNeg.size());

            for (Subneg s : tempNeg)
                result.add(s.dump(curLine++));
        }

        return result;
    }

    public static void incrementPCAddr(ArrayList<PCUpdater> addrs, int curLine, int n) {
        for(PCUpdater updater : addrs)
            updater.update(curLine, n);


    }
}
