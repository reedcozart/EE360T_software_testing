package pset1;

import static org.junit.Assert.*;
import org.junit.Test;

public class EqualsTester {
	/**
     * P1: For any non-null reference value x, x.equals(null) should return false.
     */
    @Test public void t0() {
        assertFalse(new Object().equals(null));
    }
    @Test public void t1(){
    	assertFalse(new C(1).equals(null));
    }
    @Test public void t2(){
    	assertFalse(new D(1,2).equals(null));
    }
    /**
     * P2: It is reflexive: for any non-null reference value x, x.equals(x)
     * should return true.
     */
    @Test public void t3(){
    	Object o = new Object();
    	assertTrue(o.equals(o));
    }
    @Test public void t4(){
    	C c = new C(1);
    	assertTrue(c.equals(c));
    }
    @Test public void t5(){
    	D d = new D(1,2);
    	assertTrue(d.equals(d));
    }
    /**
     * P3: It is symmetric: for any non-null reference values x and y, x.equals(y)
     * should return true if and only if y.equals(x) returns true.
     */
    @Test public void t6(){
    	Object o1 = new Boolean(true);
    	Object o2 = new Boolean(true);
    	assertTrue(o1.equals(o2));
    }
    @Test public void t7(){
    	Object o = new Object();
    	C c = new C(1);
    	assertFalse(o.equals(c));
    }
    @Test public void t8(){
    	Object o = new Object();
    	D d = new D(1,2);
    	assertFalse(o.equals(d));
    }
    
    @Test public void t9(){
    	C c = new C(1);
    	C c1 = new C(1);
    	assertTrue(c.equals(c1));
    }
    @Test public void t10(){
    	C c = new C(1);
    	Object o = new Object();
    	assertFalse(c.equals(o));
    }
    @Test public void t11(){
    	C c = new C(1);
    	D d = new D(1,2);
    	assertFalse(c.equals(d));
    }
    
    @Test public void t12(){
    	D d1 = new D(1,2);
    	D d2 = new D(1,2);
    	assertTrue(d1.equals(d2));
    }
    @Test public void t13(){
    	D d = new D(1,2);
    	C c = new C(1);
    	assertFalse(d.equals(c));
    }
    @Test public void t14(){
    	D d = new D(1,2);
    	Object o = new Object();
    	assertFalse(d.equals(o));
    }
    
    @Test public void t15(){
    	D d = new D(1,2);
    	D d2 = new D(1,3);
    	assertFalse(d.equals(d2));
    }
    @Test public void t16(){
    	D d = new D(1,2);
    	D d2 = new D(2,2);
    	assertFalse(d.equals(d2));
    }
    /**
     * P4: It is transitive: for any non-null reference values x, y, and z,
     * if x.equals(y) returns true and y.equals(z) returns true, then
     * x.equals(z) should return true.
     */
    // you do not need to write tests for P4
}