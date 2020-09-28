// --== CS400 File Header Information ==--
// Name: Sahil Srivastava
// Email: srivastava34@wisc.edu
// Team: KF
// TA: Sid
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

public class Patient {

    private int facility;
    private int floor;
    private boolean positive;
    private int age;
    private int id;
    private String name;

    private final int rand = 84249;

    Patient(int facility, int floor, boolean positive, int age, int id, String name) {
        this.facility = facility;
        this.floor = floor;
        this.positive = positive;
        this.age = age;
        this.id = id;
        this.name = name;
    }

    public int getFacility() {
        return facility;
    }
    public int getFloor() {
        return floor;
    }
    public boolean hasCovid() {
        return positive;
    }
    public int getAge() {
        return age;
    }
    public int getID() {
        return id;
    }
    public String getName() {
        return name;
    }

    /**
     * Prints out all patient information
     */
    public void info() {
        System.out.println("Facility: " + facility + " Floor: " + floor + " Covid: " + positive + " Age: " + age + " ID: " + id + " Name: " + name);
    }

    /**
     * @param capacity capacity of the current hashtable to properly hash values
     * @return hashCode used for key in hashtable
     */
    public int hashCode(int capacity) {
        return ((name.hashCode() + age + id) * rand) % capacity;
    }
}
