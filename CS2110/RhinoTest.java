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

}
