package me.ivanyu.field;
import java.io.IOException;

import me.ivanyu.*;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Recognizer;


public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	    FieldLexer l = new FieldLexer(new ANTLRInputStream(Test.class.getResourceAsStream("/example.field")));
	    FieldParser p = new FieldParser(new CommonTokenStream(l));
	    p.addErrorListener(new BaseErrorListener() {
	        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, Exception e) {
	            throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
	        }
	    });
	    p.field();
	}

}
