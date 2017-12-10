import instruction_type.PC;

public class PCUpdater {
    private PC pc;
    private int line;

    public PCUpdater(PC pc, int line) {
        this.pc = pc;
        this.line = line;
    }

    public void update(int curLine, int n) {
        if (curLine < line) // pc address is still subject to change, so update it
            pc.line_inc(n-1);
    }
}
