import utilities.Util;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("assembler requires 1 argument!");
            return;
        }

        try {
            BufferedReader fin = new BufferedReader(new FileReader(args[1]));
            BufferedWriter fout = new BufferedWriter(new FileWriter(args[1] + ".mif"));
            String tempLine;

            ArrayList<String> lines = new ArrayList<String>();
            while ((tempLine =fin.readLine()) != null)
                lines.add(tempLine);

            ArrayList<String> instructions = Generator.generateCoreInstruction(Scanner.scan(lines));

            int line = 0;

            fout.write("DEPTH = 256;\n");
            fout.write("WIDTH = 30;\n");
            fout.write("ADDRESS_WIDTH=HEX;\n");
            fout.write("DATA_RADIX=BIN;\n");
            fout.write("CONTENT");
            fout.write("BEGIN");
            for (String s : instructions)
                fout.write(Util.convertToHex(line++) + " : " + s + ";\n");
            if (line < 255)
                fout.write("[" + Util.convertToHex(line) + Util.convertToHex(255) + "] : " +
                    "111111111111111111111111111111\n");
            else if(line == 255)
                fout.write(Util.convertToHex(255) + " : " +
                    "111111111111111111111111111111\n");
            fout.write("END\n");

        } catch (FileNotFoundException e) {
            System.out.println("Could not find file " + args[1]);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Failed to write file");
            e.printStackTrace();
        }
    }
}
