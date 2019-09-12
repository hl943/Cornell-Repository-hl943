/** NetId: amd439 Time spent: 06 hours 30 minutes
 *
 * What I thought about this assignment:
 *
 *
 * /** An instance maintains info about the Rhino. */

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

    /** Constructor: a new Rhino with name n, birth year y, birth month m, and gender g. * Its
     * father and mother are unknown, and it has no children. Precondition: n has at least one
     * character in it, m is 1 for Jan, 2 for Feb, etc., and g is 'F' or 'M' for female or male */

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

    /** Constructor: a new Rhino with name n, birth year y, birth month m, gender g, mother mother,
     * father father, and no children.<br>
     * Precondition: n has at least one character in it, m is 1 for Jan, 2 for Feb, etc., g is 'F'
     * or 'M' for female or male, and mother is non-null and female, and father is non-null and
     * male). */

// Group C methods

    public Rhino(String n, char g, int y, int m, Rhino mother, Rhino father) {

        this(n, g, y, m); // calling from constructor 1 preconditions

        assert mother != null;

        assert mother.isFemale() == true;

        assert father != null;

        assert father.isFemale() == false;

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
        return mom;
    }

    /** = (pointer to) the object for father of this rhino. */

    public Rhino getPop() {
        return pop;
    }

    /** = the number of known children of this rhino. */

    public int numChildren() {
        return children;
    }

//Group B methods

    /** Set the rhino's mom to mother. Precondition: this rhino's mom is null and mother is not null
     * and mother is female. */

    public void setMom(Rhino mother) {

        assert mom == null;
        assert mother != null;
        assert mother.isFemale() == true;
        mom= mother;
    }

    /** Set this rhino's dad to father. Precondition: this rhino's dad is null and father is not
     * null and father is male. */

    public void setPop(Rhino father) {

        assert pop == null;
        assert father != null;
        assert father.isFemale() == false;
        pop= father;

    }

    // Group D methods

    /** = "r is not null and this rhino was born before r" Precondition: r is not null. */
    public boolean isOlder(Rhino r) {
        assert r != null;
        return year < r.getYOB() || year == r.getYOB() && month < r.getMOB();
    }

    /** = "this rhino and r are siblings" Precondition: r is not null. */
    public boolean areSiblings(Rhino r) {
        assert r != null;
        return this != r && (mom != null && r.getMom() != null && mom == r.getMom() ||
            pop != null && r.getPop() != null && pop == r.getPop());
    }
}
