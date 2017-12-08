/**
 * Created by Quentin on 12/6/17.
 */
public enum Addressing_mode {

    IM {
        @Override
        public String binary_representation() {
            return "000";
        }
    }, REGISTER {
        @Override
        public String binary_representation() {
            return "001";
        }
    }, MEMORY {
        @Override
        public String binary_representation() {
            return "010";
        }
    }, Input {
        @Override
        public String binary_representation() {
            return "011";
        }
    }, Output {
        @Override
        public String binary_representation() {
            return "100";
        }
    };

    public abstract String binary_representation();
}
