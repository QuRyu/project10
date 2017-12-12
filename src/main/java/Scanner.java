import instruction.*;
import instruction_type.PC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scanner {

    public static ParseResult scan(ArrayList<String> lines) {
        ArrayList<Instruction> instructions = new ArrayList<Instruction>();
        ArrayList<PC> jumpTo = new ArrayList<PC>();
        ArrayList<PCUpdater> updaters = new ArrayList<PCUpdater>();
        Map<String, PC> labelTbl = new HashMap<String, PC>();
        Map<String, PC> unsolvedLabel = new HashMap<String, PC>();


        int line = 0;

        for (String s : lines) {
            if (s.trim().equals(""))
                continue;

            String[] words = s.split("\\W+");
            String ins = words[0];

            if (ins.equals("Add"))
                instructions.add(new Add(words[1], words[2]));
            else if (ins.equals("Branch")) {
                String label = words[1];
                if (labelTbl.containsKey(label))
                    instructions.add(new Branch(labelTbl.get(label)));
                else {
                    PC temp = new PC();
                    unsolvedLabel.put(label, temp);
                    instructions.add(new Branch(temp));
                }
            } else if (ins.equals("Call")) {
                String label = words[1];
                if (labelTbl.containsKey(label))
                    instructions.add(new Call(labelTbl.get(label)));
                else {
                    PC temp = new PC();
                    unsolvedLabel.put(label, temp);
                    instructions.add(new Call(temp));
                }
            } else if (ins.equals("Halt"))
                instructions.add(new Halt());
            else if (ins.equals("Iport"))
                instructions.add(new Iport(words[1]));
            else if (ins.equals("Load"))
                instructions.add(new Load(words[1], words[2]));
            else if (ins.equals("Loadi"))
                instructions.add(new Load(words[1], words[2], true));
            else if (ins.equals("Move"))
                instructions.add(new Move(words[1], words[2]));
            else if (ins.equals("Movei"))
                instructions.add(new Move(words[1], words[2], true));
            else if (ins.equals("Oport"))
                instructions.add(new Oport(words[1]));
            else if (ins.equals("Pop"))
                instructions.add(new Pop(words[1]));
            else if (ins.equals("Push"))
                instructions.add(new Push(words[1]));
            else if (ins.equals("Return"))
                instructions.add(new Return());
            else if (ins.equals("Store"))
                instructions.add(new Store(words[1], words[2]));
            else if (ins.equals("Storei"))
                instructions.add(new Store(words[1], words[2], true));
            else if (ins.equals("Sub"))
                instructions.add(new Sub(words[1], words[2]));
            else {
                // TODO: 12/9/17 handle label case and not meaningful input
                s = s.replaceAll("\\S+", "");
                if (s.charAt(s.length() - 1) == ':') { // a label
                    String label = s.substring(0, s.length() - 1);
                    PC temp;
                    if (unsolvedLabel.containsKey(label)) {
                         temp = unsolvedLabel.get(label);
                        unsolvedLabel.remove(label); // now the label is found, remove it
                        labelTbl.put(label, temp); // add to labelTbl
                        temp.setLine(line); // update the line
                    } else {
                        temp = new PC(line);
                        labelTbl.put(label, temp);
                    }
                    updaters.add(new PCUpdater(temp, line));
                } else // unspecified input
                    throw new RuntimeException("unknown argument");
            }

            line++; // update line number
        }


        if (unsolvedLabel.size() != 0) { // there is unknown label in the program
            StringBuilder ss = new StringBuilder();
            for (Map.Entry<String, PC> entry : unsolvedLabel.entrySet()) {
                ss.append(entry.getKey());
                ss.append("\n");
            }
            throw new RuntimeException("labels not present in this program:\n" + ss.toString());
        }

        ParseResult result = new ParseResult();
        result.jumps = updaters;
        result.instructions = instructions;
        return result;
    }

}
