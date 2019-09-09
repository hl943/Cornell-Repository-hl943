import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RhinoTest {

    @Test
    public void testConstructor1() {
        Rhino R1= new Rhino("something", 'M', 1998, 11);
        assertEquals("something", R1.getName());
        assertEquals(false, R1.isFemale());
        assertEquals(1998, R1.getYOB());
        assertEquals(11, R1.getMOB());

    }

    @Test
    public void testConstructor2() {
        Rhino R2= new Rhino("something", 'F', 1998, 11);
        Rhino R3= new Rhino("anything", 'M', 1997, 11);
        Rhino R4= new Rhino("nothing", 'M', 1996, 11, R2, R3);
        assertEquals("nothing", R4.getName());
        assertEquals(false, R4.isFemale());
        assertEquals(1996, R4.getYOB());
        assertEquals(11, R4.getMOB());

        R4.setMom(R2);
        R4.setPop(R3);
        assertEquals(R4.getMom().getName(), "something");
        assertEquals(R4.getPop().getName(), "anything");
    }
}