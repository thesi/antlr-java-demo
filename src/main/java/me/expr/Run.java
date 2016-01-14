package me.expr;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Run {
    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream("b = 5 \n a = 10 \n a + b \n");
        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);
        ParseTree tree = parser.input();

        ExprBaseVisitorImp calcVisitor = new ExprBaseVisitorImp();
        Double result = calcVisitor.visit(tree);
        System.out.println("Result: " + result);
    }
}
