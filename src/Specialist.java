/** A specialist such as a cardiologist or a paediatrician. */
public class Specialist extends HealthProfessional {

    private final String specialty;

    /** Creates a specialist with an id supplied by the caller. */
    public Specialist(String id, String name, String[] workingDays, String specialty) {
        super(id, name, workingDays);
        this.specialty = (specialty == null || specialty.trim().isEmpty()) ? "General" : specialty;
    }

    /** Creates a specialist and lets the parent class generate the id. */
    public Specialist(String name, String[] workingDays, String specialty) {
        super(name, workingDays);
        this.specialty = (specialty == null || specialty.trim().isEmpty()) ? "General" : specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    /** Identifies this professional as a specialist. */
    @Override
    public String getRole() {
        return "Specialist";
    }

    /** Orders a diagnostic test, which only specialists can do. */
    public String orderDiagnosticTest(String testName) {
        if (testName == null || testName.trim().isEmpty()) {
            return "Test not ordered: a test name is required.";
        }
        return getName() + " ordered a " + testName + " for the patient.";
    }

    /** Adds the specialty to the details provided by the parent class. */
    @Override
    public String toString() {
        return super.toString() + " | Specialty: " + specialty;
    }
}
