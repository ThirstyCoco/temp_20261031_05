/** A nurse, who can take blood samples but cannot prescribe drugs. */
public class Nurse extends HealthProfessional {

    private final String ward;

    /** Creates a nurse with an id supplied by the caller. */
    public Nurse(String id, String name, String[] workingDays, String ward) {
        super(id, name, workingDays);
        this.ward = (ward == null || ward.trim().isEmpty()) ? "Unassigned" : ward;
    }

    /** Creates a nurse and lets the parent class generate the id. */
    public Nurse(String name, String[] workingDays, String ward) {
        super(name, workingDays);
        this.ward = (ward == null || ward.trim().isEmpty()) ? "Unassigned" : ward;
    }

    public String getWard() {
        return ward;
    }

    /** Identifies this professional as a nurse. */
    @Override
    public String getRole() {
        return "Nurse";
    }

    /** Takes a blood sample, which only nurses do in this service. */
    public String takeBloodSample(String patientName) {
        if (patientName == null || patientName.trim().isEmpty()) {
            return "Sample not taken: a patient name is required.";
        }
        return getName() + " took a blood sample from " + patientName + ".";
    }

    /** Adds the ward to the details provided by the parent class. */
    @Override
    public String toString() {
        return super.toString() + " | Ward: " + ward;
    }
}
