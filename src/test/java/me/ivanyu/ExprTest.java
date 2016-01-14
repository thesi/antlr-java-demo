package me.ivanyu;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class ExprTest {

    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        ExprEvaluateLexer lexer = new ExprEvaluateLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprEvaluateParser parser = new ExprEvaluateParser(tokens);
        parser.prog();
    }

}