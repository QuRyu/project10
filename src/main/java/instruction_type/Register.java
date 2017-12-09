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

    // TODO: 12/8/17 add access to PC
    public enum register_type {
        RA, RB, RC, RD, RE, SPEC_RF, SPEC_RG, SP;
    }

    public Register(register_type type) {
        this.type = type;
    }

    // register table
    private static final ArrayList<String> registers = new ArrayList<String>(Arrays.asList("RA", "RB", "RC", "RD", "RE", "SP"));

    private static Map<register_type, String> register_tbl = new HashMap<register_type, String>();
    static {
        register_tbl.put(register_type.RA, "000");
        register_tbl.put(register_type.RB, "001");
        register_tbl.put(register_type.RC, "010");
        register_tbl.put(register_type.RD, "011");
        register_tbl.put(register_type.RE, "100");
        register_tbl.put(register_type.SPEC_RF, "101");
        register_tbl.put(register_type.SP, "110");
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

