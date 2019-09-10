import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RhinoTest {

    @Test
    public void testConstructor1() {
        Rhino R1= new Rhino("something", 'M', 1998, 11);
        //check that expected string name is computed by getName
        assertEquals("something", R1.getName());
        //check that expected gender M is computed false
        assertEquals(false, R1.isFemale());
        //check that expected in birth month is computed by getMOB
        assertEquals(11, R1.getMOB());

    }

    
    @Test
    public void testConstructor2() {
      //Testing constructor two by creating two Rhino objects (R2 & R3) which are the mom and dad using the first constructor
      // and using the second constructor to create R4
        Rhino R2= new Rhino("something", 'F', 1998, 11);
        Rhino R3= new Rhino("anything", 'M', 1997, 11);
        Rhino R4= new Rhino("nothing", 'M', 1996, 11, R2, R3);
       
        //check that expected string name is computed by getName
        assertEquals("nothing", R4.getName());
        //check that expected gender M is computed false
        assertEquals(false, R4.isFemale());
        //check that expected int birth year is computed by getYOB
        assertEquals(1996, R4.getYOB());
        //check that expected in birth month is computed by getMOB
        assertEquals(11, R4.getMOB());
        
        //setting R4 mom and pop with R2 and R3 respectively
        R4.setMom(R2);
        R4.setPop(R3);
        
        assertEquals(R4.getMom().getName(), "something");
        assertEquals(R4.getPop().getName(), "anything");
        assertEquals(R4.getMom().isFemale(), true);
        assertEquals(R4.getPop().isFemale(),false);
        assertEquals(R4.getMom().getYOB(), 1998);
        assertEquals(R4.getPop().getYOB(), 1997);
        assertEquals(R4.getMom().getMOB(), 11);
        assertEquals(R4.getPop().getMOB(), 11);
        
    }        
    
}
