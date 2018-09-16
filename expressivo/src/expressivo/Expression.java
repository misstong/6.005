package expressivo;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import lib6005.parser.*;

/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 * 
 * <p>PS1 instructions: this is a required ADT interface.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You may, however, add additional methods, or strengthen the specs of existing methods.
 * Declare concrete variants of Expression in their own Java source files.
 */
public interface Expression {
    
    // Datatype definition
    //   TODO
    /**
     *  Expression=Number(value:double)
     *          +Variable(name:string)
     *          +Plus(left:Expression,right:Expression)
     *          +Times(left:Expression,right:Expression)
     */
    
    enum Grammar {ROOT,SUM,PRODUCT,PRIMITIVE,NUMBER,VARIABLE,WHITESPACE};
    
    /**
     * Create a number expression
     * @param value 
     * @return
     */
     public static Expression number(double value) {
         return new Number(value);
     }
     
     public static Expression variable(String name) {
         return new Variable(name);
     }
     
     public Expression plus(Expression e) ;
     
     public Expression times(Expression e) ;
     
     public Expression differentiate(String variable);
     
     public Expression simplify(Map<String,Double> env);
    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS1 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) {
        //throw new RuntimeException("unimplemented");
        try {
            Parser<Grammar> parser=
                    GrammarCompiler.compile(new File("src/expressivo/Expression.g"), Grammar.ROOT);
            ParseTree<Grammar> tree=parser.parse(input);
            //System.out.println(tree.toString());
            //tree.display();
            return buildAST(tree);
        } catch (UnableToParseException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        throw new IllegalArgumentException("expression is invalid");
        
    }
    
    public static Expression buildAST(ParseTree<Grammar> p) {
        // TODO Auto-generated method stub
        switch(p.getName()) {
        case NUMBER:
            return Expression.number(Double.parseDouble(p.getContents()));
        case VARIABLE:
            return Expression.variable(p.getContents());
        case PRIMITIVE:
            if(p.childrenByName(Grammar.NUMBER).isEmpty()&&p.childrenByName(Grammar.VARIABLE).isEmpty()) {
                return buildAST(p.childrenByName(Grammar.SUM).get(0));
            }else if(p.childrenByName(Grammar.NUMBER).isEmpty()) {
                return buildAST(p.childrenByName(Grammar.VARIABLE).get(0));
            }else {
                return buildAST(p.childrenByName(Grammar.NUMBER).get(0));
            }
        case PRODUCT:
            boolean first = true;
            Expression result = null;
            for (ParseTree<Grammar> child : p.childrenByName(Grammar.PRIMITIVE)){                
                if (first){
                    result = buildAST(child);
                    first = false;
                } else {
                    result = new Times(result, buildAST(child));
                }
            }
            if (first) {
                throw new RuntimeException("product must have a non whitespace child:" + p);
            }
            return result;
        case SUM:
             first=true;
             result=null;
            for(ParseTree<Grammar> child:p.childrenByName(Grammar.PRODUCT)) {
                if(first) {
                    result=buildAST(child);
                    first=false;
                }else {
                    result=new Plus(result,buildAST(child));
                }
                
            }
            if (first) {
                throw new RuntimeException("sum must have a non whitespace child:" + p);
            }
            return result;
        case ROOT:
            return buildAST(p.childrenByName(Grammar.SUM).get(0));
        case WHITESPACE:
            throw new RuntimeException("You should never reach here:" + p);
        }
        throw new RuntimeException("You should never reach here:" + p);
    }
    
    public boolean hasValue();
    public double getValue();

    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     */
    @Override 
    public String toString();

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS1 handout.
     */
    @Override
    public boolean equals(Object thatObject);
    
    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode();
    
    // TODO more instance methods
    
    /* Copyright (c) 2015-2017 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires permission of course staff.
     */
}
