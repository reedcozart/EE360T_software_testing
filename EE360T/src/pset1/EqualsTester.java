package pset1;
import static org.junit.Assert.*;
import org.junit.Test;
public class EqualsTester {
    /*
     * P1: For any non-null reference value x, x.equals(null) should return false.
     */
    @Test public void t0() {
        assertFalse(new Object().equals(null));
}
    // your test methods for P1 go here
    /*
     * P2: It is reflexive: for any non-null reference value x, x.equals(x)
     * should return true.
     */
    // your test methods for P2 go here
    /*
     * P3: It is symmetric: for any non-null reference values x and y, x.equals(y)
     * should return true if and only if y.equals(x) returns true.
     */
    // your test methods for P3 go here
    /*
     * P4: It is transitive: for any non-null reference values x, y, and z,
     * if x.equals(y) returns true and y.equals(z) returns true, then
     * x.equals(z) should return true.
     */
    // you do not need to write tests for P4
}