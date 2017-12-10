package instruction_type;

/**
 * Created by Quentin on 12/6/17.
 */
public enum Addressing_mode {

    /**
     * if IM is the addressing mode for the second argument, no write back will happen
     */
    IM {
        @Override
        public String binary_representation() {
            return "000";
        }
    },
    /**
     * read the value from register; if it is the addressing mode for second argument, the
     * result will be written back to the register
     */
    REGISTER {
        @Override
        public String binary_representation() {
            return "001";
        }
    },
    /**
     * read the value from memory; if it is the addressing mode for second argument, the
     * result will be written back to the memory address
     */
    MEMORY {
        @Override
        public String binary_representation() {
            return "010";
        }
    },
    /**
     * if it is the addressing mode for the first argument, the value of the register referenced will be
     * used to index into memory to retrieve the value of the memory cell; similar to [RA] notation
     * if it is the addressing mode for the second argument, the value of the register referenced will be
     * used to index into memory to retrieve the value, and the result will be put back into the same memory address
     */
    MEMORY_INDIRECT {
        @Override
        public String binary_representation() {
            return "011";
        }
    };

    public abstract String binary_representation();
}
