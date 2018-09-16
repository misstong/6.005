/* Copyright (c) 2015-2017 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    // TODO tests for Expression
    @Test
    public void testTostring() {
        Expression e=Expression.number(10).plus(Expression.variable("x")).times(Expression.number(6));
        assertEquals("(10.0000+x)*6.0000",e.toString());
    }
    
    @Test
    public void testEquals() {
        Expression ten=Expression.number(10);
        Expression ten2=Expression.number(10);
        Expression x=Expression.variable("x");
        Expression seven=Expression.number(7);
        assertEquals(ten.plus(x).times(seven),ten2.plus(x).times(seven));
        assertEquals(ten.plus(x).times(seven).hashCode(),ten2.plus(x).times(seven).hashCode());
    }
    
    @Test
    public void testParse() {
        Expression e=Expression.parse("1+2*x");
        assertEquals(e.toString(),"(1.0000+2.0000*x)");
    }
    
    @Test
    public void testDifferentiate() {
        Expression e=Expression.parse("1+2*x");
        assertEquals(e.differentiate("x"),new Plus(new Number(0),new Plus(new Times(new Number(2),new Number(1)),new Times(new Variable("x"),new Number(0)))));
        
    }
    
    @Test
    public void testSimplify() {
        Expression e=Expression.parse("1+2*x");
        Map<String,Double> env=new HashMap<String,Double>();
        env.put("x", (double) 0);
        assertEquals("1.0000",e.simplify(env).toString());
    }
}
