package inter;

import lexer.*;
import symbols.*;

public class Expr extends Node {

    public Token op;
    public Type  type;

    Expr(Token tok, Type p) {
		op = tok;
		type = p;
    }

    // Return a item to be the right part of a three address directives
    // Example: give a expression: E = E1 + E2
    // gen() return: x1 + x2, and x1, x2 restore the address of E1, E2
    public Expr gen() {
		return this;
    }

    // Calculate a expression to a single address
    public Expr reduce() {
    	return this;
    }

    public void jumping(int t, int f) {
		emitjumps( toString(), t, f );
    }

    public void emitjumps(String test, int t, int f) {
		if ( t != 0 && f != 0 ) {
			emit("if " + test + " goto L" + t);
			emit("goto L" + f);
		}
		else if ( t != 0 ) {
			emit("if " + test + " goto L" + t);
		}
		else if ( f != 0 ) {
			emit("iffalse " + test + " goto L" + f);
		}
		else
			; // Don't construct any instruction
	}

    public String toString() {
    	return op.toString();
    }
}
