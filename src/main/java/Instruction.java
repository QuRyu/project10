import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Quentin on 12/5/17.
 */
public interface Instruction {
    ArrayList<CoreInstruction> generate();

    public static final ArrayList<String> nstructions = new ArrayList<String>(Arrays.asList("move", "load", "store", "bra",
            "braz", "halt", "push", "pop", "oport", "iport", "add", "sub"));


}

