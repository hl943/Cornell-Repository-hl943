import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RhinoTest {

    @Test
    public void testConstructor1() {
        Rhino R1= new Rhino("something", 'M', 1998, 11);

        // check that expected string name is computed by getName
        assertEquals("something", R1.getName());

        // check that expected gender M is computed false
        assertEquals(false, R1.isFemale());

        // check that expected in birth month is computed by getMOB
        assertEquals(11, R1.getMOB());

    }

    @Test
    public void testConstructor2() {

        // Testing constructor two by creating two Rhino objects (R2 & R3) which are the mom and dad
        // using the first constructor
        // and using the second constructor to create R4

        Rhino R2= new Rhino("something", 'F', 1998, 11);
        Rhino R3= new Rhino("anything", 'M', 1997, 11);
        Rhino R4= new Rhino("nothing", 'M', 1996, 11, R2, R3);

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
        assertEquals(R4.getMom().getName(), "something");
        assertEquals(R4.getPop().getName(), "anything");
        assertEquals(R4.getMom().isFemale(), true);
        assertEquals(R4.getPop().isFemale(), false);
        assertEquals(R4.getMom().getYOB(), 1998);
        assertEquals(R4.getPop().getYOB(), 1997);
        assertEquals(R4.getMom().getMOB(), 11);
        assertEquals(R4.getPop().getMOB(), 11);

    }

    @Test

    public void testisOlder() {

        int year[]= { 1976, 1977, 1978 };
        int month[]= { 1, 6, 12 };
        char gen[]= { 'M', 'F' };

        // Initialize rhino objects for testing
        Rhino R1= new Rhino("R1", gen[1], year[1], month[1]);
        Rhino R2= new Rhino("R2", gen[0], year[1], month[1]);
        Rhino R3= new Rhino("R3", gen[0], year[1], month[0]);
        Rhino R4= new Rhino("R4", gen[1], year[1], month[2]);
        Rhino R5= new Rhino("R5", gen[0], year[0], month[1]);
        Rhino R6= new Rhino("R6", gen[1], year[0], month[0]);
        Rhino R7= new Rhino("R7", gen[1], year[0], month[2]);
        Rhino R8= new Rhino("R8", gen[1], year[2], month[1]);
        Rhino R9= new Rhino("R9", gen[1], year[2], month[0]);
        Rhino R10= new Rhino("R10", gen[1], year[2], month[2]);

        // R1 and R2 have the same year and the same month, thus R1.isOlder is false
        assertEquals(R1.isOlder(R2), false);

        // R1 and R3 have the same year, R3.month is before R1.month, R1.isOlder is false
        assertEquals(R1.isOlder(R3), false);

        // R1 and R4 have the same year, R4.month is after R1.month, R1.isOlder is true
        assertEquals(R1.isOlder(R4), true);

        // R5, R6, R7.year are before R1.year, R6.month is before R1.month, R1.isOlder is false
        assertEquals(R1.isOlder(R5), false);

        // R1 birth year and birth month are after R6, R1.isOlder is false
        assertEquals(R1.isOlder(R6), false);

        // R1 birth month is before R7 and R1 birth year is after R7 birth month, R1.isOlder is
        // false
        assertEquals(R1.isOlder(R7), false);

        // R8.month is the same as R1.month and R1 year is before R8, R1.isOlder is false
        assertEquals(R1.isOlder(R8), true);

        // R1 year is before R9 birth year and R1 birth month is after R9 birth month, R1.isOlder is
        // true
        assertEquals(R1.isOlder(R9), true);

        // R1 year is before R10 year and R1 month is before R10 birth month, r1.isOlder is true
        assertEquals(R1.isOlder(R10), true);

    }

    @Test

    public void testareSilbings() {

        Rhino Rmom1= new Rhino("Mother1", 'F', 1976, 2);
        Rhino Rpop1= new Rhino("Father1", 'M', 1975, 4);
        Rhino Rmom2= new Rhino("Mother2", 'F', 1976, 2);
        Rhino Rpop2= new Rhino("Father2", 'M', 1976, 2);
        Rhino Rkid1= new Rhino("kid1", 'F', 1996, 6);
        Rhino Rkid2= new Rhino("kid2", 'M', 1998, 12);
        Rhino Rkid3= new Rhino("kid3", 'F', 1996, 6);
        Rhino Rkid4= new Rhino("kid4", 'M', 1998, 12);
        Rhino Rkid5= new Rhino("kid5", 'F', 1996, 6);
        Rhino Rkid6= new Rhino("kid6", 'M', 1998, 12);
        Rhino Rkid7= new Rhino("kid7", 'F', 1996, 6);
        Rhino Rkid8= new Rhino("kid8", 'M', 1998, 12);
        Rhino Rkid9= new Rhino("kid9", 'M', 1998, 12);

        // Rkid1 and Rkid 2 have no parents, check Rkid1 and Rkid2 are siblings, is false;
        assertEquals(Rkid1.areSiblings(Rkid2), false);

        // check Rkid1 and Rkid1 are siblings, is false;
        Rkid1.setMom(Rmom1);
        Rkid1.setPop(Rpop1);
        assertEquals(Rkid1.areSiblings(Rkid1), false);

        // Rkid2/Rkid3.mom to Rmom1, check Rkid2 and Rkid3 are siblings, is true;
        Rkid2.setMom(Rmom1);
        Rkid3.setMom(Rmom1);
        assertEquals(Rkid2.areSiblings(Rkid3), true);

        // set Rkid4/Rkid5.pop to Rpop1, check Rkid1 and Rkid2 are siblings, is

        // true;
        Rkid4.setPop(Rpop1);
        Rkid5.setPop(Rpop1);
        assertEquals(Rkid4.areSiblings(Rkid5), true);

        // set Rkid6.mom to Rmom1 Rkid7.mom to Rmom2, Rkid6.areSiblings(Rkid7) is false
        Rkid6.setMom(Rmom1);
        Rkid7.setMom(Rmom2);
        assertEquals(Rkid6.areSiblings(Rkid7), false);

        // set Rkid8.pop to Rpop1, Rkid9.pop to Rpop2, check Rkid1 and Rkid2 are siblings, is false;
        Rkid8.setPop(Rpop1);
        Rkid9.setPop(Rpop2);
        assertEquals(Rkid8.areSiblings(Rkid9), false);

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

        Rhino Rmom= new Rhino("mother", 'F', 1985, 11);
        Rhino Rdad= new Rhino("father", 'M', 1980, 12);
        Rhino Rha= new Rhino("Rha", 'F', 1999, 10);

        // this dad's gender is F
        Rhino Rdad2= new Rhino("father", 'F', 1980, 12);

        // this mom's gender is M
        Rhino Rmom2= new Rhino("mother", 'M', 1985, 11);

        // Assert statement testing for constructor one
        // this Rhino's birth month is out of range
        assertThrows(AssertionError.class, () -> { new Rhino("errormonth", 'M', 1999, 19); });

        // this Rhino's name (n) is shorter than 1 character or null
        assertThrows(AssertionError.class, () -> { new Rhino("", 'F', 1990, 12); });

        // this Rhino's gender is neither char f or g
        assertThrows(AssertionError.class, () -> { new Rhino("genderneutral", 'X', 1990, 12); });

        // Assert statement testing for setter method
        // This Rhino's mother is null
        assertThrows(AssertionError.class,
            () -> { new Rhino("motherless", 'F', 1990, 12, null, Rdad); });

        // This Rhino's father is null
        assertThrows(AssertionError.class,
            () -> { new Rhino("fatherless", 'M', 1990, 12, Rmom, null); });

        // This Rhino's mom is not a female
        assertThrows(AssertionError.class,
            () -> { new Rhino("mollie", 'F', 1990, 12, Rmom2, Rdad); });

        // This Rhino's dad is a female
        assertThrows(AssertionError.class,
            () -> { new Rhino("mollie", 'F', 1990, 12, Rmom2, Rdad2); });

        // Assert statement testing for group D methods
        // input param for areSiblings is null
        assertThrows(AssertionError.class, () -> { Rha.areSiblings(null); });

        // input param for isOlder is null
        assertThrows(AssertionError.class, () -> { Rha.isOlder(null); });

    }
}
