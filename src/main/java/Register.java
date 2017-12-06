import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Quentin on 12/5/17.
 */
public class Register {
    // register mapping to memory
    public static final String RA = "11111111";
    public static final String RB = "11111110";
    public static final String RC = "11111101";
    public static final String RD = "11111100";
    public static final String RE = "11111011";

    // present registers
    public static final ArrayList<String> registers = new ArrayList<String>(Arrays.asList("RA", "RB", "RC", "RD", "RE"));

}
