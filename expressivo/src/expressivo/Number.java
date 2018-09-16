package expressivo;

import java.util.Map;

public class Number implements Expression{
    private final double value;
    
    Number(double value){
        this.value=value;
    }

    @Override
    public Expression plus(Expression e) {
        // TODO Auto-generated method stub
        return new Plus(this,e);
    }

    @Override
    public Expression times(Expression e) {
        // TODO Auto-generated method stub
        return new Times(this,e);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return Double.hashCode(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if(!(obj instanceof Number)) return false;
        Number num=(Number)obj;
        return this.value==num.value;
             
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("%.4f", value);
    }

    @Override
    public Expression differentiate(String variable) {
        // TODO Auto-generated method stub
        return new Number(0);
    }

    @Override
    public Expression simplify(Map<String, Double> env) {
        // TODO Auto-generated method stub
        return new Number(value);
    }

    @Override
    public boolean hasValue() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public double getValue() {
        // TODO Auto-generated method stub
        return value;
    }
    
    

}
