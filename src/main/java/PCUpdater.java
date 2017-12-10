import instruction_type.PC;

public class LineUpdate {
    private PC pc;
    private int line;

    public LineUpdate(PC pc, int line) {
        this.pc = pc;
        this.line = line;
    }

    public void update(int curLine, int n) {
        if (curLine < line) // pc address is still subject to change, so update it
            pc.line_inc(n-1);
    }
}
