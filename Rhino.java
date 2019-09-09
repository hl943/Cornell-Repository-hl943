/** NetId: nnnnn, nnnnn. Time spent: hh hours, mm minutes.
* What I thought about this assignment:
*
*/
/** An instance maintains info about the Rhino. */
public class Rhino {
    /** Name of this rhino. Must contain at least 1 character. */
    private String name;
    /** Gender of this rhino. 'F' for female, 'M' for male. */
    private char gender;
    /** Year of birth. Can be any integer. */
    private int year;
    /** Month of birth. 1 for Jan, 2 for Feb, …, 12 for Dec. */
    private int month;
    /** Number of known children of this Rhino, >=0 */
    private int children;
    /** Mother of this rhino —null if unknown. */
    private Rhino mom;
    /** Father of this rhino —null if unknown. */
    private Rhino pop;

    /** Constructor: a new Rhino with name n, birth year y, birth month m, and gender g.<br>
     * Its father and mother are unknown, and it has no children.<br>
     * Precondition: n has at least one character in it, m is 1 for Jan, 2 for Feb, etc.,<br>
     * and g is 'F' or 'M' for female or male */
    public Rhino(String n, char g, int y, int m) {
        assert n != null && n.length() > 0;
        assert g == 'M' | g == 'F';
        assert m <= 12;
        name= n;
        gender= g;
        year= y;
        month= m;
        mom= null;
        pop= null;
        children= 0;

    }

//Group A methods

    /** = the name of this rhino. */
    public String getName() {
        return name;
    }

    /** = this Rhino is female */
    public boolean isFemale() {
        return gender == 'F';
    }

    /** = the month this rhino was born in the range 1..12. */
    public int getMOB() {
        return month;
    }

    /** = the year this rhino was born. */
    public int getYOB() {
        return year;
    }

    /** = (pointer to) the object for mother of this rhino. */
    public Rhino getMom() {
        Rhino mother= mom;
        return mother;
    }

    /** = (pointer to) the object for father of this rhino. */
    public Rhino getPop() {
        Rhino father= pop;
        return father;
    }

    /** = the number of known children of this rhino. */
    public int numChildren() {
        return children;
    }

//Group B methods
    /** Set the rhino's mom to mother.<br>
     * Precondition: this rhino's mom is null and mother is not null and<br>
     * mother is female. */
    public void setMom(Rhino R) {
        assert mom == null;
        assert R.getMom() != null;
        assert R.getMom().isFemale() == true;
        mom= R.getMom();

    }

    /** Set this rhino's dad to father.<br>
     * Precondition: this rhino's dad is null and father is not null and<br>
     * father is male. */
    public void setPop(Rhino R) {
        assert pop == null;
        assert R.getPop() != null;
        assert R.getPop().isFemale() == false;
        pop= R.getPop();
    }
//Group C

}
