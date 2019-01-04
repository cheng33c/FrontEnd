package inter;

public class Seq extends Stmt {
    private Lexer lex;   // lexical parser of parser
    private Token look;  // look forward for lexical unit
    Env top = null;      // current or top symbols table
    int used = 0;        // storage location for variable declaration

    public Parser(Lexer l) throws IOException {
	lex = l;
	move();
    }

    void move() throws IOException {
	look = lex.scan();
    }

    void error(String s) {
	throw new Error("near line " + lex.line + ": " + s);
    }

    void match(int t) throws IOException {
	if ( look.tag == t )
	    move();
	else
	    error("syntax error");
    }

    public void program() throws IOException {
	Stmt s = block();
	int begin = s.newlabel();
	int after = s.newlabel();
	s.emitlabel(begin);
	s.gen(begin, after);
	s.emitlabel(after);
    }

    Stmt block() throws IOException { // block -> { decls stmts }
	match('{');
	Env savedEnv = top;
	top = new Env(top);
	decls();
	Stmt s = stmts();
	match('}');
	top = savedEnv;
	return s;
    }

    void decls() throws IOException {
	while ( look.tag == Tag.BASIC ) { // D -> type ID;
	    Type p = type();
	    Token tok = look;
	    match(Tag.ID);
	    match(';');
	    Id id = new Id( (Word)tok, p, used);
	    top.put( tok, id );
	    used = used + p.width;
	}
    }

    Type type() throws IOException {
	Type p = type();
	Token tok = look;
	match(Tag.ID);
	match(';');
    }
}
