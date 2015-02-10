package pset1;

import static org.junit.Assert.*;

import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;

import org.junit.Test;

public class HashCodeTester {
    /*
     * P5: If two objects are equal according to the equals(Object)
     * method, then calling the hashCode method on each of
     * the two objects must produce the same integer result.
     */
     @Test public void t0(){
    	 Object o1 = new Boolean(true);
    	 Object o2 = new Boolean(true);
    	 if(o1.equals(o2)){
    		 assertTrue(o1.hashCode() == o2.hashCode());
		 }
     }
     @Test public void t1(){
    	 Object o = new Object();
    	 C c = new C(1);
    	 assertFalse(o.hashCode() == c.hashCode());
     }
     @Test public void t2(){
    	 Object o = new Object();
    	 D d = new D(1,2);
    	 assertFalse(o.hashCode() == d.hashCode());
     }
     
     @Test public void t3(){
    	 C c = new C(1);
    	 C c2 = new C(1);
    	 assertTrue(c.hashCode() == c2.hashCode());
     }
     @Test public void t4(){
    	 C c = new C(1);
    	 D d = new D(1,2);
    	 assertFalse(c.hashCode() == d.hashCode());
     }
     @Test public void t5(){
    	 C c = new C(1);
    	 Object o = new Object();
    	 assertFalse(c.hashCode() == o.hashCode());
     }
     
     @Test public void t6(){
    	 D d = new D(1,2);
    	 D d1 = new D(1,2);
    	 assertTrue(d.hashCode() == d1.hashCode());
     }
     @Test public void t7(){
    	 D d = new D(1,2);
    	 C c = new C(1);
    	 assertFalse(d.hashCode() == c.hashCode());
     }
     @Test public void t8(){
    	 D d = new D(1,2);
    	 Object o = new Object();
    	 assertFalse(d.hashCode() == o.hashCode());
     }
     
}