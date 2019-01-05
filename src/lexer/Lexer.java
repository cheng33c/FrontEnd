package lexer;

import java.io.*;
import java.util.*;
import symbols.*;

public class Lexer {

    public static int line = 1;
    char peek = ' ';
    Hashtable<String, Word> words = new Hashtable<String, Word>();

    void reverse(Word w) {
	words.put(w.lexeme, w);
    }
    
    public Lexer() {
		reverse( new Word("if",   Tag.IF) );
		reverse( new Word("else", Tag.ELSE) );
		reverse( new Word("while", Tag.WHILE) );
		reverse( new Word("do", Tag.DO) );
		reverse( new Word("break", Tag.BREAK) );
		reverse( Word.True );
		reverse( Word.False );
		reverse( Type.Int );
		reverse( Type.Char );
		reverse( Type.Bool );
		reverse( Type.Float );
    }

    void readch() throws IOException {
		peek = (char) System.in.read();
    }

    boolean readch(char c) throws IOException {
		readch();
		if ( peek != c )
			return false;
		peek = ' ';
		return true;
    }

    public Token scan() throws IOException {

		for ( ; ; readch() ) {
            if (peek == ' ' || peek == '\t')
                continue;
            else if (peek == '\n')
                line = line + 1;
            else
                break;
        }
        switch (peek) {
        case '&':
            if ( readch('&') )
                return Word.and;
            else
                return new Token('&');

        case  '|':
            if ( readch('|') )
                return Word.or;
            else
                return new Token('|');

        case '=':
            if ( readch('=') )
                return Word.eq;
            else
                return new Token('=');

        case '!':
            if ( readch('=') )
                return Word.ne;
            else
                return new Token('!');

        case '<':
            if ( readch('=') )
                return Word.le;
            else
                return new Token('<');

        case '>':
            if ( readch('=') )
                return Word.ge;
            else
                return new Token('>');
        }

        // handle integer or float number and return it
        if ( Character.isDigit(peek) ) {
            int v = 0;
            do {
                v = 10 * v + Character.digit(peek, 10);
                readch();
            } while ( Character.isDigit(peek) );

            if (peek != '.') // return it if it's a integer
                return new Num(v);

            // if it's a float number , continue to read and return float number
            float x = v, d = 10;
            for ( ; ; ) {
                readch();
                if ( !Character.isDigit(peek) )
                    break;
                x = x + Character.digit(peek, 10) / d;
                d = d * 10;
            }

            return new Real(x);
        }

        // handle letter to string ID.
        // If ID find in Hashtable(words), then return it. Else put ID in Hashtable(words)
        if ( Character.isLetter(peek) ) {
            StringBuffer b = new StringBuffer();
            do {
                b.append(peek);
                readch();
            } while (Character.isLetterOrDigit(peek) );

            String s = b.toString();
            Word w = (Word)words.get(s);
            if ( w != null )
                return w;
            w = new Word(s, Tag.ID);
            words.put(s, w);

            return w;
        }

            // handle character like +,-,*,/, return it as a Token
            Token tok = new Token(peek);
            peek = ' ';
            return tok;
        }
}
