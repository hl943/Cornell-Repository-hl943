/** NetId: amd439, hl943 Time spent: 06 hours 30 minutes
 *
 * What I thought about this assignment:
 *
 *
 * An instance maintains info about the Rhino. */
public class Rhino {
    /** Name of this rhino. Must contain at least 1 character. */
    private String name;
    /** Gender of this rhino. 'F' for female, 'M' for male. */
    private char gender;
    /** Year of birth. Can be any integer. */
    private int year;
    /** Month of birth. 1 for Jan, 2 for Feb, …, 12 for Dec. */
    private int month;
    /** Number of known children of this Rhino */
    private int children;
    /** Mother of this rhino —null if unknown. */
    private Rhino mom;
    /** Father of this rhino —null if unknown. */
    private Rhino pop;

    /** Constructor: a new Rhino with name n, birth year y, birth month m, and gender g. * Its
     * father and mother are unknown, and it has no children. Precondition: n has at least one
     * character in it, m is 1 for Jan, 2 for Feb, etc., and g is 'F' or 'M' for female or male */

    public Rhino(String n, int y, int m, char g) {
        assert n != null && n.length() > 0;
        assert g == 'M' | g == 'F';
        assert m <= 12 && m > 0;
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

// Group C constructors

    public Rhino(String n, int y, int m, char g, Rhino mother, Rhino father) {
        this(n, y, m, g);
        assert mother != null;
        assert mother.isFemale();
        assert father != null;
        assert father.isFemale() == false;
        mother = mom;
        father = pop;
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
        assert mother.isFemale();
        mom= mother;
        mom.children= mom.numChildren() + 1;

    }

    /** Set this rhino's dad to father. Precondition: this rhino's dad is null and father is not
     * null and father is male. */
    public void setPop(Rhino father) {
        assert pop == null;
        assert father != null;
        assert !father.isFemale();
        pop= father;
        pop.children= pop.numChildren() + 1;

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

    public static void main(String[] args) {
        Rhino R1= new Rhino("something", 1998, 11, 'M');
        Rhino R2= new Rhino("something", 1998, 11, 'F');
        R1.setMom(R2);
        System.out.println(R2.equals(R1.getMom()));
        System.out.println(R2.numChildren());
    }
}
