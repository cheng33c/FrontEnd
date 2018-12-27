package symbols;

import lexer;

public class Array extends Type {
    public Type of;
    public int size = 1;

    // Init Array
    public Array(int sz, Type p) {
	// sz * p.width means Array's length(size)  multip type size(p.width)
	super("[]", Tag.INDEX, sz*p.width);
	size = sz;
	of = p;
    }

    public String toString() {
	return "[" + size + "]" + of.toString();
    }
}
