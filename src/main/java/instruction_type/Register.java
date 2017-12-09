package instruction_type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Quentin on 12/5/17.
 */
public class Register implements Operand {
    private register_type type;

    public static final Register RF_singleton = new Register(register_type.SPEC_RF);
    public static final Register RE_singleton = new Register(register_type.RE);

    public enum register_type {
        RA, RB, RC, RD, RE, SPEC_RF, SPEC_RG, SP, PC,
        IPORT, OPORT; // treat iport and oport also as registers
    }

    public Register(register_type type) {
        this.type = type;
    }

    // This function only helps create registers RA, RB, RC, RD, RE,
    public static Register createRegister(String s) {
        if (s == "RA")
            return new Register(register_type.RA);
        else if (s.equals("RB"))
            return new Register(register_type.RB);
        else if (s.equals("RC"))
            return new Register(register_type.RC);
        else if (s.equals("RD"))
            return new Register(register_type.RD);
        else if (s.equals("RE"))
            return new Register(register_type.RE);
        throw new RuntimeException("register " + s + " is not present");
    }
    // register table
    private static final ArrayList<String> registers = new ArrayList<String>(Arrays.asList("RA", "RB", "RC", "RD", "RE", "SP", "PC"));

    private static Map<register_type, String> register_tbl = new HashMap<register_type, String>();
    static {
        register_tbl.put(register_type.RA, "00000000");
        register_tbl.put(register_type.RB, "00000001");
        register_tbl.put(register_type.RC, "00000010");
        register_tbl.put(register_type.RD, "00000011");
        register_tbl.put(register_type.RE, "00000100");
        register_tbl.put(register_type.SPEC_RF, "000000101");
        register_tbl.put(register_type.SP, "000000110");
        register_tbl.put(register_type.PC, "000000111");
        register_tbl.put(register_type.SPEC_RG, "00001000");
    }

    public static boolean register_lookup(String r)  {
        return registers.contains(r);
    }

    public String binary_representation() {
        return register_tbl.get(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Register register = (Register) o;

        return type == register.type;
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }
}

