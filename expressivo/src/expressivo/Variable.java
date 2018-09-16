package expressivo;

import java.util.Map;

public class Variable implements Expression{
    
    private final String name;
    
    Variable(String name){
        this.name=name;
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
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if(!(obj instanceof Variable ) ) return false;
        Variable tmp=(Variable)obj;
        return tmp.name.equals(name);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.name;
    }

    @Override
    public Expression differentiate(String variable) {
        // TODO Auto-generated method stub
        if(name.equals(variable)) return new Number(1);
        return new Number(0);
    }

    @Override
    public Expression simplify(Map<String, Double> env) {
        // TODO Auto-generated method stub
        if(env.containsKey(name)) {
            return new Number(env.get(name));
        }
        return this;
    }

    @Override
    public boolean hasValue() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public double getValue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
