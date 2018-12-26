package inter;

import lexer.*;
import symbols.*;

public class Temp extends Expr {
    static int count = 0;
    int number = 0;

    public Temp(type p) {
	super(Word.temp, p);
	number = ++count;
    }

    public String toString() {
	return op.toString() + " " + expr.toString();
    }
}
