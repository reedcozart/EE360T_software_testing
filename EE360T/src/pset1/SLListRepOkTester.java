package pset1;

import static org.junit.Assert.*;
import org.junit.Test;
import pset1.SLList.Node;

public class SLListRepOkTester {
	
    @Test public void t0() {
        SLList l = new SLList();
        assertTrue(l.repOk());
    }
    //Test suite needs to consist of valid, and invalid tests. It needs to make a decision, (make sure it isn't an infinite loop)
    @Test public void t1() {
    	//l is one element long and n loops on itself
    	SLList l = new SLList();
        Node n = new Node();
        n.elem = false;
        n.next = n; 
        l.header = n; 
        assertFalse(l.repOk());
    }
    /**
     * Valid SLList l->n->m test
     */
    @Test public void t2() {
    	//valid SLList l->n->m
    	SLList l = new SLList();
        Node n = new Node();
        Node m = new Node();
        l.header = n;
        n.elem = false; 
        n.next = m;
        m.elem = true;
        m.next = null;
        assertTrue(l.repOk());
    }
    /**
     * Invalid SLList l->n->m->n->m->..... test
     */
    @Test public void t3(){
       	SLList l = new SLList();
        Node n = new Node();
        Node m = new Node();
        l.header = n;
        n.elem = false; 
        n.next = m;
        m.elem = true;
        m.next = n;
        assertFalse(l.repOk());
    }
    /**
     * Invalid SLList l: n->m->m->m.... test
     */
    @Test public void t4(){
    	SLList l = new SLList();
        Node n = new Node();
        Node m = new Node();
        l.header = n;
        n.elem = false; 
        n.next = m;
        m.elem = true;
        m.next = m;
        assertFalse(l.repOk());
    }
    /**
     * Valid SLlist l: n
     */
    @Test public void t5(){
    	SLList l = new SLList();
        Node n = new Node();
        l.header = n;
        n.elem = false; 
        n.next = null;
        assertTrue(l.repOk());
    }
    
}
