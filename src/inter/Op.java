package inter;

import lexer.*;
import symbols.*;

public class Op extends Expr {
    public Op(Token tok, type p) {
	super(tok, p);
    }

    public Expr reduce() {
	Expr x = gen();
	Temp t = new Temp(type);
	emit( t.toString() + " = " + x.toString() );
	return t;
    }
}
