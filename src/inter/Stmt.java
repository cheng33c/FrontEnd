package inter;

public class Stmt extends Node {

    public Stmt() {}

    public static Stmt Null = new Stmt();

    //the arguments is the label of start and next instruction
    public void gen(int b, int a) {}

    // record the label of next instruction
    int after = 0;

    // break
    public static Stmt Enclosing = Stmt.Null;

}
