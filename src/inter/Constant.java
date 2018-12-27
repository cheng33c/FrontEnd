package inter;

import lexer.*;
import symbols.*;

public class Constant extends Expr {
    
    public Constant (Token tok, Type p) {
	super (tok, p);
    }

    public Constant (int i) {
	super (new Num(i), Type.Int);
    }

    public static final Constant
	True = new Constant(Word.True, Type.Bool),
	False = new Constant(Word.False, Type.Bool);

    // If this constant is a static object(True) and t != 0 then
    // construct a jump instruction to object t
    // Else if this constant is not a static object(False) and f != 0 then
    // construct a jump instruction to object f
    public void jumping(int t, int f) {
	if ( this == True && t != 0 )
	    emit( "goto L" + t );
	else if ( this == False && f != 0 )
	    emit( "goto L" + f );
    }
}
