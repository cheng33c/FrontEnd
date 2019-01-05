package inter;

import lexer.*;
import symbols.*;

public class Constant extends Expr {

    // constructure constant bool expression
    public Constant (Token tok, Type p) {
	super (tok, p);
    }

    // constructure constant number i
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
    // This method use for constant bool expression not for constant number
    // If bool expression is true and t!= 0 then jump to true address
    // Else if bool expression is false and f != 0 then jump to false address
    public void jumping(int t, int f) {
        if ( this == True && t != 0 )
            emit( "goto L" + t );
        else if ( this == False && f != 0 )
            emit( "goto L" + f );
    }
}
