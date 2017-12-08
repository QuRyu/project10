import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Quentin on 12/5/17.
 */
public enum Register {
    RA, RB, RC, RD, RE, SP, PC, CR, SPEC_RF;

    // register table
    private static final ArrayList<String> registers = new ArrayList<String>(Arrays.asList("RA", "RB", "RC", "RD", "RE", "SP", "PC", "CR"));

    public static boolean register_lookup(String r)  {
        return registers.contains(r);
    }

    public abstract String binary_representation();
}

