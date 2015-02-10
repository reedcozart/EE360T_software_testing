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
        SLList l = new SLList();
        //l is one element long and n loops on itself
        Node n = new Node();
        n.elem = false;
        n.next = n; 
        l.header = n; 
    }
    // your code goes here
}
