package me.expr;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashMap;

public class ExprBaseVisitorImp extends ExprBaseVisitor<Double> {
    private HashMap<String, Double> variables = new HashMap<String, Double>();

    @Override
    public Double visitPlus(@NotNull ExprParser.PlusContext ctx) {
        return visit(ctx.plusOrMinus()) + visit(ctx.multOrDiv());
    }
    
    @Override
    public Double visitMinus(@NotNull ExprParser.MinusContext ctx) {
        return visit(ctx.plusOrMinus()) - visit(ctx.multOrDiv());
    }

    @Override
    public Double visitMultiplication(@NotNull ExprParser.MultiplicationContext ctx) {
        return visit(ctx.multOrDiv()) * visit(ctx.pow());
    }

    @Override
    public Double visitDivision(@NotNull ExprParser.DivisionContext ctx) {
        return visit(ctx.multOrDiv()) / visit(ctx.pow());
    }

    @Override
    public Double visitSetVariable(@NotNull ExprParser.SetVariableContext ctx) {
        Double value = visit(ctx.plusOrMinus());
        variables.put(ctx.ID().getText(), value);
        return value;
    }

    @Override
    public Double visitPower(@NotNull ExprParser.PowerContext ctx) {
        if (ctx.pow() != null)
            return Math.pow(visit(ctx.unaryMinus()), visit(ctx.pow()));
        return visit(ctx.unaryMinus());
    }

    @Override
    public Double visitChangeSign(@NotNull ExprParser.ChangeSignContext ctx) {
        return -1*visit(ctx.unaryMinus());
    }

    @Override
    public Double visitBraces(@NotNull ExprParser.BracesContext ctx) {
        return visit(ctx.plusOrMinus());
    }

    @Override
    public Double visitConstantPI(@NotNull ExprParser.ConstantPIContext ctx) {
        return Math.PI;
    }

    @Override
    public Double visitConstantE(@NotNull ExprParser.ConstantEContext ctx) {
        return Math.E;
    }

    @Override
    public Double visitInt(@NotNull ExprParser.IntContext ctx) {
        return Double.parseDouble(ctx.INT().getText());
    }

    @Override
    public Double visitVariable(@NotNull ExprParser.VariableContext ctx) {
        return variables.get(ctx.ID().getText());
    }

    @Override
    public Double visitDouble(@NotNull ExprParser.DoubleContext ctx) {
        return Double.parseDouble(ctx.DOUBLE().getText());
    }

    @Override
    public Double visitCalculate(@NotNull ExprParser.CalculateContext ctx) {
        return visit(ctx.plusOrMinus());
    }
}
