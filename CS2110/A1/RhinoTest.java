import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RhinoTest {

    @Test
    public void testConstructor1() {
        Rhino R1= new Rhino("something", 1998, 11, 'M');
        // check that expected string name is computed by getName
        assertEquals("something", R1.getName());
        // check that expected gender M is computed false
        assertEquals(false, R1.isFemale());
        // check that expected in birth month is computed by getMOB
        assertEquals(11, R1.getMOB());
        // check that expected in birth year is computed by getYOB
        assertEquals(1998, R1.getYOB());
        // check that expected mom field is initialized to be null
        assertEquals(null, R1.getMom());
        // check that expected pop field is initialized to be null
        assertEquals(null, R1.getPop());
        // check that expected numChildren() return the initailized number of children
        assertEquals(0, R1.numChildren());
    }

    @Test
    public void testConstructor2() {

        // Testing constructor two by creating two Rhino objects (R2 & R3) which are the mom and dad
        // using the first constructor
        // and using the second constructor to create R4
        Rhino R2= new Rhino("something", 1998, 11, 'F');
        Rhino R3= new Rhino("anything", 1997, 11, 'M');
        Rhino R4= new Rhino("nothing", 1996, 11, 'M', R2, R3);
        // check that expected string name is computed by getName
        assertEquals("nothing", R4.getName());
        // check that expected gender M is computed false
        assertEquals(false, R4.isFemale());
        // check that expected int birth year is computed by getYOB
        assertEquals(1996, R4.getYOB());
        // check that expected in birth month is computed by getMOB
        assertEquals(11, R4.getMOB());
        // setting R4 mom and pop with R2 and R3 respectively
        R4.setMom(R2);
        R4.setPop(R3);
        assertEquals("something", R4.getMom().getName());
        assertEquals("anything", R4.getPop().getName());
        assertEquals(true, R4.getMom().isFemale());
        assertEquals(false, R4.getPop().isFemale());
        assertEquals(1998, R4.getMom().getYOB());
        assertEquals(1997, R4.getPop().getYOB());
        assertEquals(11, R4.getMom().getMOB());
        assertEquals(11, R4.getPop().getMOB());
    }

    @Test

    public void testisOlder() {
        int year[]= { 1976, 1977, 1978 };
        int month[]= { 1, 6, 12 };
        char gen[]= { 'M', 'F' };
        // Initialize rhino objects for testing
        Rhino R1= new Rhino("R1", year[1], month[1], gen[0]);
        Rhino R2= new Rhino("R2", year[1], month[1], gen[0]);
        Rhino R3= new Rhino("R3", year[1], month[0], gen[0]);
        Rhino R4= new Rhino("R4", year[1], month[2], gen[1]);
        Rhino R5= new Rhino("R5", year[0], month[1], gen[0]);
        Rhino R6= new Rhino("R6", year[0], month[0], gen[1]);
        Rhino R7= new Rhino("R7", year[0], month[2], gen[1]);
        Rhino R8= new Rhino("R8", year[2], month[1], gen[1]);
        Rhino R9= new Rhino("R9", year[2], month[0], gen[1]);
        Rhino R10= new Rhino("R10", year[2], month[2], gen[1]);
        // R1 and R2 have the same year and the same month, thus R1.isOlder is false
        assertEquals(false, R1.isOlder(R2));
        // R1 and R3 have the same year, R3.month is before R1.month, R1.isOlder is false
        assertEquals(false, R1.isOlder(R3));
        // R1 and R4 have the same year, R4.month is after R1.month, R1.isOlder is true
        assertEquals(true, R1.isOlder(R4));
        // R5, R6, R7.year are before R1.year, R6.month is before R1.month, R1.isOlder is false
        assertEquals(false, R1.isOlder(R5));
        // R1 birth year and birth month are after R6, R1.isOlder is false
        assertEquals(false, R1.isOlder(R6));
        // R1 birth month is before R7 and R1 birth year is after R7 birth month, R1.isOlder is
        // false
        assertEquals(false, R1.isOlder(R7));
        // R8.month is the same as R1.month and R1 year is before R8, R1.isOlder is false
        assertEquals(true, R1.isOlder(R8));
        // R1 year is before R9 birth year and R1 birth month is after R9 birth month, R1.isOlder is
        // true
        assertEquals(true, R1.isOlder(R9));
        // R1 year is before R10 year and R1 month is before R10 birth month, r1.isOlder is true
        assertEquals(true, R1.isOlder(R10));
    }

    @Test
    public void testareSilbings() {
        Rhino Rmom1= new Rhino("Mother1", 1976, 2, 'F');
        Rhino Rpop1= new Rhino("Father1", 1975, 4, 'M');
        Rhino Rmom2= new Rhino("Mother2", 1976, 2, 'F');
        Rhino Rpop2= new Rhino("Father2", 1976, 2, 'M');
        Rhino Rkid1= new Rhino("kid1", 1996, 6, 'F');
        Rhino Rkid2= new Rhino("kid2", 1998, 12, 'M');
        Rhino Rkid3= new Rhino("kid3", 1996, 6, 'F');
        Rhino Rkid4= new Rhino("kid4", 1998, 12, 'M');
        Rhino Rkid5= new Rhino("kid5", 1996, 6, 'F');
        Rhino Rkid6= new Rhino("kid6", 1998, 12, 'M');
        Rhino Rkid7= new Rhino("kid7", 1996, 6, 'F');
        Rhino Rkid8= new Rhino("kid8", 1998, 12, 'F');
        Rhino Rkid9= new Rhino("kid9", 1998, 12, 'M');
        // Rkid1 and Rkid 2 have no parents, check Rkid1 and Rkid2 are siblings, is false;
        assertEquals(false, Rkid1.areSiblings(Rkid2));
        // check Rkid1 and Rkid1 are siblings, is false;
        Rkid1.setMom(Rmom1);
        Rkid1.setPop(Rpop1);
        assertEquals(false, Rkid1.areSiblings(Rkid1));
        // Rkid2/Rkid3.mom to Rmom1, check Rkid2 and Rkid3 are siblings, is true;
        Rkid2.setMom(Rmom1);
        Rkid3.setMom(Rmom1);
        assertEquals(true, Rkid2.areSiblings(Rkid3));
        // set Rkid4/Rkid5.pop to Rpop1, check Rkid1 and Rkid2 are siblings, is
        // true;
        Rkid4.setPop(Rpop1);
        Rkid5.setPop(Rpop1);
        assertEquals(true, Rkid4.areSiblings(Rkid5));
        // set Rkid6.mom to Rmom1 Rkid7.mom to Rmom2, Rkid6.areSiblings(Rkid7) is false
        Rkid6.setMom(Rmom1);
        Rkid7.setMom(Rmom2);
        assertEquals(false, Rkid6.areSiblings(Rkid7));
        // set Rkid8.pop to Rpop1, Rkid9.pop to Rpop2, check Rkid1 and Rkid2 are siblings, is false;
        Rkid8.setPop(Rpop1);
        Rkid9.setPop(Rpop2);
        assertEquals(false, Rkid8.areSiblings(Rkid9));
    }

    // Below is a list of all preconditions:

    // Precondition for first constructor:
    // n has at least one Character in it, m is 1 for Jan, 2 for Feb, etc., and g
    // is 'F' or 'M' for female or male

    // Precondition for setterMethods:
    // this rhino's mom is null and mother is not null and mother is female.
    // this rhino's dad is null and father is not null and father is male.

    // Precondition for group D methods:
    // r is not null and this rhino was born before r" Precondition: r is not null.
    // this rhino and r are siblings" Precondition: r is not null.

    @Test
    void testAsserts() {
        Rhino Rmom= new Rhino("mother", 1985, 11, 'F');
        Rhino Rdad= new Rhino("father", 1980, 12, 'M');
        Rhino Rha= new Rhino("Rha", 1999, 10, 'F');
        // this dad's gender is F
        Rhino Rdad2= new Rhino("father", 1980, 12, 'F');
        // this mom's gender is M
        Rhino Rmom2= new Rhino("mother", 1985, 11, 'M');
        // Assert statement testing for constructor one
        // this Rhino's birth month is out of range
        assertThrows(AssertionError.class, () -> { new Rhino("errormonth", 1999, 19, 'M'); });
        // this Rhino's name (n) is shorter than 1 character or null
        assertThrows(AssertionError.class, () -> { new Rhino("", 1990, 12, 'F'); });
        // this Rhino's gender is neither char f or g
        assertThrows(AssertionError.class, () -> { new Rhino("genderneutral", 1990, 12, 'X'); });
        // Assert statement testing for setter method
        // This Rhino's mother is null
        assertThrows(AssertionError.class,
            () -> { new Rhino("motherless", 1990, 12, 'F', null, Rdad); });
        // This Rhino's father is null
        assertThrows(AssertionError.class,
            () -> { new Rhino("fatherless", 1990, 12, 'M', Rmom, null); });
        // This Rhino's mom is not a female
        assertThrows(AssertionError.class,
            () -> { new Rhino("mollie", 1990, 12, 'F', Rmom2, Rdad); });
        // This Rhino's dad is a female
        assertThrows(AssertionError.class,
            () -> { new Rhino("mollie", 1990, 12, 'F', Rmom2, Rdad2); });
        // Assert statement testing for group D methods
        // input param for areSiblings is null
        assertThrows(AssertionError.class, () -> { Rha.areSiblings(null); });
        // input param for isOlder is null
        assertThrows(AssertionError.class, () -> { Rha.isOlder(null); });
    }
}