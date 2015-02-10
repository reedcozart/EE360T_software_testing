package pset1;

import static org.junit.Assert.*;

import org.junit.Test;
import pset1.SLList.Node;

public class SLListAddTester {
	
    @Test public void test0() {
        SLList l = new SLList();
        assertTrue(l.repOk());
        l.add(true);
        // write a sequence of assertTrue method invocations that
        // perform checks on the values for all the declared fields
        // of list and Node objects reachable from l
        assertTrue(l.header != null);
        // your code goes here
        assertTrue(l.header instanceof Node );
        assertTrue(l.header.getClass() == Node.class);
        assertTrue(l.header.elem == true);
        assertTrue(l.header.next == null);
        assertTrue(l.repOk());
    }
    
    @Test public void test1() {
        SLList l = new SLList();
        assertTrue(l.repOk());
        l.add(true);
        assertTrue(l.repOk());
        l.add(false);
        assertTrue(l.repOk());
        // write a sequence of assertTrue method invocations that
        // perform checks on the values for all the declared fields
        // of list and node objects reachable from l
        assertTrue(l.header != null);
        // your code goes here        
        assertTrue(l.header instanceof Node );
        assertTrue(l.header.getClass() == Node.class);
        assertTrue(l.header.elem == false);
        Node n = l.header.next;
        assertTrue(n instanceof Node );
        assertTrue(n.getClass() == Node.class);
        assertTrue(n.elem == true);
        assertTrue(l.repOk());
    }
}
