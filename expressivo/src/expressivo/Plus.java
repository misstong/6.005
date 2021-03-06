package expressivo;

import java.util.Map;

public class Plus implements Expression{
    
    private final Expression left;
    private final Expression right;
    
    Plus(Expression left,Expression right){
        this.left=left;
        this.right=right;
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
        int hashcode=1;
        hashcode=31*hashcode+left.hashCode();
        hashcode=31*hashcode+right.hashCode();
        return hashcode;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if(!(obj instanceof Plus)) return false;
        Plus plus=(Plus)obj;
        return plus.left.equals(left)&&plus.right.equals(right);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "("+this.left.toString()+"+"+this.right.toString()+")";
    }

    @Override
    public Expression differentiate(String variable) {
        // TODO Auto-generated method stub
        return new Plus(left.differentiate(variable),right.differentiate(variable));
    }

    @Override
    public Expression simplify(Map<String, Double> env) {
        // TODO Auto-generated method stub
        Expression l=left.simplify(env);
        Expression r=right.simplify(env);
        if(l.hasValue()&&r.hasValue()) {
            return new Number(l.getValue()+r.getValue());
        }
        return new Plus(l,r);
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
