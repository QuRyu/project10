/**
 * Created by Quentin on 12/6/17.
 */
public enum Addressing_mode {

    IM {
        @Override
        public String machine_representation() {
            return "000";
        }
    }, REGISTER {
        @Override
        public String machine_representation() {
            return "001";
        }
    }, MEMORY {
        @Override
        public String machine_representation() {
            return "010";
        }
    }, Input {
        @Override
        public String machine_representation() {
            return "011";
        }
    }, Output {
        @Override
        public String machine_representation() {
            return "100";
        }
    };

    public abstract String machine_representation();
}
